import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class AllMethods {
	
	Scanner scanner = new Scanner(System.in);
	
	public void createTable(Connection connection,Statement statement) throws SQLException{
		String preparedStatement = "";
		String input;
		
		while(true){
			input = scanner.next();
			if(input.equals(");")){
				break;
			}else{
				preparedStatement += input + " ";
			}
		}
		preparedStatement += ");";
		
		System.out.println(preparedStatement);
		statement.executeUpdate(preparedStatement);
		System.out.println("Table Created");
		
	}
	
	
	
	public void viewTable(Connection connection,Statement statement) throws SQLException{
		System.out.println("Which table you want to see?");
		String tableName = scanner.nextLine();
		String preparedStatement = "select * from ";
		preparedStatement += tableName;
		
		
		ResultSet resultSet = statement.executeQuery(preparedStatement);
		
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		
		//Column name illa 
		System.out.println(resultSetMetaData.toString());
		while(resultSet.next()){
			
			for(int i = 1; i < columnCount + 1; i++){
				if(i == 1){
					System.out.print(resultSet.getString(i) + "\t");
				}else if(i == 2){
					System.out.print( resultSet.getString(i) + "\t\t\t");
				}else{
					System.out.print(resultSet.getString(i));
				}
			}
			System.out.println();
		}
		
		
	}
	
	
	
	public void viewTableBasedOnColumnName(Connection connection,Statement statement) throws SQLException{
		
		System.out.println("Please enter Table name!");
		String tableName = scanner.nextLine();
		System.out.println("Attribute type? ");
		String columnName = scanner.nextLine();
		System.out.println("Attribute value? ");
		String columnValue = scanner.nextLine();
		
		ArrayList<String> displayColumns = new ArrayList();
		System.out.println("Hit $ at the end of listing all column names you want");
		String input;
		
		while(true){
			input = scanner.next();
			if(input.equals("$") || input.equals("*")){
				break;
			}else{
				displayColumns.add(input);
			}
		}
		String preparedStatement = "";
		if(input.equals("*")){
			preparedStatement = "select * from " + tableName + " where " + columnName + " = " + columnValue + " ;";
		}else{
			for(int i = 0; i < displayColumns.size(); i++){
				if(i == displayColumns.size()-1){
					preparedStatement += displayColumns.get(i);
				}else{
					preparedStatement += displayColumns.get(i) + " , ";
				}
			}
			preparedStatement += " from " + tableName + " where " + columnName + " = " + columnValue + " ;";
			
		}
		System.out.println(preparedStatement);
		
		ResultSet resultSet = statement.executeQuery(preparedStatement);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		
		while(resultSet.next()){
			for(int i = 1; i < columnCount + 1; i++){
				if(i == 1){
					System.out.print(resultSet.getString(i) + "\t");
				}else if(i == 2){
					System.out.print( resultSet.getString(i) + "\t\t\t");
				}else{
					System.out.print(resultSet.getString(i));
				}
			}
			System.out.println();
		}
		
	}


	public void deleteRecord(Connection connection,Statement statement) throws SQLException{
		System.out.println("table name?");
		String tableName = scanner.nextLine();
		System.out.println("column name?");
		String columnName = scanner.nextLine();
		System.out.println("column value");
		String columnValue = scanner.nextLine();
		String preparedStatement = "delete from ";
		preparedStatement += tableName + " where " + columnName + " = " + columnValue;
		statement.executeUpdate(preparedStatement);
		System.out.println("Row(s) deleted");
	}
	
	public void dropTable(Connection connection,Statement statement) throws SQLException{
		System.out.println("Table name?");
		String tableName = scanner.nextLine();
		statement.executeUpdate("drop table " + tableName);
	}
	
	public void insertRecord(Connection connection,Statement statement) throws SQLException{
		ArrayList<String> recordValues = new ArrayList<String>();
		
		String preparedStatement = "insert into ";
		System.out.println("Table name?");
		String tableName = scanner.nextLine();
		preparedStatement += tableName + " values ( ";
		String input;
		System.out.println("Enter record values, ');' to end:");
		while(true){
			input = scanner.next();
			if(input.equals(");")){
				break;
			}else{
				recordValues.add(input);
			}
		}
		for(int i = 0; i < recordValues.size(); i++){
			if(i == recordValues.size() - 1){
				preparedStatement += recordValues.get(i);
			}else{
				preparedStatement += recordValues.get(i) + " , ";
			}
		}
		preparedStatement += " );";
		System.out.println(preparedStatement);
		statement.executeUpdate(preparedStatement);
		System.out.println("1 Row inserted");
	}
	
	public void alterTable(Connection connection,Statement statement) throws SQLException, SQLSyntaxErrorException{
		System.out.println("Which table you want to alter?");
		String tableName = scanner.nextLine();
		System.out.println("How do you want to alter it? (add, alter, drop)");
		String operation = scanner.nextLine();
		
		String preparedStatement = "ALTER TABLE " + tableName + " ";
		
		if(operation.equals("add") || operation.equals("ADD") || operation.equals("Add")){
			
			preparedStatement += "add ";
			ArrayList<String> addList = new ArrayList<String>();
			String input;
			while(true){
				input = scanner.next();
				if(input.equals(";")){
					break;
				}else{
					addList.add(input);
				}
			}
			for(String s : addList){
				preparedStatement += s  + " ";
			}
			preparedStatement += ";";
			System.out.println(preparedStatement);
			statement.executeUpdate(preparedStatement);
			
			
		}else if(operation.equals("alter") || operation.equals("ALTER") || operation.equals("Alter")){
			
			preparedStatement += "alter column ";
			System.out.println("Which column do you want to alter?");
			String columnName = scanner.nextLine();
			System.out.println("What column-type do you wnat to give?");
			String columnType = scanner.nextLine();
			preparedStatement += columnName + " " + columnType + " ;";
			System.out.println(preparedStatement);
			statement.executeUpdate(preparedStatement);
			
			
		}else if(operation.equals("drop") || operation.equals("DROP") || operation.equals("Drop")){
			
			preparedStatement += "drop column ";
			System.out.println("Which column to drop?");
			String columnName = scanner.nextLine();
			preparedStatement += columnName + " ;";
			System.out.println(preparedStatement);
			statement.executeUpdate(preparedStatement);
			
		}
		System.out.println(preparedStatement);
		statement.executeUpdate(preparedStatement);
		System.out.println("Table " + tableName + " updated");
		
	}

}

