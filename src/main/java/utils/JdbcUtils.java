package utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;
import org.postgresql.util.PSQLException;

import entities.Person;
import entities.Table;

public class JdbcUtils {
	static private final String url = "jdbc:postgresql://localhost/";
	static private final String user = "postgres";
	static private final String password = "postgres";

	// add private static connection type

	/**
	 * Connect to the PostgreSQL database
	 *
	 * @return a Connection object
	 */
	static public Connection connect(String dbName) {
		// To singleton, use the static variable already created
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url + dbName, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	/**
	 * Create a database
	 * 
	 * @param conn
	 *            connection to database
	 * @param name
	 *            of the new database
	 */
	static public void createDatabase(Connection conn, String name) {
		Statement stmt = null;
		try {
			System.out.println("Creating database...");
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE " + name;
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
		} catch (PSQLException se) {
			System.out.println(se.getMessage());
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	/**
	 * Create a table
	 * 
	 * @param conn
	 *            connection to database
	 * @param classNotTheKeyWord
	 *            table that will be created
	 */
	static public void CreateTable(Connection conn, Class<? extends Table> classNotTheKeyWord) {
		Statement stmt = null;

		try {
			System.out.println("Creating Table...");
			stmt = conn.createStatement();
			StringBuilder str = new StringBuilder();

			int i = 0;
			str.append("create Table " + classNotTheKeyWord.getSimpleName() + " (");
			for (Field x : classNotTheKeyWord.getDeclaredFields()) {
				if (i++ == classNotTheKeyWord.getDeclaredFields().length - 1) {
					str.append(x.getName() + " " + GenerateData.convertDataTypeJavaSql(x.getType().getSimpleName())
							+ ");");
				} else {
					if (x.getName().contains("id_"))
						str.append(x.getName() + " " + GenerateData.convertDataTypeJavaSql(x.getType().getSimpleName())
								+ " NOT NULL Primary key,");
					else
						str.append(x.getName() + " " + GenerateData.convertDataTypeJavaSql(x.getType().getSimpleName())
								+ ",");
				}
			}
			stmt.executeUpdate(str.toString());
			System.out.println("Table created successfully...");
		} catch (PSQLException se) {
			System.out.println(se.getMessage());
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}

	/**
	 * Insert data into a table
	 * 
	 * @param conn
	 *            connection to database
	 * @param table
	 *            where data will be inserted
	 * @param data
	 *            to insert
	 */
	static public void insertData(Connection conn, Class<? extends Table> table, JSONObject data) {
		Statement stmt = null;

		try {
			System.out.println("inserting Record...");
			stmt = conn.createStatement();
			StringBuilder str = new StringBuilder();

			int i = 0;
			str.append("INSERT INTO " + table.getSimpleName() + " (");
			for (Field x : table.getDeclaredFields()) {
				if (i++ == table.getDeclaredFields().length - 1) {
					str.append(x.getName() + ")");
				} else {
					str.append(x.getName() + ",");
				}
			}
			str.append(" VALUES(");
			int j = 0;
			for (Field x : table.getDeclaredFields()) {
				if (j++ == table.getDeclaredFields().length - 1) {
					str.append("\'" + data.get(x.getName()) + "\'" + ");");
				} else {
					str.append("\'" + data.get(x.getName()) + "\'" + ",");
				}
			}
			stmt.executeUpdate(str.toString());
			System.out.println("Records inserted successfully...");
		} catch (PSQLException se) {
			System.out.println(se.getMessage());
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

}
