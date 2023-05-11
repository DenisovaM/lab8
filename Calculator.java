package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
public class Calculator {
    public static void main(String[] args) {
        CalFrame frame = new CalFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class CalFrame extends JFrame {
    public CalFrame() {
        setTitle("");
        CalcPanel panel = new CalcPanel();
        add(panel);
        pack();
    }
}
class CalcPanel extends JPanel {
    private JButton out;
    private JPanel panel;
    private double result;
    private String oldCommand;

    public CalcPanel() {
        setLayout(new BorderLayout());
        result = 0;
        oldCommand = "=";
        start_operation = true;
        Font fontout = new Font("TimesRoman", Font.BOLD, 50);
        out = new JButton("0");
        out.setEnabled(false);
        add(out, BorderLayout.NORTH);
        out.setFont(fontout);
       
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5));
        newButton("7", OpOne);
        newButton("8", OpOne);
        newButton("9", OpOne);
        newButton("+", OpTwo);
        newButton("4", OpOne);
        newButton("5", OpOne);
        newButton("6", OpOne);
        newButton("-", OpTwo);
        newButton("1", OpOne);
        newButton("2", OpOne);
        newButton("3", OpOne);
        newButton("*", OpTwo);

        newButton("0", OpOne);
        newButton("C", OpOne);
        newButton("=", OpTwo);
        newButton("/", OpTwo);
        add(panel, BorderLayout.CENTER);
    }
    private void newButton(String label, ActionListener listener) {
        Font fontButton = new Font("TimesRoman", Font.BOLD, 20);
        JButton button = new JButton(label);
        button.addActionListener(listener);
        button.setFont(fontButton);
        panel.add(button);
    }
    
    
    public void operation(double x) {

        switch (oldCommand) {
            case "+":
                result += x;
                break;
            case "-":
                result -= x;
                break;
            case "*":
                result *= x;
                break;
            case "/":
                result /= x;
                break;
            case "=":
                result = x;
                break;
            default:
                error();
                break;
        }
        out.setText("" + result);
    }

}
