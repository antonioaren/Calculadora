package model;

public interface I_CalculatorModel {
	
	public void add(int operand) throws Exception;
	public void subtract(int operand) throws Exception;
	public void multiply(int operand) throws Exception;
	public void divide(int operand) throws Exception;
	public void reset();

	public int  getResult();	
	public void setResult(int value);
}





