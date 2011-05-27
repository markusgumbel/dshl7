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
* The Initial Developer of the Original Code is .
* Portions created by Initial Developer are Copyright (C) 2002-2006
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*/
package org.hl7.hibernate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.util.StringHelper;

/**
 * An improved naming strategy that just adds an "_" to everything :)
 * @see DefaultNamingStrategy the default strategy
 * @author Matt Carlson
 */
public class ReservedWordAvoidanceNamingStrategy implements NamingStrategy, Serializable {

  private static final Set<String> RESERVED_WORDS = new HashSet<String>(Arrays.asList(new String[] {"ABSOLUTE", "ACTION", "ADD",
    "AFTER", "ALL", "ALLOCATE", "ALTER", "AND", "ANY", "ARE", "ARRAY", "AS", "ASC", "ASENSITIVE", "ASSERTION",
    "ASYMMETRIC", "AT", "ATOMIC", "AUTHORIZATION", "AVG", "BEFORE", "BEGIN", "BETWEEN", "BIGINT", "BINARY", "BIT",
    "BIT_LENGTH", "BLOB", "BOOLEAN", "BOTH", "BREADTH", "BY", "CALL", "CALLED", "CASCADE", "CASCADED", "CASE", "CAST",
    "CATALOG", "CHAR", "CHARACTER", "CHARACTER_LENGTH", "CHAR_LENGTH", "CHECK", "CLOB", "CLOSE", "COALESCE", "COLLATE",
    "COLLATION", "COLUMN", "COMMIT", "CONDITION", "CONNECT", "CONNECTION", "CONSTRAINT", "CONSTRAINTS", "CONSTRUCTOR",
    "CONTAINS", "CONTINUE", "CONVERT", "CORRESPONDING", "COUNT", "CREATE", "CROSS", "CUBE", "CURRENT", "CURRENT_DATE",
    "CURRENT_DEFAULT_TRANSFORM_GROUP", "CURRENT_PATH", "CURRENT_ROLE", "CURRENT_TIME", "CURRENT_TIMESTAMP",
    "CURRENT_TRANSFORM_GROUP_FOR_TYPE", "CURRENT_USER", "CURSOR", "CYCLE", "DATA", "DATE", "DAY", "DEALLOCATE", "DEC",
    "DECIMAL", "DECLARE", "DEFAULT", "DEFERRABLE", "DEFERRED", "DELETE", "DEPTH", "DEREF", "DESC", "DESCRIBE",
    "DESCRIPTOR", "DETERMINISTIC", "DIAGNOSTICS", "DISCONNECT", "DISTINCT", "DO", "DOMAIN", "DOUBLE", "DROP", "DYNAMIC",
    "EACH", "ELEMENT", "ELSE", "ELSEIF", "END", "EQUALS", "ESCAPE", "EXCEPT", "EXCEPTION", "EXEC", "EXECUTE", "EXISTS",
    "EXIT", "EXTERNAL", "EXTRACT", "FALSE", "FETCH", "FILTER", "FIRST", "FLOAT", "FOR", "FOREIGN", "FOUND", "FREE",
    "FROM", "FULL", "FUNCTION", "GENERAL", "GET", "GLOBAL", "GO", "GOTO", "GRANT", "GROUP", "GROUPING", "HANDLER",
    "HAVING", "HOLD", "HOUR", "IDENTITY", "IF", "IMMEDIATE", "IN", "INDEX", "INDICATOR", "INITIALLY", "INNER", "INOUT", "INPUT",
    "INSENSITIVE", "INSERT", "INT", "INTEGER", "INTERSECT", "INTERVAL", "INTO", "IS", "ISOLATION", "ITERATE", "JOIN",
    "KEY", "LANGUAGE", "LARGE", "LAST", "LATERAL", "LEADING", "LEAVE", "LEFT", "LEVEL", "LIKE", "LOCAL", "LOCALTIME",
    "LOCALTIMESTAMP", "LOCATOR", "LOOP", "LOWER", "MAP", "MATCH", "MAX", "MEMBER", "MERGE", "METHOD", "MIN", "MINUTE",
    "MODIFIES", "MODULE", "MONTH", "MULTISET", "NAMES", "NATIONAL", "NATURAL", "NCHAR", "NCLOB", "NEW", "NEXT", "NO",
    "NONE", "NOT", "NULL", "NULLIF", "NUMERIC", "OBJECT", "OCTET_LENGTH", "OF", "OLD", "ON", "ONLY", "OPEN", "OPTION",
    "OR", "ORDER", "ORDINALITY", "OUT", "OUTER", "OUTPUT", "OVER", "OVERLAPS", "PAD", "PARAMETER", "PARTIAL",
    "PARTITION", "PATH", "POSITION", "PRECISION", "PREPARE", "PRESERVE", "PRIMARY", "PRIOR", "PRIVILEGES", "PROCEDURE",
    "PUBLIC", "RANGE", "READ", "READS", "REAL", "RECURSIVE", "REF", "REFERENCES", "REFERENCING", "RELATIVE", "RELEASE",
    "REPEAT", "RESIGNAL", "RESTRICT", "RESULT", "RETURN", "RETURNS", "REVOKE", "RIGHT", "ROLE", "ROLLBACK", "ROLLUP",
    "ROUTINE", "ROW", "ROWS", "SAVEPOINT", "SCHEMA", "SCOPE", "SCROLL", "SEARCH", "SECOND", "SECTION", "SELECT",
    "SENSITIVE", "SESSION", "SESSION_USER", "SET", "SETS", "SIGNAL", "SIMILAR", "SIZE", "SMALLINT", "SOME", "SPACE",
    "SPECIFIC", "SPECIFICTYPE", "SQL", "SQLCODE", "SQLERROR", "SQLEXCEPTION", "SQLSTATE", "SQLWARNING", "START",
    "STATE", "STATIC", "SUBMULTISET", "SUBSTRING", "SUM", "SYMMETRIC", "SYSTEM", "SYSTEM_USER", "TABLE", "TABLESAMPLE",
    "TEMPORARY", "THEN", "TIME", "TIMESTAMP", "TIMEZONE_HOUR", "TIMEZONE_MINUTE", "TO", "TRAILING", "TRANSACTION",
    "TRANSLATE", "TRANSLATION", "TREAT", "TRIGGER", "TRIM", "TRUE", "UNDER", "UNDO", "UNION", "UNIQUE", "UNKNOWN",
    "UNNEST", "UNTIL", "UPDATE", "UPPER", "USAGE", "USER", "USING", "VALUE", "VALUES", "VARCHAR", "VARYING", "VIEW",
    "WHEN", "WHENEVER", "WHERE", "WHILE", "WINDOW", "WITH", "WITHIN", "WITHOUT", "WORK", "WRITE", "YEAR", "ZONE"}));

