package com.jspiders.jdbc.first;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class MyThirdJdbcProgram {

	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\J2ee Software\\MyFolder\\prop.properties");
		FileReader reader = new FileReader(file);
		Properties prop = new Properties();
		prop.load(reader);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Driver driverref = new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl = prop.getProperty("dburl");
			//System.out.println(dburl);
			con = DriverManager.getConnection(dburl, prop);
			
			String query = " update studentsinfo "
					+ " set middlename = ? "
					+ " where regno = ? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,args[0]);
			pstmt.setInt(2,Integer.parseInt(args[1]));
			int res= pstmt.executeUpdate();
			
			if(res!=0) {
				System.out.println("Record Updated");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(con!=null) {
					con.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			
		}
		
	}

}
