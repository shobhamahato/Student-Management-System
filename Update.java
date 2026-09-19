package studentPack;

import java.awt.Color;
import java.sql.Statement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.SwingConstants;

public class Update implements ActionListener {

    JFrame frame;
    JLabel headingLabel;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton btnUpdate, btnSearch,btnBack;
    JRadioButton rbMale, rbFemale;
    ButtonGroup bg;
    JTextArea ta, ta2;
    String studentId;

    Update() {
        frame = new JFrame("Update Student Form");
        frame.setSize(500, 570); 
        frame.setLocation(200, 100);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        frame.getContentPane().setBackground(new Color(255, 204, 204));
        
        headingLabel = new JLabel("Update Student Details");
        headingLabel.setForeground(new Color(0, 102, 204)); 
        headingLabel.setBounds(100, 10, 300, 30); 
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); 
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        frame.add(headingLabel);

        l1 = new JLabel("Name");
        l1.setBounds(20, 50, 100, 25);
        l1.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l1);

        tf1 = new JTextField();
        tf1.setBounds(130, 50, 300, 30);
        tf1.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(tf1);

        l2 = new JLabel("Roll");
        l2.setBounds(20, 90, 100, 25);
        l2.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l2);

        tf2 = new JTextField();
        tf2.setBounds(130, 90, 300, 30);
        tf2.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(tf2);

        l3 = new JLabel("Mobile");
        l3.setBounds(20, 130, 100, 25);
        l3.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l3);

        tf3 = new JTextField();
        tf3.setBounds(130, 130, 300, 30);
        tf3.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(tf3);

        l4 = new JLabel("Email");
        l4.setBounds(20, 170, 100, 25);
        l4.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l4);

        tf4 = new JTextField();
        tf4.setBounds(130, 170, 300, 30);
        tf4.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(tf4);

        l5 = new JLabel("Address");
        l5.setBounds(20, 210, 100, 25);
        l5.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l5);

        ta = new JTextArea();
        ta.setBounds(130, 210, 300, 80);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1); 
        ta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), border));
        ta.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(ta);

        l6 = new JLabel("Gender");
        l6.setBounds(20, 310, 100, 25);
        l6.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l6);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(130, 310, 100, 25);
        rbMale.setFont(new Font("Arial", Font.PLAIN, 14));
        rbMale.setBackground(new Color(240, 240, 240)); 
        frame.add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(240, 310, 100, 25);
        rbFemale.setFont(new Font("Arial", Font.PLAIN, 14));
        rbFemale.setBackground(new Color(240, 240, 240));
        frame.add(rbFemale);

        bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        l7 = new JLabel("CGPA");
        l7.setBounds(20, 350, 100, 25);
        l7.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(l7);

        ta2 = new JTextArea();
        ta2.setBounds(130, 350, 300, 40);
        ta2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), border));
        ta2.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(ta2);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(180, 410, 120, 35); 
        btnUpdate.setBackground(new Color(51, 153, 255));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnUpdate.addActionListener(this);
        frame.add(btnUpdate);

        btnBack = new JButton("<HTML><U>Back</U></HTML>");
        btnBack.setBounds(10, 500, 60, 20); 
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
        if (e.getSource() == btnUpdate) {
            try {
                Connection con = DBConnection.getConnection();
                
                String name = tf1.getText();
                String rollStr = tf2.getText();
                String mobileStr = tf3.getText();
                String email = tf4.getText();
                String address = ta.getText();
                String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";
                String cgpaStr = ta2.getText();
                
                int roll = Integer.parseInt(rollStr);
                long mobile = Long.parseLong(mobileStr);
                double cgpa = Double.parseDouble(cgpaStr);
                
                String qry = "UPDATE STUDENT_INFO SET name='" + name + "', mobile=" + mobile + ", email='" + email + "', address='" + address + "', gender='" + gender + "', cgpa=" + cgpa + " WHERE roll=" + roll;

                Statement smt = (Statement) con.createStatement();
                int i = ((java.sql.Statement) smt).executeUpdate(qry);
                if(i>0) {
                }
              
                JOptionPane.showMessageDialog(frame, i+ " Details Updated");
            }catch(Exception e1) {
                System.out.print(e1);
            } 
        }
        else if (e.getSource() == btnBack) {
            frame.dispose();
            new Dashboard();
        }
    }

    private void clearFields() {
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        ta.setText("");
        bg.clearSelection();
        ta2.setText("");
        tf2.setEditable(true);
    }

    public static void main(String args[]) {
        new Update();
    }
}