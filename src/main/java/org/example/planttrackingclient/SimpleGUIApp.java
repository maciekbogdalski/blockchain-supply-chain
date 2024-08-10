package org.example.planttrackingclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SimpleGUIApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Plant Tracking System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Main panel
            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new GridLayout(5, 1));

            // Buttons
            JButton createButton = new JButton("Create Plant Tracking");
            JButton updateButton = new JButton("Update Plant State");
            JButton transferButton = new JButton("Transfer Ownership");
            JButton queryStateButton = new JButton("Query Plant State");
            JButton queryHistoryButton = new JButton("Query Plant History");

            // Add buttons to panel
            panel.add(createButton);
            panel.add(updateButton);
            panel.add(transferButton);
            panel.add(queryStateButton);
            panel.add(queryHistoryButton);

            // Action listeners for buttons
            createButton.addActionListener((ActionEvent e) -> {
                // Call create plant tracking functionality
                JOptionPane.showMessageDialog(frame, "Create functionality placeholder");
            });

            updateButton.addActionListener((ActionEvent e) -> {
                // Call update plant state functionality
                JOptionPane.showMessageDialog(frame, "Update functionality placeholder");
            });

            transferButton.addActionListener((ActionEvent e) -> {
                // Call transfer ownership functionality
                JOptionPane.showMessageDialog(frame, "Transfer functionality placeholder");
            });

            queryStateButton.addActionListener((ActionEvent e) -> {
                // Call query plant state functionality
                JOptionPane.showMessageDialog(frame, "Query State functionality placeholder");
            });

            queryHistoryButton.addActionListener((ActionEvent e) -> {
                // Call query plant history functionality
                JOptionPane.showMessageDialog(frame, "Query History functionality placeholder");
            });

            // Display the window
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);
        });
    }
}

