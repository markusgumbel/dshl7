#/bin/sh
cat <<_END_ |sqlite3 /usr/tracroot/javasig/db/trac.db
begin;
  insert into spammer(username)
    select distinct author
      from wiki
        where text like '%<a href=%'
          and author not in (select username from permission)
          and author not in (select username from spammer)
  ;
  insert into spammer(username)
    select distinct reporter
      from ticket
        where description like '%<a href=%'
          and reporter not in (select username from permission)
          and reporter not in (select username from spammer)
  ;
  delete from wiki where author in (select username from spammer);
  delete from ticket where reporter in (select username from spammer);
commit;
_END_
