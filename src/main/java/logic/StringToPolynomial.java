package logic;

import model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToPolynomial {
    public static Polynomial parse(String polynomial) {
        Polynomial result = new Polynomial();
        Pattern pattern = Pattern.compile("((?:[+-]?\\d*\\.?\\d*)?)(x(\\^(\\d+))?)?");
        Matcher matcher = pattern.matcher(polynomial);

        boolean firstTerm = true;

        while (matcher.find()) {
            String coefficientInit = matcher.group(1);
            String exponentInit = matcher.group(4);

            double coeff;
            if (coefficientInit == null || coefficientInit.isEmpty()) {
                if (firstTerm) {
                    coeff = 1.0;
                } else {
                    coeff = 0.0;
                }
            } else if (coefficientInit.equals("+")) {
                coeff = 1.0;
            } else if (coefficientInit.equals("-")) {
                coeff = -1.0;
            } else {
                coeff = Double.parseDouble(coefficientInit);
            }

            int exp;
            if (exponentInit == null) {
                if (matcher.group(2) != null && matcher.group(2).equals("x")) {
                    exp = 1;
                } else {
                    exp = 0;
                }
            } else {
                exp = Integer.parseInt(exponentInit);
            }
            if (exp != 0 || coeff != 0) {
                result.addTerm(exp, coeff);
            }
            if (firstTerm) {
                firstTerm = false;
            }
        }

        return result;
    }
}