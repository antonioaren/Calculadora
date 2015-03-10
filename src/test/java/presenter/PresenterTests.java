package presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import view.I_CalculatorView;

import static org.mockito.Mockito.*;  // Required for mockito
import org.mockito.InOrder;

import presenter.CalculatorPresenter;
import model.CalculatorModel;

public class PresenterTests {
	private CalculatorPresenter _presenter;
	private I_CalculatorView _viewMock;
	private InOrder _inOrderMock;

	@Before
	public void setUp() throws Exception {
		// _logger = NativeLogger.get_Logger();
		CalculatorModel model = new CalculatorModel();

		set_viewMock    (mock (I_CalculatorView.class));
		set_inOrderMock (inOrder(get_viewMock()));

		set_presenter   (new CalculatorPresenter(model, _viewMock));
	}

	// Test all digits: 1234567890
	@Test
	public void test01_display() {
		get_presenter().digitPressed("1");
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("3");
		get_presenter().digitPressed("4");
		get_presenter().digitPressed("5");
		get_presenter().digitPressed("6");
		get_presenter().digitPressed("7");
		get_presenter().digitPressed("8");
		get_presenter().digitPressed("9");
		get_presenter().digitPressed("0");

		//  The presenter must have shown values on the display 11 times (the previous 10
		//  digits plus the initial value of the display)
		
		verify (get_viewMock(), times (11)).display(anyString());
		get_inOrderMock().verify (_viewMock).display("0");
		get_inOrderMock().verify (_viewMock).display("1");
		get_inOrderMock().verify (_viewMock).display("12");
		get_inOrderMock().verify (_viewMock).display("123");
		get_inOrderMock().verify (_viewMock).display("1234");
		get_inOrderMock().verify (_viewMock).display("12345");
		get_inOrderMock().verify (_viewMock).display("123456");
		get_inOrderMock().verify (_viewMock).display("1234567");
		get_inOrderMock().verify (_viewMock).display("12345678");
		get_inOrderMock().verify (_viewMock).display("123456789");
		get_inOrderMock().verify (_viewMock).display("1234567890");

		// verify (get_viewMock()).display("1234567890");  // Should not work fine???

		verify(get_viewMock(), never()).displayWarning(anyString());
	}

	// 12 <clear>
    @Test
	public void test02_clear() {
		get_presenter().digitPressed("1");
		get_presenter().digitPressed("2");
		get_presenter().clearPressed();

		verify (get_viewMock(), times (4)).display(anyString());
		get_inOrderMock().verify (_viewMock).display("0");
		get_inOrderMock().verify (_viewMock).display("1");
		get_inOrderMock().verify (_viewMock).display("12");
		get_inOrderMock().verify (_viewMock).display("0");	

		verify(get_viewMock(), never()).displayWarning(anyString());
	}

	// -------------------------------------------------------------------------------
	//                                Testing Addition
	// -------------------------------------------------------------------------------

	// 1 + 2 =
	@Test
	public void test03_basicOpAdd01() {
		get_presenter().digitPressed("1");       //  2nd call to display
		get_presenter().operatorPressed("+");    //  3rd call to display
		get_presenter().digitPressed("2");       //  4rd call to display
		get_presenter().operatorPressed("=");    //  5th call to display

		verify (get_viewMock(), times (5)).display(anyString());

		get_inOrderMock().verify (_viewMock).display("0");
		get_inOrderMock().verify (_viewMock, times(2)).display("1");
		get_inOrderMock().verify (_viewMock).display("2");
		get_inOrderMock().verify (_viewMock).display("3");		

		//		verify (get_viewMock()).display("3");

		verify(get_viewMock(), never()).displayWarning(anyString());
	}

	// 1 + 2 + 4 =
	@Test
	public void test03_basicOpAdd02() {
		get_presenter().digitPressed("1");       //  2nd call to display
		get_presenter().operatorPressed("+");    //  3rd call to display
		get_presenter().digitPressed("2");       //  4rd call to display
		get_presenter().operatorPressed("+");    //  5th call to display
		get_presenter().digitPressed("4");       //  6th call to display
		get_presenter().operatorPressed("=");    //  7th call to display

		verify (get_viewMock(), times (7)).display(anyString());

		get_inOrderMock().verify (_viewMock).display("0");
		get_inOrderMock().verify (_viewMock, times(2)).display("1");
		get_inOrderMock().verify (_viewMock).display("2");
		get_inOrderMock().verify (_viewMock).display("3");	
		get_inOrderMock().verify (_viewMock).display("4");
		get_inOrderMock().verify (_viewMock).display("7");

		//		verify (get_viewMock()).display("3");

		verify(get_viewMock(), never()).displayWarning(anyString());
	}

