package studentPack;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage implements ActionListener {

    JFrame frame;
    JLabel headingLabel, welcomeLabel, instructionLabel;
    JButton startButton;

    StartPage() {

        frame = new JFrame("Student Management System");
        frame.setSize(600, 400);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 204, 204));
        frame.setLayout(null);

        headingLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        headingLabel.setForeground(new Color(0, 102, 204));
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBounds(100, 50, 400, 30);
        frame.add(headingLabel);

        welcomeLabel = new JLabel(
                "Welcome to the Student Management System!"
        );
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(100, 100, 400, 25);
        frame.add(welcomeLabel);

        instructionLabel = new JLabel(
                "Click the button below to get started."
        );
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setBounds(100, 130, 400, 25);
        frame.add(instructionLabel);

        startButton = new JButton("Start");
        startButton.setBackground(new Color(51, 153, 255));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        startButton.addActionListener(this);
        startButton.setBounds(250, 200, 100, 40);
        frame.add(startButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {
            frame.dispose();
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        new StartPage();
    }
}