package studentPack;
import java.sql.*;

public class ConnectionTest {

	 public static void main(String[] args) {
		  try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection con = DBConnection.getConnection();
		  
		   if(con!=null) {
		    System.out.println("Connected...");
		   }
		  } catch(ClassNotFoundException ce) {
		   System.out.println(ce);
		  } catch(SQLException se) {
		   System.out.println(se);
		  }
		 }
		}