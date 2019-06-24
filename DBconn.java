package bachelor_project;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {
	static Connection Conn;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (Conn == null) // 연결이 되어 있지 않다면2
		{
			String url = "jdbc:mysql://localhost:3306/bachelor";
			String user = "root";
			String pwd = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			Conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("동기화 완료");
		}
		return Conn;
	}
	// 메소드 오버로딩
	public static Connection getConnection(String url, String user, String pwd)throws ClassNotFoundException, SQLException
	{
		if (Conn == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			Conn = DriverManager.getConnection(url, user, pwd);
		}
		return Conn;
	}

	public void close() throws SQLException
	{
		if (Conn != null)
		{
			if (!Conn.isClosed())
				Conn.close();
		}
		Conn = null;

	}

}