package calculadora;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

import model.CalculatorModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by miranda on 2/5/15.
 */
public class MyStepdefs {
    private CalculatorModel _model;
    private Exception _error;

    @Given("^El numero (\\-?[0-9]+)$")
    public void El_numero_numero_(int arg1) throws Throwable {
        _model = new CalculatorModel();
        _error = null;

        _model.setResult(arg1);
    }

    @When("^le sumamos el numero (\\-?[0-9]+)$")
    public void le_sumamos_el_numero(int arg1) throws Throwable {
        _model.add(arg1);
    }

    @When("^le restamos el numero (\\-?[0-9]+)$")
    public void le_restamos_el_numero_numero_(int arg1) throws Throwable {
        _model.subtract(arg1);
    }

    @When("^lo multiplicamos por (\\-?[0-9]+)$")
    public void lo_multiplicamos_por(int arg1) throws Throwable {
        _model.multiply(arg1);
    }

    @When("^lo dividimos por (\\-?[0-9]+)$")
    public void lo_dividimos_por(int arg1) throws Throwable {
        try {
            _model.divide(arg1);
        } catch (Exception e) {
            _error = e;
        }
    }

    @Then("^el resultado es (\\-?[0-9]+)$")
    public void el_resultado_es(int arg1) throws Throwable {
        assertEquals(arg1, _model.getResult());
    }

    @Then("^el resultado es un error$")
    public void el_resultado_es_un_error() throws Throwable {
        assertTrue(_error != null);
    }
}
