#!/bin/sh -x
# update the mw environment, rebuild webstart and javadoc
cd $(dirname $0)
cd ..
svn update
(cd ../encumbered ; svn update)
ant/bin/ant -Dedition.dir=../encumbered clean dist
cd ..
mv hl7-javasig.zip public_html/hl7-javasig-$(date +'%u-%Y%m%d').zip