  /**
   * The singleton instance
   */
  public static final NamingStrategy INSTANCE = new ReservedWordAvoidanceNamingStrategy();

  private static final String CONF_DATABASE = System.getProperty("org.hl7.hibernate.database","hsqldb");

	public static final NamingStrategy instance() {
		if(CONF_DATABASE.equals("oracle"))
			return OracleNamingStrategy.INSTANCE;
		else
			return INSTANCE;
	}

  protected ReservedWordAvoidanceNamingStrategy() {
      // for overriding only
  }

  public String classToTableName(String className) {
    return avoidReservedWord(StringHelper.unqualify(className));
  }

  public String propertyToColumnName(String propertyName) {
    return avoidReservedWord(propertyName);
  }

  public String tableName(String tableName) {
    return avoidReservedWord(tableName);
  }

  public String columnName(String columnName) {
    return avoidReservedWord(columnName);
  }

	// gone in 3.2
  public String propertyToTableName(String className, String propertyName) {
    return avoidReservedWord(StringHelper.unqualify(className) + capitalize(propertyName));
  }

	// new in 3.2
	/**
	 * Return the unqualified property name, not the best strategy but a backward compatible one
	 */
	public String collectionTableName(
			String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable,
			String propertyName
	) {
		return tableName( ownerEntityTable + capitalize(propertyToColumnName(propertyName)) );
	}

	/**
	 * Return the argument
	 */
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return columnName( joinedColumn );
	}

	public String foreignKeyColumnName(
			String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName
	) {
		String header = propertyName != null ? StringHelper.unqualify( propertyName ) : propertyTableName;

		if (header == null) 
			throw new IllegalArgumentException("NammingStrategy not properly filled");

		return columnName( header ); //+ "_" + referencedColumnName not used for backward compatibility
	}

	public String logicalColumnName(String columnName, String propertyName) {
		return StringHelper.isNotEmpty( columnName ) ? columnName : StringHelper.unqualify( propertyName );
	}

	public String logicalCollectionTableName(String tableName,
											 String ownerEntityTable, String associatedEntityTable, String propertyName
	) {
		if ( tableName != null ) {
			return tableName(tableName);
		}
		else {
			//use of a stringbuffer to workaround a JDK bug
			return tableName(ownerEntityTable +
											 capitalize(
																	associatedEntityTable != null ?
																	associatedEntityTable :
																	StringHelper.unqualify( propertyName )
																	));
		}
	}

	public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
		return columnName(StringHelper.isNotEmpty( columnName ) ? columnName : propertyName + capitalize(referencedColumn));
	}


	// helper stuff

	private final static String capitalize(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	protected String avoidReservedWord(String s) {
		return RESERVED_WORDS.contains(s.toUpperCase()) ? s + "_" : s;
	}
}
