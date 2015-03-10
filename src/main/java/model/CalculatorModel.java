package model;
public class CalculatorModel implements I_CalculatorModel {
	private int _result;
			
	public CalculatorModel() { // Constructor de inicialización
        _result = 0;
	}
	
	@Override
	public void add(int operand) throws Exception {// operación que suma
	    setResult (getResult() + operand);
	}

	@Override
	public void subtract(int operand) throws Exception {// operacion que resta

	    setResult (getResult() - operand);
	}

	@Override
	public void multiply(int operand) throws Exception {// operación que multiplica
	    setResult (getResult() * operand);		
	}

	@Override
	public void divide(int operand) throws Exception {//Operación que divide
		if (operand == 0) {// Si divide por 0
	         throw new DivideByZero();
		}else {
            setResult(getResult() / operand);
        }
	}
	
	@Override
	public int getResult() {// devuelve el valor de _result
		return _result;
	}
	
	@Override
	public void setResult(int value) {//es el metodo que pone el resultado.
		_result = value;
	}

	@Override
	public void reset() {
		_result = 0;
	}

	
}
