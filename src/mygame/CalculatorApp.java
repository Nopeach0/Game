/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author behda
 */
public class CalculatorApp extends JFrame implements ActionListener{
    private final JTextField myTextField;
    private final JButton button0;
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JButton button5;
    private final JButton button6;
    private final JButton button7;
    private final JButton button8;
    private final JButton button9;
    private final JButton addition;
    private final JButton subtract;
    private final JButton multiply;
    private final JButton equal;
    private final JButton delete;
    
    public CalculatorApp(String title){
        super(title);
        getContentPane().setLayout(null);
        
        //Display the numbers Operations
        myTextField = new JTextField("");
        myTextField.setLocation(85, 71);
        myTextField.setBackground(Color.WHITE);
        myTextField.setSize(150, 50);
        add(myTextField);
        
        //Buttons in order
        button0 = new JButton("0");
        button0.setLocation(115, 465);
        button0.setSize(100, 50);
        add(button0);
        button1 = new JButton("1");
        button1.setLocation(15, 410);
        button1.setSize(100, 50);
        add(button1);
        button2 = new JButton("2");
        button2.setLocation(115, 410);
        button2.setSize(100, 50);
        add(button2);
        button3 = new JButton("3");
        button3.setLocation(215, 410);
        button3.setSize(100, 50);
        add(button3);
        button4 = new JButton("4");
        button4.setLocation(15, 355);
        button4.setSize(100, 50);
        add(button4);
        button5 = new JButton("5");
        button5.setLocation(115, 355);
        button5.setSize(100, 50);
        add(button5);
        button6 = new JButton("6");
        button6.setLocation(215, 355);
        button6.setSize(100, 50);
        add(button6);
        button7 = new JButton("7");
        button7.setLocation(15, 300);
        button7.setSize(100, 50);
        add(button7);
        button8 = new JButton("8");
        button8.setLocation(115, 300);
        button8.setSize(100, 50);
        add(button8);
        button9 = new JButton("9");
        button9.setLocation(215, 300);
        button9.setSize(100, 50);
        add(button9);
        
        //Addition, Subtraction, Multiplication
        addition = new JButton("+");
        addition.setLocation(15, 245);
        addition.setSize(100, 50);
        add(addition);
        subtract = new JButton("-");
        subtract.setLocation(115, 245);
        subtract.setSize(100, 50);
        add(subtract);
        multiply = new JButton("x");
        multiply.setLocation(215, 245);
        multiply.setSize(100, 50);
        add(multiply);
        
        //Add the equal button
        equal = new JButton("=");
        equal.setLocation(215, 465);
        equal.setSize(100, 50);
        add(equal);
        
        //Add the deleted button
        delete = new JButton("DELETE");
        delete.setLocation(15, 465);
        delete.setSize(100, 50);
        add(delete);
        
        //Get the Button number String with the Opertaion String and add it to the main JTextField
        //Number Buttons
        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        
        //Operation buttons
        addition.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
        delete.addActionListener(this);
        equal.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 570);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentText = myTextField.getText().trim();
        Object source = e.getSource();
        if(source == button0){
            myTextField.setText(currentText + "0");
        } else if (source == button1){
            myTextField.setText(currentText + "1");
        } else if (source == button2){
            myTextField.setText(currentText + "2");
        } else if (source == button3) {
            myTextField.setText(currentText + "3");
        } else if (source == button4) {
            myTextField.setText(currentText + "4");
        } else if (source == button5) {
            myTextField.setText(currentText + "5");
        } else if (source == button6) {
            myTextField.setText(currentText + "6");
        } else if (source == button7) {
            myTextField.setText(currentText + "7");
        } else if (source == button8) {
            myTextField.setText(currentText + "8");
        } else if (source == button9) {
            myTextField.setText(currentText + "9");
        } else if (source == addition) {
            myTextField.setText(currentText + "+");
        } else if (source == subtract) {
            myTextField.setText(currentText + "-");
        } else if (source == multiply) {
            myTextField.setText(currentText + "*");
        } else if (source == delete){
            myTextField.setText("");
        } else if (source == equal){
            if (currentText.contains("+")){
                String[] parts = currentText.split("\\+");
                if (parts.length == 2){
                    try {
                        int num1 = Integer.parseInt(parts[0]);
                        int num2 = Integer.parseInt(parts[1]);
                        int result = num1 + num2;
                        myTextField.setText(String.valueOf(result));
                        
                    } catch(NumberFormatException ex){
                        myTextField.setText("Error");
                    }
                } else {
                    myTextField.setText("Error");
                }
              
            } else if(currentText.contains("-")){
                String[] parts = currentText.split("\\-");
                if (parts.length == 2){
                    try {
                        int num1 = Integer.parseInt(parts[0]);
                        int num2 = Integer.parseInt(parts[1]);
                        int result = num1 - num2;
                        myTextField.setText(String.valueOf(result));
                    } catch (NumberFormatException ex){
                        myTextField.setText("Error");
                    }
                } else {
                    myTextField.setText("Error");
                }
            } else if (currentText.contains("*")){
                String[] parts = currentText.split("\\*");
                if (parts.length == 2){
                    try {
                        int num1 = Integer.parseInt(parts[0]);
                        int num2 = Integer.parseInt(parts[1]);
                        int result = num1 * num2;
                        myTextField.setText(String.valueOf(result));
                    } catch (NumberFormatException ex){
                        myTextField.setText("Error");
                    }
                } else {
                    myTextField.setText("Error");
                }
            }
        }
    }
    public static void main(String[] args) {
        CalculatorApp calculator = new CalculatorApp("CalculatorApp");
        calculator.setVisible(true);
    }
}
