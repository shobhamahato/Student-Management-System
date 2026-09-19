package studentPack;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class LoginPage implements ActionListener {

    JFrame frame;
    JLabel headingLabel, userIdLabel, passwordLabel;
    JTextField userIdField;
    JPasswordField passwordField;
    JButton loginButton;
    JPanel buttonPanel;
    
    LoginPage() {
        frame = new JFrame("Admin Login");
        frame.setSize(400, 300);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 204, 204));
        frame.setLayout(null);

        headingLabel = new JLabel("Admin Login");
        headingLabel.setForeground(new Color(0, 102, 204));
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBounds(100, 20, 200, 30);
        frame.add(headingLabel);

        userIdLabel = new JLabel("User Name:");
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userIdLabel.setBounds(50, 80, 100, 25);
        frame.add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(160, 80, 180, 30);
        userIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(userIdField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(50, 130, 100, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 130, 180, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        loginButton.addActionListener(this);
        loginButton.setBounds(160, 200, 100, 35);
        frame.add(loginButton);

        frame.setVisible(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                Connection con = DBConnection.getConnection();
                
                String NAME =userIdField.getText();
                int PASSWORD=Integer.parseInt(passwordField.getText());
                
                String query = "SELECT * FROM LOGIN_INFO WHERE NAME = ? AND PASSWORD = ?";

                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, NAME);
                pst.setInt(2, PASSWORD);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    frame.dispose();
                    new Dashboard();
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed: Invalid credentials in admin table.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }

                rs.close();
                pst.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        new LoginPage();
    }
}