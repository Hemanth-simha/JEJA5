package com.jspiders.jdbc.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class MyFirstJdbcProgram {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			//Load the Driver
			Driver driverref = new Driver();
			DriverManager.registerDriver(driverref);
			
			//Establish DB Connection via Driver
			String dburl = "jdbc:mysql://localhost:3306/jspiders?user=root&password=root";
			con = DriverManager.getConnection(dburl);
			
			//Issue SQL Query via Connection
			String query = " select * from studentsinfo ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			// Process the Result returned by SQL Query
			while(rs.next()) {
				int regNum = rs.getInt("regno");
				String fName = rs.getString("firstname");
				String mName = rs.getString("middlename");
				String lName = rs.getString("lastname");
				
				System.out.println("Regno : "+regNum);
				System.out.println("FirstName : "+fName);
				System.out.println("MiddleName : "+mName);
				System.out.println("LastName : "+lName);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// Closed all JDBC Objects
			try {
			      if(con!=null)
			      	{
			    	  con.close();
			      	}
			      if(stmt!=null) {
			    	  stmt.close();
			      }
			      if(rs!=null) {
			    	  rs.close();
			      }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}