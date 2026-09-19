package studentPack;
import java.sql.*;

public class CreateTable {

    public static void main(String[] args) {
        
        try {
               Class.forName("com.mysql.cj.jdbc.Driver");
              
               Connection con = DBConnection.getConnection();           
               String qry = "CREATE TABLE STUDENT_INFO(NAME VARCHAR(50),ROLL INT , MOBILE BIGINT(10),EMAIL VARCHAR(20),ADDRESS VARCHAR(50),GENDER VARCHAR(10),CGPA FLOAT(4,2))";
               
               Statement smt = con.createStatement();
               int i = smt.executeUpdate(qry);
               if(i==0) {
                System.out.println("Table Created");
               }
               
               con.close();
              } catch(ClassNotFoundException ce) {
               System.out.println(ce);
              } catch(SQLException se) {
               System.out.println(se);
              }
             }

}