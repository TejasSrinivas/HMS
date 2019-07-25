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
		String str = scanner.nextLine();
		double input = Double.parseDouble(str);
		
		if(input == Math.round(input)){
			System.out.println("Chosen Input: " + Math.round(input));
		}else{
			System.out.println("Chosen Input: " + input);
		}
		
		app.processRequest(input, connection, statement, methods);
		scanner.close();
	}
	
	public void processRequest(double request, Connection connection, Statement statement, AllMethods methods) throws SQLException{
		
		if(request == 1.0){
			methods.insertRecord(connection,statement);
		}else if(request == 2.0){
			methods.viewTable(connection,statement);
		}else if(request == 3.0){
			methods.alterTable(connection, statement);
		}else if(request == 4.0){
			methods.dropTable(connection, statement);
		}else if(request == 5.0){
			methods.deleteRecord(connection, statement);
		}
	}
}
