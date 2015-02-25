package model;

import static org.junit.Assert.*;

import model.CalculatorModel;
import model.I_CalculatorModel;
import model.DivideByZero;

import org.junit.Before;
import org.junit.Test;

public class CalculatorModelTest {
    I_CalculatorModel myCalc;

    @Before
    public void setUp() throws Exception {
        myCalc = new CalculatorModel();
    }

    // --------------------------------------------------------	
    @Test
    public void t00initVal() {
        assertTrue (myCalc.getResult() == 0);
    }

    @Test
    public void t00setGet() {
        myCalc.setResult(12);
        assertTrue(myCalc.getResult() == 12);
    }

    @Test
    public void t00setReset() {
        myCalc.setResult(12);
        myCalc.reset();
        assertTrue(myCalc.getResult() == 0);
    }

    // --------------------------------------------------------	
    @Test
    public void t01add01() throws Exception {
        myCalc.add (5);
        assertTrue (myCalc.getResult() == 5);
    }

    @Test
    public void t01add02() throws Exception {
        myCalc.setResult(3);
        myCalc.add (7);
        assertTrue (myCalc.getResult() == 10);
    }

    @Test
    public void t01add03() throws Exception {
        myCalc.setResult(3);
        myCalc.add (7);
        myCalc.add(6);
        assertTrue (myCalc.getResult() == 16);
    }

    @Test
    public void t01add04() throws Exception {
        myCalc.add (-7);
        assertTrue (myCalc.getResult() == -7);
    }

    // --------------------------------------------------------
    @Test
    public void t02sub01() throws Exception {
        myCalc.subtract(5);
        assertTrue (myCalc.getResult() == -5);
    }

    @Test
    public void t02sub02() throws Exception {
        myCalc.setResult (3);
        myCalc.subtract (7);
        assertTrue (myCalc.getResult() == -4);
    }

    @Test
    public void t02sub03() throws Exception {
        myCalc.setResult (3);
        myCalc.subtract (7);
        myCalc.subtract (6);
        assertTrue (myCalc.getResult() == -10);
    }

    @Test
    public void t02sub04() throws Exception {
        myCalc.subtract(-7);
        assertTrue (myCalc.getResult() == 7);
    }


    // --------------------------------------------------------
    @Test
    public void t03mult01() throws Exception {
        myCalc.multiply (5);
        assertTrue (myCalc.getResult() == 0);
    }

    @Test
    public void t03mult02() throws Exception {
        myCalc.setResult(3);
        myCalc.multiply (7);
        assertTrue (myCalc.getResult() == 21);
    }

    @Test
    public void t03mult03() throws Exception {
        myCalc.setResult(-6);
        myCalc.multiply (6);
        myCalc.multiply (3);
        assertTrue (myCalc.getResult() == -108);
    }

    // --------------------------------------------------------
    @Test
    public void t04div01() throws Exception {
        myCalc.divide (5);
        assertTrue (myCalc.getResult() == 0);
    }

    @Test
    public void t04div02() throws Exception {
        myCalc.divide (7);
        assertTrue (myCalc.getResult() == 0);
    }

    @Test
    public void t04div03() throws Exception {
        myCalc.setResult (7);
        myCalc.divide (-2);
        assertTrue (myCalc.getResult() == -3);
    }

    @Test(expected=DivideByZero.class)
    public void t04divZero1() throws Exception {
        myCalc.setResult(5);
        myCalc.divide (0);
        fail("DivisionByZero not raised");
    }

    @Test
    public void t04divZero2() {
        myCalc.setResult(5);

        try {
            myCalc.divide (0);
            fail("DivisionByZero not raised");

        } catch (DivideByZero e) {
            assertTrue(true);

        } catch (Exception e) {
            fail("unknown exception");
        }

    }


}
