package model;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Double> termeni;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return Objects.equals(termeni, that.termeni);
    }
    @Override
    public int hashCode() {
        return Objects.hash(termeni);
    }
    public Polynomial() {
        termeni = new TreeMap<>();
    }
    public TreeMap<Integer, Double> getTermeni() {
        return termeni;
    }
    public int degree() {
        if (termeni.isEmpty()) {
            return 0;
        } else {
            int maxExponent = Integer.MIN_VALUE;
            for (int exponent : termeni.keySet()) {
                if (exponent > maxExponent) {
                    maxExponent = exponent;
                }
            }
            return maxExponent;
        }
    }
    public int leadingTermExponent() {
        if (termeni.isEmpty()) {
            return 0;
        } else {
            return degree();
        }
    }
    public boolean isZero() {
        return termeni.isEmpty();
    }
    public void addTerm(int exponent, double coefficient) {
        if (coefficient != 0) {
            termeni.put(exponent, termeni.getOrDefault(exponent, 0.0) + coefficient);
        }
    }
    public void sortPolynomialsDecreasingly() {
        TreeMap<Integer, Double> sortedPolynomials = new TreeMap<>(Comparator.reverseOrder());
        sortedPolynomials.putAll(termeni);
        termeni = sortedPolynomials;
    }
    private int maxExponent() {
        int maxExponent = 0;
        for (int exponent : termeni.keySet()) {
            maxExponent = Math.max(maxExponent, exponent);
        }
        return maxExponent;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = maxExponent(); i >= 0; i--) {
            double coefficient = termeni.getOrDefault(i, 0.0);
            if (coefficient != 0) {
                if (!sb.isEmpty() && coefficient > 0) {
                    sb.append(" + ");
                }

                if (coefficient < 0) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                }

                if (i == 0 || coefficient != 1) {
                    sb.append(coefficient);
                }

                if (i > 0) {
                    sb.append("x");
                    if (i != 1) {
                        sb.append("^").append(i);
                    }
                }
            }
        }

        if (sb.isEmpty()) {
            sb.append("0");
        }

        return sb.toString();
    }
}