	// 123 + 4 =
	@Test
	public void test03_basicOpAdd03() {
		get_presenter().digitPressed("1");       //  2nd call to display
		get_presenter().digitPressed("2");       //  3rd call to display
		get_presenter().digitPressed("3");       //  4rd call to display

		get_presenter().operatorPressed("+");    //  5th call to display
		get_presenter().digitPressed("4");       //  6th call to display
		get_presenter().operatorPressed("=");    //  7th call to display

		verify (get_viewMock(), times (7)).display(anyString());

		get_inOrderMock().verify (_viewMock).display("0");
		get_inOrderMock().verify (_viewMock).display("1");
		get_inOrderMock().verify (_viewMock).display("12");
		get_inOrderMock().verify (_viewMock, times(2)).display("123");	
		get_inOrderMock().verify (_viewMock).display("127");

		//		verify (get_viewMock()).display("3");

		verify(get_viewMock(), never()).displayWarning(anyString());
	}

	// 2 + 9999999999 + 3 =
	@Test
	public void test03_basicOpAdd04() {
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("+");

		// 9999999999
		for (int j=1; j<=10; j++) {
			get_presenter().digitPressed("9");
		}

		get_presenter().operatorPressed("+");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("5");

		verify(get_viewMock(), times (1)).displayWarning("wrong number");
	}

	// -------------------------------------------------------------------------------
	//                                Testing Subtraction
	// -------------------------------------------------------------------------------

