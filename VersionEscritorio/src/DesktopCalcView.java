//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..

import model.CalculatorModel;
import model.I_CalculatorModel;
import presenter.CalculatorPresenter;
import presenter.I_CalculatorPresenter;
import view.I_CalculatorView;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Container;

public class DesktopCalcView implements I_CalculatorView {
	private I_CalculatorModel _model;
	private I_CalculatorPresenter _presenter;

	private JTextField _display;

	public DesktopCalcView() {
        JFrame guiFrame = new JFrame();

		//make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Simple Calculator");
		guiFrame.setSize(300,300);

		//This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);

		_display = new JTextField();
		_display.setHorizontalAlignment(JTextField.RIGHT);
		_display.setEditable(false);

		guiFrame.add(_display, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();

		//Make a Grid
		buttonPanel.setLayout(new GridLayout(4,4));   
		guiFrame.add(buttonPanel, BorderLayout.CENTER);

        //  ------ Initialization of the Model-View-Presenter

        _model     = new CalculatorModel();
        _presenter = new CalculatorPresenter(_model, this);

		//First row
		for (int i=1;i<=3;i++) {
			addDigit(buttonPanel, String.valueOf(i), _presenter);
		}
		addOperator(buttonPanel, "/", _presenter);

		//Second row
		for (int i=4;i<=6;i++) {
			addDigit(buttonPanel, String.valueOf(i), _presenter);
		}
		addOperator(buttonPanel, "*", _presenter);

		//Third row
		for (int i=7;i<=9;i++) {
			addDigit(buttonPanel, String.valueOf(i), _presenter);
		}
		addOperator(buttonPanel, "-", _presenter);

		//Fourth row
		addDigit(buttonPanel, "0", _presenter);
		addDigit(buttonPanel, ".", _presenter);		
		addOperator(buttonPanel, "=", _presenter);
		addOperator(buttonPanel, "+", _presenter);

		guiFrame.setVisible(true);  

		//  ------ Start the presenter because the view is now ready

		//  _presenter.start();
	}

	private void addDigit(Container parent, String name, I_CalculatorPresenter presenter) {
		JButton _button = new JButton(name);
		_button.setActionCommand(name);
		
		DigitAction _action = new DigitAction(name, presenter);
		_button.addActionListener(_action);

		parent.add(_button);
	}

	private void addOperator(Container parent, String name, I_CalculatorPresenter presenter) {
		JButton _button = new JButton(name);
		_button.setActionCommand(name);
		
		OperatorAction _action = new OperatorAction(name, presenter);
		_button.addActionListener(_action);	
		
		parent.add(_button);
	}
	
	private class OperatorAction implements ActionListener {
		private String _text;
        private I_CalculatorPresenter _presenter;
 
		public OperatorAction(String text, I_CalculatorPresenter presenter) {
			_text = text;
			_presenter = presenter;
		}

		public void actionPerformed(ActionEvent event) {
			_presenter.operatorPressed(_text);
		}
	}
	
	private class DigitAction implements ActionListener {
		private String _text;
        private I_CalculatorPresenter _presenter;
        
		public DigitAction(String text, I_CalculatorPresenter presenter) {
			_text = text;
			_presenter = presenter;
		}

		public void actionPerformed(ActionEvent event)
		{
			_presenter.digitPressed(_text);
		}
	}

	@Override
	public void display(String text) {
		_display.setText(text);
	}
	
	@Override
	public void displayWarning(String text) {
		//  No action
	}

}
