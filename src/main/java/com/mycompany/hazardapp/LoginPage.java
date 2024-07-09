/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.mycompany.hazardapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private JTextField userIdField;
    private JPasswordField passwordField;
    private JRadioButton normalUserButton;
    private JRadioButton adminButton;
    private ButtonGroup userTypeGroup;

    public LoginPage() {
        // Set up the frame
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // User ID label and text field
        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(userIdLabel, gbc);
        gbc.gridx = 1;
        add(userIdField, gbc);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // User type radio buttons
        JLabel userTypeLabel = new JLabel("User Type:");
        normalUserButton = new JRadioButton("Normal User");
        adminButton = new JRadioButton("Admin");
        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(normalUserButton);
        userTypeGroup.add(adminButton);
        normalUserButton.setSelected(true);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(userTypeLabel, gbc);
        gbc.gridx = 1;
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(normalUserButton);
        radioPanel.add(adminButton);
        add(radioPanel, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(loginButton, gbc);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    private void handleLogin() {
        String userId = userIdField.getText();
        char[] password = passwordField.getPassword();
        String userType = normalUserButton.isSelected() ? "Normal User" : "Admin";

        // Handle login logic here
        // For example, you can validate the user credentials and user type

        if (validateLogin(userId, password, userType)) {
            JOptionPane.showMessageDialog(this, "Login successful as " + userType);
            // Proceed to the main application screen
            // For example, open the Map class
            Map map = new Map();
            map.openMap();
            this.dispose(); // Close the login page
        } else {
            JOptionPane.showMessageDialog(this, "Invalid user ID or password");
        }
    }

    private boolean validateLogin(String userId, char[] password, String userType) {
        // Replace this with actual validation logic
        // For now, we just return true for demonstration purposes
        return true;
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}

