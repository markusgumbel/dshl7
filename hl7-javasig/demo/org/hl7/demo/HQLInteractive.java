/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.hl7.hibernate.Persistence;

/** The bare bones of an interactive HQL query tool. This one is 
		really hard to use and of course command line. It's a quick
		hack to try stuff out.

    This test is written as if it was a junit TestCase but I didn't
		bother to depend on the junit library. Also, I do not actually
		make assertions, instead I depend on the log output to check
		what's going on.

		Queries are assumed SQL queries if they start with the word
		'select', else they are considered HQL queries.

		@author Gunther Schadow
		@version $Id: HQLInteractive.java 5652 2007-03-30 15:35:44Z crosenthal $
*/
public class HQLInteractive {

	/** Use this to define canned queries to run */
	static final Command _commands[] = {
		new Command("from org.hl7.rim.impl.RoleImpl as role inner join role.id as roleid where roleid.extension = 'gschadow'", "hql"),
		new Command("@u:org.hl7.rim.impl.RoleImpl:SELECT {u.*} FROM _Role {u} INNER JOIN _Role_id i ON(i._role_INTERNAL_ID=u._INTERNAL_ID) WHERE i._root='2.16.840.1.113883.19.3.2410' AND i._extension='gschadow'", "sql")
	};

	public static void main(String args[]) throws Exception {
		HQLInteractive test = new HQLInteractive();
		test.setUp();
		//test.test_commands();
		test.test_interactive();
		test.tearDown();
	}
	
	public void setUp() { }
	
	public void tearDown() { }
	
	static class Command {
		String _command;
		String _comment;
		Object _param;
		Type _paramType;
		public String getCommand() { return _command; }
		public String getComment() { return _comment; }
		public Object getParam() { return _param; }
		public Command(String command, String comment, Object param, Type paramType) {
			_command = command;
			_comment = comment;
			_param = param;
			_paramType = paramType;
		}
		public Command(String command, String comment) {
			this(command, comment, null, null);
		}
		public void execute() {
			System.out.println("// " + _comment + "\n");
			if(_param != null) {
				HQLInteractive.execute(_command,_param,_paramType);
			} else {
				HQLInteractive.execute(_command);
			}
		}
	}
	
	public void test_commands() {
		for(int i = 0; i < _commands.length; i++) {
			System.out.println("\n\n\n// " + (i+1));
			_commands[i].execute();
		}
	}
	
	public void test_interactive() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("hql-interactive> ");
			System.out.flush();
			String commandLine = input.readLine();
			if(commandLine == null || commandLine.equals("quit"))
				return;
			if(commandLine == null || commandLine.equals("help")) {
				System.err.println("HQL Interactive lets you query HQL and Hibernate SQL queries.");
				System.err.println("Type one query per line (use some external editor to get it all on one single line).");
				System.err.println("SQL queries start with '@<alias>:<class-fqn> <query>', also on a single line. Example '@r:my.MyThing FROM {r.*} MyThing r'");
				System.err.println("Type 'quit' to exit.");
				System.err.println("Type 'help' to display this help.");
			} else
				execute(commandLine);
		}
	}
	
	static final Matcher matcher = Pattern.compile("@([^:]+):([^:]+):(.*)").matcher("");
	
	static void execute(String command) {
		System.err.println("Executing: " + command);
		try {
			matcher.reset(command);
			if(matcher.matches())
				printResults(((SQLQuery)Persistence.instance().instance().createSQLQuery(matcher.group(3))).addEntity(matcher.group(1),Class.forName(matcher.group(2))));
			else
				printResults(Persistence.instance().instance().createHQLQuery(command));
		} catch(Exception x) {
			System.out.println(x);
		}
		Persistence.instance().instance().close();
	}
	
	static void execute(String command, Object param, Type type) {
		System.out.println("\n>"+command+" with param="+param+"\n");
		Transaction transaction = Persistence.instance().instance().getSession().beginTransaction();
		
		try {
			matcher.reset(command);
			if(matcher.matches())
				printResults(Persistence.instance().instance().createSQLQuery(matcher.group(3)).setEntity(matcher.group(1),Class.forName(matcher.group(2))).setParameter(0,param,type));
			else
				printResults(Persistence.instance().instance().createHQLQuery(command).setParameter(0,param,type));
		} catch(Exception x) {
			System.out.println(x);
		}
		
		transaction.rollback();
		Persistence.instance().instance().close();
	}
	
	static void printResults(Query query) throws Exception {
		int i = 0;
		for(Object result : query.list()) {
			System.out.println(Integer.toString(i++)+":"+result);
		}
		System.err.println(Integer.toString(i) + " results found");
	}
}
