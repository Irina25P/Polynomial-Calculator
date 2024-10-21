import logic.Operations;
import logic.StringToPolynomial;
import model.Polynomial;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Tests {
    private static Integer numberOfTests = 0;
    private static Integer numberOfTestsPassed = 0;
    private static Operations operations;

    @BeforeClass
    public static void init() {
        System.out.println("Initialize Operations");
        operations = new Operations();
    }

    @AfterClass
    public static void finalMethod() {
        operations = null;
        System.out.println(numberOfTests + " has been executed and only " + numberOfTestsPassed + " has passed");
    }
    @Before
    public void increment() {
        numberOfTests++;
    }
    @After
    public void afterEachTest() {
        System.out.println("Test Finished");
    }
    @Test
    public void testAddition() {
        Polynomial p1 = StringToPolynomial.parse("2x^2+3x+1");
        Polynomial p2 = StringToPolynomial.parse("4x^3+2x^2-x");
        Polynomial expected = StringToPolynomial.parse("4x^3+4x^2+2x+1");
        assertEquals(expected, Operations.add(p1, p2));
        numberOfTestsPassed++;
    }
    @Test
    public void testSubtraction() {
        Polynomial p1 = StringToPolynomial.parse("2x^2+3x+1");
        Polynomial p2 = StringToPolynomial.parse("4x^3+2x^2-x");
        Polynomial expected = StringToPolynomial.parse("-4x^3+4x+1");
        assertEquals(expected, Operations.subtract(p1, p2));
        numberOfTestsPassed++;
    }
    @Test
    public void testMultiplication() {
        Polynomial p1 = StringToPolynomial.parse("2x+1");
        Polynomial p2 = StringToPolynomial.parse("3x-2");
        Polynomial expected = StringToPolynomial.parse("6x^2-x-2");
        assertEquals(expected, Operations.multiply(p1, p2));
        numberOfTestsPassed++;
    }
    @Test
    public void testDivision() {
        Polynomial p1 = StringToPolynomial.parse("x^3-2x^2+6x-5");
        Polynomial p2 = StringToPolynomial.parse("x^2-1");
        Polynomial[] expected = new Polynomial[] {
                StringToPolynomial.parse("x-2"),
                StringToPolynomial.parse("7x-7")
        };
        assertArrayEquals(expected, Operations.divide(p1, p2));
        numberOfTestsPassed++;
    }
    @Test
    public void testDerivative() {
        Polynomial p = StringToPolynomial.parse("3x^3-2x^2+5x-7");
        Polynomial expected = StringToPolynomial.parse("9x^2-4x+5");
        assertEquals(expected, Operations.derivative(p));
        numberOfTestsPassed++;
    }
    @Test
    public void testIntegration() {
        Polynomial p = StringToPolynomial.parse("3x^3-2x^2+5x-7");
        Polynomial expected = StringToPolynomial.parse("0.75x^4-0.6666666666666666x^3+2.5x^2-7x");
        assertEquals(expected, Operations.integrate(p));
        numberOfTestsPassed++;
    }

    @Test
    public void testAdditionDifferentTerms() {
        Polynomial p1 = StringToPolynomial.parse("x^2+2x+1");
        Polynomial p2 = StringToPolynomial.parse("3x^3+4x^2+5");
        Polynomial expected = StringToPolynomial.parse("3x^3+5x^2+2x+6");
        assertEquals(expected, Operations.add(p1, p2));
        numberOfTestsPassed++;
    }

    @Test
    public void testSubtractionDifferentTerms() {
        Polynomial p1 = StringToPolynomial.parse("x^3+3x^2+2x+1");
        Polynomial p2 = StringToPolynomial.parse("4x^3+x^2+x+5");
        Polynomial expected = StringToPolynomial.parse("-3x^3+2x^2+x-4");
        assertEquals(expected, Operations.subtract(p1, p2));
        numberOfTestsPassed++;
    }

    @Test
    public void testMultiplicationDifferentTerms() {
        Polynomial p1 = StringToPolynomial.parse("2x+1");
        Polynomial p2 = StringToPolynomial.parse("x^2-2");
        Polynomial expected = StringToPolynomial.parse("2x^3+x^2-4x-2");
        assertEquals(expected, Operations.multiply(p1, p2));
        numberOfTestsPassed++;
    }

    @Test
    public void testDivisionDifferentTerms() {
        Polynomial p1 = StringToPolynomial.parse("x^3+2x^2+3x+4");
        Polynomial p2 = StringToPolynomial.parse("x+1");
        Polynomial[] expected = new Polynomial[] {
                StringToPolynomial.parse("x^2+x+2"),
                StringToPolynomial.parse("2.0")
        };
        assertArrayEquals(expected, Operations.divide(p1, p2));
        numberOfTestsPassed++;
    }

    @Test
    public void testDerivativeDifferentTerms() {
        Polynomial p = StringToPolynomial.parse("2x^3+4x^2+6x+8");
        Polynomial expected = StringToPolynomial.parse("6x^2+8x+6");
        assertEquals(expected, Operations.derivative(p));
        numberOfTestsPassed++;
    }

    @Test
    public void testIntegrationDifferentTerms() {
        Polynomial p = StringToPolynomial.parse("4x^3+6x^2+8x+10");
        Polynomial expected = StringToPolynomial.parse("x^4+2x^3+4x^2+10x");
        assertEquals(expected, Operations.integrate(p));
        numberOfTestsPassed++;
    }

}