package org.example;

import GUI.Calculator;
import model.Polynomial;
import logic.StringToPolynomial;
import logic.Operations;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*Polynomial polinom1 = new Polynomial();
        polinom1 = StringToPolynomial.parse("2x^2+3x+1");
        Polynomial polinom2 = new Polynomial();
        polinom2 = StringToPolynomial.parse("4x^3+2x^2-x");
        System.out.println(polinom1.toString());
        System.out.println(polinom2.toString());
        Polynomial result = Operations.add(polinom1, polinom2);
        System.out.println(result.toString());*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calculator calculator = new Calculator();
                calculator.setVisible(true);
            }
        });
    }
}