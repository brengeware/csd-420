package fansapp;

import javax.swing.*;
import java.awt.*;

public class FanGUI extends JFrame {
    
    private FanDAO dao = new FanDAO();
    
    private JTextField idField = new JTextField(10);
    private JTextField firstField = new JTextField(15);
    private JTextField lastField = new JTextField(15);
    private JTextField teamField = new JTextField(15);
    private JLabel statusLabel = new JLabel(" ");
    
    public FanGUI() {
        setTitle("FanDB - Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));
        
        add(new JLabel("ID"));
        add(idField);
        add(new JLabel("First Name:"));
        add(firstField);
        add(new JLabel("Last Name:"));
        add(lastField);
        add(new JLabel("Favorite Team:"));
        add(teamField);
        
        JButton displayBtn = new JButton("Display");
        JButton updateBtn = new JButton("Update");
        
        displayBtn.addActionListener(e -> displayRecord());
        updateBtn.addActionListener(e -> updateRecord());
        
        add(displayBtn);
        add(updateBtn);
        add(statusLabel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void displayRecord() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Fan fan = dao.getFanByID(id);
            if (fan != null) {
                firstField.setText(fan.getFirstname());
                lastField.setText(fan.getLastname());
                teamField.setText(fan.getFavoriteteam());
                statusLabel.setText("Record Found!");
            } else {
                statusLabel.setText("ERROR - No record found for ID " + id);
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Make sure you enter a valid ID.");
        }
    }
    
    private void updateRecord() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Fan fan = new Fan(id, firstField.getText().trim(),
                    lastField.getText().trim(), teamField.getText().trim());
            boolean success = dao.updateFan(fan);
            statusLabel.setText(success ? "Record updated." : "Update Failed! ID not found.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Make sure you enter a valid ID.");
        }
    }
    
    //Run
    public static void main(String[] args) {
        new FanGUI();
    }
    
}
