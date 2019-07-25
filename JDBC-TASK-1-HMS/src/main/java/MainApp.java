import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainApp {
	
	static Connection connection;
	static Statement statement; 
	
	
	public static void main(String[] args) throws SQLException {

		MainApp app = new MainApp();
		AllMethods methods = new AllMethods();
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jul2019", "root", "server123");
			
			if(connection != null){
				System.out.println("Connected Successfully!");
				statement = connection.createStatement();
				System.out.println(connection);
				System.out.println(statement);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		DisplayOptions display = new DisplayOptions();
		display.displayOptions();
		Scanner scanner = new Scanner(System.in);
		while(true){
			
			String str = scanner.nextLine();
			System.out.println("You have chosen option :" + str);
			if(str.equalsIgnoreCase("exit")){
				break;
			}else{
				app.processRequest(str, connection, statement, methods);
			}
		}
		scanner.close();
	}
	
	public void processRequest(String request, Connection connection, Statement statement, AllMethods methods) throws SQLException{
		
		if(request.equals("1")){
			methods.insertRecord(connection,statement);
		}else if(request.equals("2")){
			methods.viewTable(connection,statement);
		}else if(request.equals("3")){
			methods.alterTable(connection, statement);
		}else if(request.equals("4")){
			methods.dropTable(connection, statement);
		}else if(request.equals("5")){
			methods.deleteRecord(connection, statement);
		}
	}
}
