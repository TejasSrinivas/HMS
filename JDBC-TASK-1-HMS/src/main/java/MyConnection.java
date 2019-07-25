import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyConnection {
	
	public void myConnection(Connection connection, Statement statement){
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jul2019", "root", "server123");
			
			if(connection != null){
				System.out.println("Connected Successfully!");
				statement = connection.createStatement();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
