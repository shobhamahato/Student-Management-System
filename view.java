package studentPack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class view {
 JFrame frame;
 JTable table;
 DefaultTableModel model;
 JScrollPane sp;
 
 view() {
  frame = new JFrame("	STUDENT DETAILS");
  frame.setSize(550, 400);
  frame.setLocation(200, 100);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  String[] colNames = { "NAME","ROLL","MOBILE","EMAIL","ADDRESS","GENDER","CGPA"};
  model = new DefaultTableModel();
  model.setColumnIdentifiers(colNames);
  
  try {
	  Connection con = DBConnection.getConnection();
      
	  String qry = "SELECT * FROM STUDENT_INFO";
	  Statement smt = con.createStatement();
	  ResultSet rs = smt.executeQuery(qry);
   
   while(rs.next()) {
    int roll = rs.getInt("ROLL");
    String name = rs.getString("NAME");
    long mobile = rs.getLong("MOBILE");
    String email = rs.getString("EMAIL");
   
    String address = rs.getString("ADDRESS");
    String gender = rs.getString("GENDER");
    float cgpa = rs.getFloat("CGPA");
    
    model.addRow(new Object[] {name,roll,mobile,email,address,gender,cgpa});
   }
   
  } catch (Exception e) {
	  System.out.print(e);
  }
  
  table = new JTable();
  table.setModel(model);
  
  sp = new JScrollPane(table);
  frame.add(sp);
  
  frame.setVisible(true);
 }
 
 public static void main(String[] args) {
  new view();
 }
}