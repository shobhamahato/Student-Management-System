package studentPack;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminTable {

	public static void main(String[] args)
	{
		try {
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   Connection con = DBConnection.getConnection();		   
			   String qry = "CREATE TABLE LOGIN_INFO(NAME VARCHAR(50),PASSWORD INT(10))";
			   
			   Statement smt = con.createStatement();
			   int i = smt.executeUpdate(qry);
			   if(i==0) {
			    System.out.println("ADMIN Table Created");
			   }
			   
			   con.close();
			  } catch(ClassNotFoundException ce) {
			   System.out.println(ce);
			  } catch(SQLException se) {
			   System.out.println(se);
			  }
			 }

	}