	// -2 =
	@Test
	public void test04_basicOpSub00() {
		get_presenter().operatorPressed("-");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("-2");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 1 - 2 =
	@Test
	public void test04_basicOpSub01() {
		get_presenter().digitPressed("1");
		get_presenter().operatorPressed("-");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("-1");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 1 - 2 - 3 =
	@Test
	public void test04_basicOpSub02() {
		get_presenter().digitPressed("1");
		get_presenter().operatorPressed("-");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("-");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("-4");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 123 - 3 =
	@Test
	public void test04_basicOpSub03() {
		get_presenter().digitPressed("1");
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("-");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("120");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// -------------------------------------------------------------------------------
	//                                Testing Multiplication
	// -------------------------------------------------------------------------------

	// 2 * 3 =
	@Test
	public void test05_basicOpMul01() {
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("*");
		get_presenter().digitPressed("3");		
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("6");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 2 * 3 * 4 =
	@Test
	public void test05_basicOpMul02() {
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("*");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("*");
		get_presenter().digitPressed("4");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("24");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 123 * 2 =
	@Test
	public void test05_basicOpMul03() {
		get_presenter().digitPressed("1");
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("*");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("246");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// -------------------------------------------------------------------------------
	//                                Testing Division
	// -------------------------------------------------------------------------------

	// 24 / 3 =
	@Test
	public void test06_basicOpDiv01() {
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("4");
		get_presenter().operatorPressed("/");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("8");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 24 / 3 / 2 =
	@Test
	public void test06_basicOpDiv02() {
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("4");
		get_presenter().operatorPressed("/");
		get_presenter().digitPressed("3");
		get_presenter().operatorPressed("/");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("4");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// 246 / 2 =
	@Test
	public void test06_basicOpDiv03() {
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("4");
		get_presenter().digitPressed("6");
		get_presenter().operatorPressed("/");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("=");

		verify (get_viewMock()).display("123");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}

	// -------------------------------------------------------------------------------
	//                                Testing Operators
	// -------------------------------------------------------------------------------

	// + - * / =
	@Test
	public void test07_basicOp01() {
		get_presenter().operatorPressed("+");
		get_presenter().operatorPressed("-");
		get_presenter().operatorPressed("*");
		get_presenter().operatorPressed("/");
		get_presenter().operatorPressed("=");

		verify (get_viewMock(), times (6)).display("0");
		verify (get_viewMock(), times (1)).displayWarning(anyString());
	}	

	// 12 = + 3 =
	@Test
	public void test07_basicOp02() {
		get_presenter().digitPressed("1");
		get_presenter().digitPressed("2");
		get_presenter().operatorPressed("=");
		get_presenter().operatorPressed("+");
		get_presenter().digitPressed("3");		
		get_presenter().operatorPressed("=");

		verify (get_viewMock(), times (1)).display("15");
		verify (get_viewMock(), never()).displayWarning(anyString());
	}	

	// -------------------------------------------------------------------------------
	//                                Testing Backspace
	// -------------------------------------------------------------------------------
	// 12 = + 3 =

	@Test
	public void test08_backspace() {
		get_presenter().digitPressed("1");
		get_presenter().digitPressed("2");
		get_presenter().digitPressed("3");

		get_presenter().backspacePressed();
		verify (get_viewMock(), times (2)).display("12");

		get_presenter().backspacePressed();
		verify (get_viewMock(), times (2)).display("1");

		get_presenter().digitPressed("4");
		verify (get_viewMock(), times (1)).display("14");

		get_presenter().backspacePressed();
		verify (get_viewMock(), times (3)).display("1");

		get_presenter().backspacePressed();
		verify (get_viewMock(), times (2)).display("0");

		verify (get_viewMock(), never()).displayWarning(anyString());
	}	

	// -------------------------------------------------------------------------------
	//                                 Stress Tests
	// -------------------------------------------------------------------------------

	private void randomDigit() {
		int NumDigits = 10; 
		Integer index = (int) (Math.random() * NumDigits);
		assertTrue (index >=0 && index <NumDigits);

		get_presenter().digitPressed(index.toString());
	}

	@Test
	public void test11_stress01() {
		for (int j=0; j<1000; j++) {
			randomDigit();
		}
	}

	// ---
	
	private void randomOperator() {
		int NumOperands = 5;
		Integer index = (int) (Math.random() * NumOperands);
		assertTrue (index >=0 && index <NumOperands);

		switch (index) {
		case 0: get_presenter().operatorPressed("+");
		case 1: get_presenter().operatorPressed("-");
		case 2: get_presenter().operatorPressed("*");
		case 3: get_presenter().operatorPressed("/");
		case 4: get_presenter().operatorPressed("=");
		}
	}
	
	@Test
	public void test11_stress02() {
		for (int j=0; j<1000; j++) {
			randomOperator();
		}
	}

	// ---

	private void randomButton() {
		int NumDigits = 18;
		Integer index = (int) (Math.random() * NumDigits);
		assertTrue (index >=0 && index <NumDigits);

		switch (index) {
		case 0: get_presenter().operatorPressed("0");
		case 1: get_presenter().operatorPressed("1");
		case 2: get_presenter().operatorPressed("2");
		case 3: get_presenter().operatorPressed("3");
		case 4: get_presenter().operatorPressed("4");
		case 5: get_presenter().operatorPressed("5");
		case 6: get_presenter().operatorPressed("6");
		case 7: get_presenter().operatorPressed("7");
		case 8: get_presenter().operatorPressed("8");
		case 9: get_presenter().operatorPressed("9");

		case 10: get_presenter().operatorPressed("+");
		case 11: get_presenter().operatorPressed("-");
		case 12: get_presenter().operatorPressed("*");
		case 13: get_presenter().operatorPressed("/");
		case 14: get_presenter().operatorPressed("=");

		case 15: get_presenter().clearPressed();
		case 16: get_presenter().backspacePressed();
		case 17: get_presenter().dotPressed();
		}
	}

	@Test
	public void test11_stress03() {
		for (int j=0; j<1000; j++) {
			randomButton();
		}
	}

	// Getters/setters ----------------------------------------------------------

	private InOrder get_inOrderMock() {
		return _inOrderMock;
	}

	private void set_inOrderMock(InOrder _inOrderMock) {
		this._inOrderMock = _inOrderMock;
	}

	private CalculatorPresenter get_presenter() {
		return _presenter;
	}

	private void set_presenter(CalculatorPresenter _presenter) {
		this._presenter = _presenter;
	}

	private I_CalculatorView get_viewMock() {
		return _viewMock;
	}

	private void set_viewMock(I_CalculatorView _viewMock) {
		this._viewMock = _viewMock;
	}

}
