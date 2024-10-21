package logic;

import model.Polynomial;

import java.util.TreeMap;
import java.util.Map;

public class Operations {
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        if(polynomial2 == null) {
            return polynomial1;
        }
        else if(polynomial1 == null) {
            return polynomial2;
        }
        result.getTermeni().putAll(polynomial1.getTermeni());

        for (Map.Entry<Integer, Double> term : polynomial2.getTermeni().entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();
            double existingCoefficient = result.getTermeni().getOrDefault(exponent, 0.0);
            result.getTermeni().put(exponent, existingCoefficient + coefficient);
        }
        removeZeroCoefficientTerms(result.getTermeni());
        result.sortPolynomialsDecreasingly();
        return result;
    }

    public static void removeZeroCoefficientTerms(TreeMap<Integer, Double> polynomials) {
        polynomials.entrySet().removeIf(entry -> entry.getValue()==0);
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();

        if(polynomial2 == null) {
            return polynomial1;
        }
        else if(polynomial1 == null) {
            return polynomial2;
        }

        result.getTermeni().putAll(polynomial1.getTermeni());

        for (Map.Entry<Integer, Double> term : polynomial2.getTermeni().entrySet()) {
            double existingCoefficient = result.getTermeni().getOrDefault(term.getKey(), 0.0);
            result.getTermeni().put(term.getKey(), existingCoefficient - term.getValue());
        }
        removeZeroCoefficientTerms(result.getTermeni());
        result.sortPolynomialsDecreasingly();
        return result;
    }

    public static Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2) {
        if(polynomial2 == null || polynomial1 == null) {
            return null;
        }

        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> term1 : polynomial1.getTermeni().entrySet()) {
            int exponent1 = term1.getKey();
            double coefficient1 = term1.getValue();

            for (Map.Entry<Integer, Double> term2 : polynomial2.getTermeni().entrySet()) {
                int exponent2 = term2.getKey();
                double coefficient2 = term2.getValue();

                int newExponent = exponent1 + exponent2;
                double newCoefficient = coefficient1 * coefficient2;

                double existingCoefficient = result.getTermeni().getOrDefault(newExponent, 0.0);
                result.getTermeni().put(newExponent, existingCoefficient + newCoefficient);
            }
        }
        result.sortPolynomialsDecreasingly();
        return result;
    }

    public static Polynomial derivative(Polynomial polynomial1) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> term : polynomial1.getTermeni().entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();

            if (exponent > 0) {
                double newCoefficient = exponent * coefficient;
                result.getTermeni().put(exponent - 1, newCoefficient);
            }
        }
        result.sortPolynomialsDecreasingly();
        return result;
    }

    public static Polynomial integrate(Polynomial polynomial1) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> term : polynomial1.getTermeni().entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();

            double newCoefficient = coefficient / (exponent + 1);
            result.getTermeni().put(exponent + 1, newCoefficient);
        }
        result.sortPolynomialsDecreasingly();
        return result;
    }

    public static Polynomial[] divide(Polynomial polynomial1, Polynomial polynomial2) {

        polynomial1.sortPolynomialsDecreasingly();
        polynomial2.sortPolynomialsDecreasingly();

        int maxDegree1 = polynomial1.leadingTermExponent();
        int maxDegree2 = polynomial2.leadingTermExponent();

        if (maxDegree1 < maxDegree2) {
            //System.out.println("Impartirea nu se poate efectua. Gradul polinomului 1 este mai mic decat gradul polinomului 2.");
            return null;
        }

        Polynomial cat = new Polynomial();
        Polynomial rest = new Polynomial();

        while (maxDegree1 >= maxDegree2) {
            int degree = maxDegree1 - maxDegree2;
            double coefficient = polynomial1.getTermeni().get(maxDegree1) / polynomial2.getTermeni().get(maxDegree2);

            cat.getTermeni().put(degree, coefficient);

            Polynomial step = new Polynomial();
            step.getTermeni().put(degree, coefficient);

            polynomial1 = subtract(polynomial1, multiply(polynomial2, step));
            polynomial1.sortPolynomialsDecreasingly();
            maxDegree1 = polynomial1.leadingTermExponent();
        }

        rest = polynomial1;
        Polynomial[] result = new Polynomial[2];
        result[0] = cat;
        result[1] = rest;
        return result;
    }
}
