package studentPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.cj.xdevapi.Statement;

public class Delete implements ActionListener {

    JFrame frame;
    JLabel headingLabel, rollLabel;
    JTextField rollTextField;
    JButton deleteButton,btnBack;

    Delete() {
        frame = new JFrame("Delete Student");
        frame.setSize(400, 250); 
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 204, 204));
        frame.setLayout(null);

        headingLabel = new JLabel("Delete Student Details");
        headingLabel.setForeground(new Color(0, 102, 204));
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBounds(50, 20, 300, 30);
        frame.add(headingLabel);

        rollLabel = new JLabel("Roll:");
        rollLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        rollLabel.setBounds(50, 70, 100, 25);
        frame.add(rollLabel);

        rollTextField = new JTextField();
        rollTextField.setBounds(160, 70, 180, 30);
        rollTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(rollTextField);

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(255, 51, 51)); 
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteButton.addActionListener(this);
        deleteButton.setBounds(160, 120, 100, 35);
        frame.add(deleteButton);
        
        btnBack = new JButton("<HTML><U>Back</U></HTML>");
        btnBack.setBounds(10, 160, 60, 20); 
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
        if (e.getSource() == deleteButton) {
            try {
                String roll = rollTextField.getText();
                if (roll.isEmpty())
                {
                     JOptionPane.showMessageDialog(frame, "Please enter Roll Number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Connection con = DBConnection.getConnection();
                    
                    int rollno= Integer.parseInt(rollTextField.getText());
                    String sql = "DELETE FROM STUDENT_INFO WHERE ROLL = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1,rollno);
                    
                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Student record deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No student found with this ID.");
                    }

                    pst.close();
                    con.close();
                    rollTextField.setText(""); 
                }
               
            }catch(Exception e1) {
                System.out.print(e1);
            }
            
        }
        else if (e.getSource() == btnBack) {
            frame.dispose();
            new Dashboard();
        }
    }

    public static void main(String args[]) {
        new Delete();
    }
}