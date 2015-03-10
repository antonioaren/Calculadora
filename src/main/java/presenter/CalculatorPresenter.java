package presenter;

import view.I_CalculatorView;

import model.I_CalculatorModel;

public class CalculatorPresenter implements I_CalculatorPresenter {
	
	private I_CalculatorModel _model;
	private I_CalculatorView  _view;

	private String _number;
	private String _savedOperand;
	
	public CalculatorPresenter (I_CalculatorModel model, I_CalculatorView view) {
		set_model((I_CalculatorModel) model);
		set_view((I_CalculatorView) view);
		_number = "0";
		_savedOperand = "";
        displayNumber();
	}
	
	private void displayNumber() {
		get_view().display(_number);    	
	}

	// ------------------------------------------------------------------------

	@Override
	public void backspacePressed() {
		_savedOperand = "";

		if (_number.length() == 1) {
			_number = "0";			
		} else {
	  		_number = StringUtils.remove_right_char(_number);	
		}
		
		displayNumber();
	}

	@Override
	public void clearPressed() {
		get_model().reset();
		
		_number = "0";
		_savedOperand = "";

		displayNumber();
	}

	@Override
	public void digitPressed(String c) {
		Integer MaxInt = Integer.MAX_VALUE;
		int MaxDigits = MaxInt.toString().length();

		if (_number.length() == MaxDigits) {
			;
		} else if (_number.equals("0")) {
			_number = c;
		} else {
			_number = _number + c;
		}

		displayNumber();
	}

	@Override
	public void operatorPressed (String c) {
		Integer n;

		//  Evito problemas con numeros que tengan el maximo numero de cifras para
		//  un entero (por ejemplo 10 cifras), pero con un valor superior a Integer'Last

		try {
			n = StringUtils.toInteger(_number);
		} catch (Exception e) {
			get_view().displayWarning("wrong number");
			Integer current_value = get_model().getResult();
			_number = current_value.toString();
			displayNumber();
			_number = "0";
			return;
		}

		if (_savedOperand.equals("")
				&& get_model().getResult() == 0) {
			get_model().setResult (n);

		} else {
			try {
				if (_savedOperand.equals("+")) {
					get_model().add(n);

				} else if (_savedOperand.equals("-")) {
					get_model().subtract(n);        		

				} else if (_savedOperand.equals("*")) {
					get_model().multiply(n);        		

				} else if (_savedOperand.equals("/")) {
					get_model().divide(n);
				}
			} catch (Exception e) {
				get_view().displayWarning("operation error");
			}
		}

		Integer current_value = get_model().getResult();
		_number = current_value.toString();
		displayNumber();

		_number = "0";
		_savedOperand = c;
	}

	@Override
	public void dotPressed() {
		// to-do
	}

	//  Getters/setters ------------------------------------------------------

	protected I_CalculatorView get_view() {
		return _view;
	}

	private void set_view(I_CalculatorView _view) {
		this._view = _view;
	}

	// ---	

	private void set_model(I_CalculatorModel _model) {
		this._model = _model;
	}

	protected I_CalculatorModel get_model() {
		return _model;
	}

}