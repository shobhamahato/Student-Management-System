package studentPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;


public class Dashboard implements ActionListener {

    JFrame frame;
    JLabel headingLabel;
    JButton btnAdd, btnUpdate, btnView, btnDelete,btnBack;
    JPanel buttonPanel;

    Dashboard() {
        frame = new JFrame("Student Management Dashboard");
        frame.setSize(600, 400);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 204, 204));
        
        frame.setLayout(null); 

        headingLabel = new JLabel("Student Management System");
        headingLabel.setForeground(new Color(0, 102, 204));
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBounds(100, 20, 400, 30); 
        frame.add(headingLabel);

        btnAdd = new JButton("Add Student");
        btnAdd.setBackground(new Color(51, 153, 255));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAdd.addActionListener(this);
        btnAdd.setBounds(100, 100, 150, 40); 
        frame.add(btnAdd);

        btnUpdate = new JButton("Update Student");
        btnUpdate.setBackground(new Color(51, 153, 255));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnUpdate.addActionListener(this);
        btnUpdate.setBounds(350, 100, 150, 40); 
        frame.add(btnUpdate);

        btnView = new JButton("View Student");
        btnView.setBackground(new Color(51, 153, 255));
        btnView.setForeground(Color.WHITE);
        btnView.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnView.addActionListener(this);
        btnView.setBounds(100, 200, 150, 40); 
        frame.add(btnView);

        btnDelete = new JButton("Delete Student");
        btnDelete.setBackground(new Color(51, 153, 255));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnDelete.addActionListener(this);
        btnDelete.setBounds(350, 200, 150, 40); 
        frame.add(btnDelete);
        
        btnBack = new JButton("<HTML><U>Log out</U></HTML>");
		  btnBack.setBounds(480, 320, 100, 20);
		  btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		  btnBack.setForeground(Color.BLUE);
		  btnBack.setBorderPainted(false);
		  btnBack.setContentAreaFilled(false);
		  btnBack.setFocusPainted(false);
		  btnBack.setOpaque(false);
		  btnBack.addActionListener(this);
		  frame.add(btnBack);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
         frame.dispose();
         new AddStudent();
         
        } else if (e.getSource() == btnUpdate) {
         frame.dispose();
         new Update();
            
        } else if (e.getSource() == btnView) {
         frame.dispose();
         new view();
            
        } else if (e.getSource() == btnDelete) {
         frame.dispose();
         new Delete();
            
        }else if (e.getSource() == btnBack) {
         frame.dispose();
         new LoginPage();
            
        }
    }

    public static void main(String args[]) {
        new Dashboard();
    }
}
