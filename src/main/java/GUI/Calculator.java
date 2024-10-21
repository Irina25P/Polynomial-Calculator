package GUI;

import logic.Operations;
import logic.StringToPolynomial;
import model.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {
    private JTextField polynomial1Field;
    private JTextField polynomial2Field;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton derivativeButton;
    private JButton integrateButton;
    private JLabel resultLabel;

    public Calculator() {
        setTitle("Polynomial Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 220, 230));

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(255, 220, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        polynomial1Field = new JTextField(10);
        polynomial2Field = new JTextField(10);
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        derivativeButton = new JButton("Derivative");
        integrateButton = new JButton("Integrate");

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Polynomial 1:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(polynomial1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Polynomial 2:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(polynomial2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(addButton, gbc);

        gbc.gridx = 1;
        inputPanel.add(subtractButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(multiplyButton, gbc);

        gbc.gridx = 1;
        inputPanel.add(divideButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(derivativeButton, gbc);

        gbc.gridx = 1;
        inputPanel.add(integrateButton, gbc);

        // Panel for result
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setBackground(new Color(220, 230, 255));
        resultLabel = new JLabel("Result: ");
        resultPanel.add(resultLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> performOperation(Operation.ADDITION));
        subtractButton.addActionListener(e -> performOperation(Operation.SUBTRACTION));
        multiplyButton.addActionListener(e -> performOperation(Operation.MULTIPLICATION));
        divideButton.addActionListener(e -> performOperation(Operation.DIVISION));
        derivativeButton.addActionListener(e -> performOperation(Operation.DERIVATIVE));
        integrateButton.addActionListener(e -> performOperation(Operation.INTEGRATION));

        setSize(400, 250);

        setLocationRelativeTo(null);
    }

    private void performOperation(Operation operation) {
        String polynomial1 = polynomial1Field.getText();
        String polynomial2 = polynomial2Field.getText();

        Polynomial p1 = StringToPolynomial.parse(polynomial1);

        Polynomial p2 = null;
        if (!polynomial2.isEmpty()) {
            p2 = StringToPolynomial.parse(polynomial2);
        }

        Polynomial result = null;

        switch (operation) {
            case ADDITION:
                result = Operations.add(p1, p2);
                break;
            case SUBTRACTION:
                result = Operations.subtract(p1, p2);
                break;
            case MULTIPLICATION:
                result = Operations.multiply(p1, p2);
                if(result == null) {
                    resultLabel.setText("Result: " + 0);
                    return;
                }
                break;
            case DERIVATIVE:
                result = Operations.derivative(p1);
                break;
            case INTEGRATION:
                result = Operations.integrate(p1);
                resultLabel.setText("Result: " + result + " +C");
                return;
            case DIVISION:
                if (p2 == null || p2.isZero()) {
                    resultLabel.setText("Cannot divide by zero.");
                    return;
                }
                Polynomial[] divisionResult = Operations.divide(p1, p2);
                if (divisionResult != null) {
                    Polynomial cat = divisionResult[0];
                    Polynomial rest = divisionResult[1];
                    resultLabel.setText("Quotient: " + cat + ", Remainder: " + rest);
                } else {
                    resultLabel.setText("Division cannot be performed.");
                }
                return;
        }

        if (result != null) {
            resultLabel.setText("Result: " + result);
        } else {
            resultLabel.setText("Invalid input polynomial");
        }
    }

    enum Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, DERIVATIVE, INTEGRATION
    }
}