package studentPack;

import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

public class AddStudent implements ActionListener {
    JFrame frame;
    JLabel headingLabel;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton btnAdd,btnBack;
    JRadioButton rbMale, rbFemale;
    ButtonGroup bg;
    JTextArea ta, ta2;

    AddStudent() {
        frame = new JFrame("Add Student Form");
        frame.setSize(450, 650);
        frame.setLocation(200, 50);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 204, 204));

        headingLabel = new JLabel("Add Student");
        headingLabel.setForeground(new Color(0, 102, 204));
        headingLabel.setBounds(150, 10, 200, 30);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(headingLabel);

        l1 = new JLabel("Name");
        l1.setBounds(10, 50, 200, 20);
        frame.add(l1);

        tf1 = new JTextField();
        tf1.setBounds(10, 75, 180, 30);
        frame.add(tf1);

        l2 = new JLabel("Roll");
        l2.setBounds(10, 110, 200, 20);
        frame.add(l2);

        tf2 = new JTextField();
        tf2.setBounds(10, 135, 180, 30);
        frame.add(tf2);

        l3 = new JLabel("Mobile");
        l3.setBounds(10, 170, 200, 20);
        frame.add(l3);

        tf3 = new JTextField();
        tf3.setBounds(10, 195, 180, 30);
        frame.add(tf3);

        l4 = new JLabel("Email");
        l4.setBounds(10, 230, 200, 20);
        frame.add(l4);

        tf4 = new JTextField();
        tf4.setBounds(10, 255, 180, 30);
        frame.add(tf4);

        l5 = new JLabel("Address");
        l5.setBounds(10, 290, 200, 20);
        frame.add(l5);

        ta = new JTextArea();
        ta.setBounds(10, 315, 200, 80);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        ta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), border));
        frame.add(ta);

        l6 = new JLabel("Gender");
        l6.setBounds(10, 410, 50, 30);
        frame.add(l6);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(70, 410, 80, 30);
        rbMale.setBackground(new Color(255, 204, 204));
        frame.add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(160, 410, 80, 30);
        rbFemale.setBackground(new Color(255, 204, 204));
        frame.add(rbFemale);

        bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        l7 = new JLabel("CGPA");
        l7.setBounds(10, 450, 80, 30);
        frame.add(l7);

        ta2 = new JTextArea();
        ta2.setBounds(10, 490, 180, 40);
        ta2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), border));
        frame.add(ta2);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(150, 550, 120, 35);
        btnAdd.setBackground(new Color(51, 153, 255));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAdd.addActionListener(this);
        frame.add(btnAdd);
        
        btnBack = new JButton("<HTML><U>Back</U></HTML>");
        btnBack.setBounds(10, 570, 60, 20); 
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

    public static void main(String args[]) {
        new AddStudent();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            frame.dispose();
            new Dashboard();
        }
        else if (e.getSource() == btnAdd) {
            String name = tf1.getText();
            String rollStr = tf2.getText();
            String mobileStr = tf3.getText();
            String email = tf4.getText();
            String address = ta.getText();
            String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";
            String cgpaStr = ta2.getText();

            if (name.isEmpty() || mobileStr.isEmpty() || address.isEmpty() || cgpaStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                return;
            }

            try {
                int roll = Integer.parseInt(rollStr);
                long mobile = Long.parseLong(mobileStr);
                double cgpa = Double.parseDouble(cgpaStr);

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO STUDENT_INFO(NAME, ROLL, MOBILE, EMAIL, ADDRESS, GENDER, CGPA) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setInt(2, roll);
                pstmt.setLong(3, mobile);
                pstmt.setString(4, email);
                pstmt.setString(5, address);
                pstmt.setString(6, gender);
                pstmt.setDouble(7, cgpa);

                int rows = pstmt.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(frame, "Student added successfully!");
                    tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText("");
                    ta.setText(""); ta2.setText(""); bg.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add student.");
                }

                con.close();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid number format for Roll, Mobile, or CGPA.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        }
    }
}