package com.eric.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableCreator {
    private static String URL = "jdbc:oracle:thin:@172.17.68.140:1521:ORCL";
    private static String NAME = "sunaihua";
    private static String PWD = "sunaihua";
    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static void main(String[] args) throws Exception {

        String c = "com.eric.annotation.Member";
        Class<?> cl = Class.forName(c);
        DBTable dbTable = cl.getAnnotation(DBTable.class);
        if (dbTable == null) {
            System.out.println("No DBTable annotations in class " + c);
        }
        String command = getTableSql(cl, dbTable);

        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        stmt.execute(command);
        System.out.println("Table Creation SQL for " + c + " is :\n" + command);
    }

    private static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, NAME, PWD);
        return con;
    }

    private static String getTableSql(Class<?> cl, DBTable dbTable) {
        String tableName = dbTable.name();
        // If the name is empty, use the Class name:
        if (tableName.length() < 1)
            tableName = cl.getName().toUpperCase();
        StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (Field field : cl.getDeclaredFields()) {
            String columnName = null;
            Annotation[] anns = field.getDeclaredAnnotations();
            if (anns.length < 1)
                continue; // Not a db table column
            if (anns[0] instanceof SQLInteger) {
                SQLInteger sInt = (SQLInteger) anns[0];
                // Use field name if name not specified
                if (sInt.name().length() < 1)
                    columnName = field.getName().toUpperCase();
                else
                    columnName = sInt.name();
                createCommand.append(columnName + " INT" + getConstraints(sInt.constraints()));
            }
            if (anns[0] instanceof SQLString) {
                SQLString sString = (SQLString) anns[0];
                // Use field name if name not specified.
                if (sString.name().length() < 1)
                    columnName = field.getName().toUpperCase();
                else
                    columnName = sString.name();
                createCommand.append(columnName + " VARCHAR(" + sString.value() + ")"
                        + getConstraints(sString.constraints()));
            }

            createCommand.append(",\n");
        }
        String command = createCommand.substring(0, createCommand.toString().length() - 2);
        command = command + ")";
        return command;
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if (con.primaryKey())
            constraints += " PRIMARY KEY";
        if (con.unique())
            constraints += " UNIQUE";
        return constraints;
    }
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
