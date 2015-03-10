package model;
public class CalculatorModel implements I_CalculatorModel {
	private int _result;
			
	public CalculatorModel() {
		_result = 0;
	}
	
	@Override
	public void add(int operand) throws Exception {
	    setResult (getResult() + operand);
	}

	@Override
	public void subtract(int operand) throws Exception {
	    setResult (getResult() - operand);
	}

	@Override
	public void multiply(int operand) throws Exception {
	    setResult (getResult() * operand);		
	}

	@Override
	public void divide(int operand) throws Exception {
		if (operand == 0) {
	         throw new DivideByZero();
		}
		
	    setResult (getResult() / operand);
	}
	
	@Override
	public int getResult() {
		return _result;
	}
	
	@Override
	public void setResult(int value) {
		_result = value;
	}

	@Override
	public void reset() {
		_result = 0;
	}

	
}
