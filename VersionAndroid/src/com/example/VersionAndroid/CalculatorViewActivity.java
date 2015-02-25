package com.example.VersionAndroid;

import view.I_CalculatorView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import presenter.I_CalculatorPresenter;

public class CalculatorViewActivity extends Activity implements I_CalculatorView, OnClickListener {
	private EditText _display;
    private I_CalculatorPresenter _presenter;

	private void debug (String text) {
		Log.d("CalcView", text);
	}	

	private void registerListener(int resourceId) {
		View v = findViewById(resourceId);

		if (v != null) {
			v.setOnClickListener (this);
		} else {
			debug("registerListener - resource not available");
		}
	}
	
	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);

		debug("onCreate");

		// Load the view
		setContentView(R.layout.calculator_view);

		// Reference to the EditText field for displaying the result
		set_display((EditText) findViewById(R.id.display));

		// Register this object as the listener of all the buttons
		registerListener(R.id.button0);
		registerListener(R.id.button1);
		registerListener(R.id.button2);
		registerListener(R.id.button3);
		registerListener(R.id.button4);
		registerListener(R.id.button5);
		registerListener(R.id.button6);
		registerListener(R.id.button7);
		registerListener(R.id.button8);
		registerListener(R.id.button9);

		registerListener(R.id.buttonPlus);
		registerListener(R.id.buttonMinus);
		registerListener(R.id.buttonMult);		
		registerListener(R.id.buttonDivide);

		registerListener(R.id.buttonClear);
		registerListener(R.id.buttonEqual);
		registerListener(R.id.buttonBackspace);
		registerListener(R.id.buttonDot);

		// Register this view in the mediator and get its presenter
		AndroidAppMediator app = (AndroidAppMediator) getApplication();
		app.register(this);
		_presenter = (I_CalculatorPresenter) app.getPresenter(this);
		
		debug("onCreate() [done]");
	}

	@Override
	public void displayWarning(String text) {
		Toast.makeText (getBaseContext(),text, Toast.LENGTH_SHORT).show();
		debug("toast: " + text);
	}

	/**
	 * @param text Text to be shown in the display of this view
	 */
	@Override
	public void display(String text) {
		debug("display: " + text);

		get_display().setText(text);
	}

	/**
	 * @param view Current view
	 * @return The text associated with a button
	 */
	private String getText(View view) {
		return ((Button) view).getText().toString();
	}

	/**
	 * In this implementation we associate the same observer to all
	 * the buttons of the view.
	 * @param view Current view
	 */
	@Override
	public void onClick(View view) {
		int btn = view.getId();

		switch (btn) {
			case R.id.button0:
			case R.id.button1:
			case R.id.button2:
			case R.id.button3:
			case R.id.button4:
			case R.id.button5:
			case R.id.button6:
			case R.id.button7:
			case R.id.button8:
			case R.id.button9:
				String digit = this.getText(view);
				debug(digit);
				getPresenter().digitPressed(digit);
				break;

			case R.id.buttonPlus:
			case R.id.buttonMinus:
			case R.id.buttonMult:
			case R.id.buttonDivide:
			case R.id.buttonEqual:
				String op = this.getText(view);
				debug(op);
				getPresenter().operatorPressed(op);
				break;

			//  Examples of toast display
			case R.id.buttonClear:
				debug("clear");
				displayWarning("clear pressed");
				getPresenter().clearPressed();
				break;
	
			case R.id.buttonBackspace:
				debug("backspace");
				displayWarning("backspace pressed");
				getPresenter().backspacePressed();
				break;
	
			case R.id.buttonDot:
				displayWarning("dot pressed");
				break;
		}
	}

	// Getters/setters ***************************************************

	private EditText get_display() {
		return _display;
	}
	private void set_display(EditText _display) {
		this._display = _display;
	}
	private I_CalculatorPresenter getPresenter() {
		return _presenter;
	}
}