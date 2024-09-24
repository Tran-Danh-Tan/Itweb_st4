package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectSQL {
	private final String serverName = "localhost";
	private final String dbName = "ltwebst4";
	private final String portNumber = "53734";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "danhtan";
	
	public Connection getDBConnection() throws Exception{

		String url = "jdbc:sqlserver://"+serverName+":"+portNumber+"\\"+ instance +";databaseName="+dbName;
		
		if(instance == null || instance.trim().isEmpty())
			
			url = "jdbc:sqlserver://"+serverName+":"+portNumber+";databaseName="+dbName;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		return DriverManager.getConnection(url, userID, password);
			
			
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new DBConnectSQL().getDBConnection());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
