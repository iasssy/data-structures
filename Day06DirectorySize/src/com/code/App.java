package com.code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class App {
    public static JFrame mainWindow;
    private JButton btnSelectDirectory;
    private JPanel panelMain;
    private JLabel lblPath;
    private JLabel lblSumNonRecursive;
    private JLabel lblSumRecursive;

    private File selectedDirectory;

    public App() {
        btnSelectDirectory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                // chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Choose directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                chooser.setAcceptAllFileFilterUsed(false);
                //
                if (chooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                    selectedDirectory = chooser.getSelectedFile();
                    lblPath.setText(selectedDirectory.toString());
                    double sizeNonRecKB = getDirSizeNonRecInKB(selectedDirectory);
                    lblSumNonRecursive.setText(String.format("%.2f", sizeNonRecKB));
                    double sizeRecKB = getDirSizeRecInKB(selectedDirectory);
                    lblSumRecursive.setText(String.format("%.2f", sizeRecKB));
                }
            }
        });
    }

    private double getDirSizeNonRecInKB(File dir) {
        long sum = 0L;
        File[] allItemsInDir = dir.listFiles();
        for (File file : allItemsInDir) {
            sum += file.length();
        }
        return sum / (double) 1024;
    }

    /**
     *
     *
     * @param dir
     * @return
     */
    private double getDirSizeRecInKB(File dir) {
        long sum = 0L;
        File[] allItemsInDir = dir.listFiles();
        // the directory is not null
        if (allItemsInDir != null) {
            for (File file : allItemsInDir) {
                sum += file.length();
                if (file.isDirectory()) {
                    // recursive
                    sum += getDirSizeRecInKB(file) * 1024;
                }
            }
        }
        return sum / (double) 1024;
    }

    public static void main(String[] args) {
        mainWindow = new JFrame("App");
        mainWindow.setContentPane(new App().panelMain);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);

    }
}
