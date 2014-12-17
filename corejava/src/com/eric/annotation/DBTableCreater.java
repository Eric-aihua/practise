package com.eric.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBTableCreater {
	static String	DRIVER	= "org.gjt.mm.mysql.Driver";
	static String	URL	   = "jdbc:mysql://localhost:3306/demo";
	static String	NAME	= "root";
	static String	PWD	   = "";
	
	public static void main(String[] args) throws Exception {
		/*
		 * CREATE TABLE member( number int, firstName varchar(30) , lastName
		 * varchar(30) , age int);
		 */
		String sql = getTableFromClass(Member.class);
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		statement.execute(sql);
		System.out.println(sql);
	}
	
	public static String getTableFromClass(Class c) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ");
		DBTable dbTable = (DBTable) c.getAnnotation(DBTable.class);
		sb.append(dbTable.name() + "(" + "\n");
		Field[] fields = c.getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			Field field = fields[j];
			Annotation[] anns = field.getAnnotations();
			if (anns != null) {
				if (anns.length == 0) {
					continue;
				} else {
					for (int i = 0; i < anns.length; i++) {
						if (anns[i] instanceof SQLString) {
							if (((SQLString) anns[i]).name() != null && !"".equals(((SQLString) anns[i]).name())) {
								sb.append(((SQLString) anns[i]).name());
							} else {
								sb.append(field.getName());
							}
							sb.append(" varchar(");
							sb.append(((SQLString) anns[i]).name() + ")");
						}
						if (anns[i] instanceof SQLInteger) {
							if (((SQLInteger) anns[i]).name() != null && !"".equals(((SQLInteger) anns[i]).name())) {
								sb.append(((SQLInteger) anns[i]).name());
							} else {
								sb.append(field.getName());
							}
							sb.append(" int ");
						}
						if (anns[i] instanceof Constraints) {
							addContrains(sb, (Constraints) anns[i]);
							
						}
					}
				}
			}
			if (j != fields.length - 1)
				sb.append(" ,\n");
		}
		sb.append(");");
		return sb.toString();
	}
	
	private static void addContrains(StringBuilder sb, Constraints ann) {
		if (ann.allowNull()) {
			sb.append(" NOT null ");
		}
		if (ann.primaryKey()) {
			sb.append(" primary Key ");
		}
		if (ann.unique()) {
			sb.append(" unique ");
		}
	}
	
	private static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, NAME, PWD);
		return connection;
	}
}
