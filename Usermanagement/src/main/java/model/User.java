package model;


import java.sql.*;

public class User {

	
	private static String url = "jdbc:mysql://localhost:3306/userpm";
	private static String userName = "root";
	private static String password = "1234";
	private static Connection con;
	
	//A common method to connect to the DB
	
	private static Connection connect()
	
	{ 
		
		try
		{   
			Class.forName("com.mysql.jdbc.Driver"); 
			//Provide the correct details: DBServer/DBName, user, password 
			
			con = DriverManager.getConnection(url,userName,password); 
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		
		return con; 
		}
	
		
		public String insertItem(String username, String insitute, String phone, String email, String password )
		
		{
			String output = "";
			try{ 
				Connection con = connect();
				if (con == null) 
				{return"Error while connecting to the database for inserting."; }
				// create a prepared statement
				
				String query = " insert into customer  (`id`,`username`,`insitute`,`phone`,`email`,`password`)"+ " values (?, ?, ?, ?, ?, ?)"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
			
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, username);
				preparedStmt.setString(3, insitute); 
				preparedStmt.setString(4, phone); 
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, password); 
				
				
				// execute the statement
				
				
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully"; 
				
			}
			catch (Exception e)
			{
				output = "Error while inserting the item."; 
				System.err.println(e.getMessage()); 
				} return output; 
				
		}	
				
				
		public String readItems() 
		{ 
			String output = ""; 
			
		try
		{ 
			Connection con = connect();
			
			if (con == null)
			{return"Error while connecting to the database for reading."; } 
			
			// Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>username</th><th>insitute</th>" +"<th>phone</th>" +  "<th>email</th>"+"<th>password</th>"+"<th>Update</th><th>Remove</th></tr>";    
			
			String query = "select * from customer";
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			
			while (rs.next()) 
			
			{
				String id = Integer.toString(rs.getInt("id"));
				String username = rs.getString("username"); 
				String insitute = rs.getString("insitute"); 
				String phone = rs.getString("phone"); 
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				
				
				// Add into the  table
				
				output += "<tr><td>" + username + "</td>";
				output += "<td>" + insitute + "</td>";
				output += "<td>" + phone + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" +password + "</td>";
				
				
				// buttons
				
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><form method='post' action='User.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='hidItemIDDelete' type='hidden' value='" + id 
						 + "'>" + "</form></td></tr>"; 
				
			} 
			con.close();
			
			// Complete the ht table
			
			output += "</table>";
			
		} 
		catch (Exception e)
		{
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
			}
		return output; 
		
		
		}
		
		
		
		public String updateItem(String id , String username, String institue, String phone, String email, String password)
		
		{
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				
				if (con == null)
				{return"Error while connecting to the database for updating."; }
				
				// create a prepared statement
				
				String query = "UPDATE customer SET username=?,institute=?,phone=?,email=?,password=? WHERE id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				
				preparedStmt.setString(1, username);
				preparedStmt.setString(2, institue); 
				preparedStmt.setString(3, phone); 
				preparedStmt.setString(4, email);
				preparedStmt.setString(5,password ); 
				
				
				
				// execute the statement
				
				preparedStmt.execute(); 
				con.close();
				
				output = "Updated successfully";
				
			} 
			
			catch (Exception e) 
			
			{ 
				output = "Error while updating the item."; 
				System.err.println(e.getMessage());
				
			} 
			
			return output; 
			
			}
		
		
		public String deleteItem(String id)
		
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				
				if (con == null) 
				
				{return"Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				
				String query = "delete from customer where id=?"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setInt(1, Integer.parseInt(id));
				
				// execute the statement
				
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully"; 
				
			} 
			
			catch (Exception e)
			
			{ 
				output = "Error while deleting the item.";
			    System.err.println(e.getMessage()); 
			    
			} 
			
			return output;
			
		}
			
		
		
	}
