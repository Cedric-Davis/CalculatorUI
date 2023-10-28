import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	@SuppressWarnings("serial")
public class CalculatorBox extends JFrame{

	JButton btnAdd, btnSubtract, btnMultiply, btnDivide, btnClear, btnDelete, btnEqual, btnPeriod;
	JButton[] numbtns;
	JTextField output;
	String previous, current, operator;
	
	public void appendToOutput(String text) {
		// TODO Auto-generated method stub
		if(text.equals(".") && current.contains(".")) {
			return;			
		}
		current+=text;
	}
	public void selectOperator(String newOperator) {
		// TODO Auto-generated method stub
		if(current.isEmpty()) {
			operator = newOperator;
			return;
		}
		if(!previous.isEmpty()) {
			System.out.println(previous);
			calculate();
		}
		System.out.println(newOperator);
		operator = newOperator;
		previous = current;
		current = "";
	}
	public void updateOutput() {
		// TODO Auto-generated method stub
		output.setText(current);
	}
	public void delete() {
		// TODO Auto-generated method stub
		if(current.length() > 0) {
			current = current.substring(0, current.length() - 1);
		}
	}
	public void clear() {
		// TODO Auto-generated method stub
		current = "";
		previous = "";
		operator = null;
	}
	public void calculate() {
		// TODO Auto-generated method stub
		if(previous.length() < 1 || current.length() < 1) {
			return;
		}
		double result = 0.0;
		double num1 = Double.parseDouble(previous);
		double num2 = Double.parseDouble(current);
		switch(operator) {
			case "*":
				result = num1*num2;
				break;
			case "÷":
				result = num1/num2;
				break;
			case "+":
				result = num1+num2;
				break;
			case "-":
				result = num1-num2;
				break;
			default:
				break;
		}
		current = String.valueOf(result);
		operator = null;
		previous = "";
		proccessOutputNumber();
	}
public void proccessOutputNumber() {
		// TODO Auto-generated method stub
		if(current.length() > 0) {
			String integerPart = current.split("\\.")[0];
			String decimalPart = current.split("\\.")[1];
			if(decimalPart.equals("0")) {
				current = integerPart;
			}
		}
	}
private class NumberBtnHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedBtn = (JButton) e.getSource();
			for (JButton btn : numbtns) {
				if (selectedBtn == btn) {
					appendToOutput(btn.getText());
					updateOutput();
				}
			}
		}
	}
	private class OperatorBtnHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedBtn = (JButton) e.getSource();
			if (selectedBtn == btnMultiply) {
				selectOperator(btnMultiply.getText());
			} else if (selectedBtn == btnAdd) {
				selectOperator(btnAdd.getText());
			} else if (selectedBtn == btnSubtract) {
				selectOperator(btnSubtract.getText());
			} else if (selectedBtn == btnDivide) {
				selectOperator(btnDivide.getText());
			}
			updateOutput();
			}
		}

	private class OtherBtnHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e ) {
			JButton selectedBtn = (JButton) e.getSource();
			if (selectedBtn == btnDelete) {
				delete();
			} else if (selectedBtn == btnClear) {
				clear();
			} else if (selectedBtn == btnEqual) {
				calculate();
			}
			updateOutput();
			}
		}

	
	public CalculatorBox() {
		super("Box Calculator");
		JPanel mainPanel = new JPanel();
		previous = "";
		current = "";
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		
		output = new JTextField(16);
		btnAdd = new JButton("+");
		btnSubtract = new JButton("-");
		btnMultiply = new JButton("*");
		btnDivide = new JButton("÷");
		btnClear = new JButton("C");
		btnDelete = new JButton("D");
		btnEqual = new JButton("=");
		btnPeriod = new JButton(".");
		// Instantiate action listeners
        NumberBtnHandler numBtnHandler = new NumberBtnHandler();
        OtherBtnHandler otherBtnHandler = new OtherBtnHandler();
        OperatorBtnHandler opBtnHandler = new OperatorBtnHandler();

		
		numbtns = new JButton[11];
		numbtns[10] = btnPeriod;
		for(int count = 0; count <numbtns.length-1; count++) {
			numbtns[count] = new JButton(String.valueOf(count));
			numbtns[count].setFont(new Font("Monospaced", Font.BOLD, 22));
			numbtns[count].addActionListener(numBtnHandler);
		}
		
		btnAdd.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnSubtract.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnMultiply.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnDivide.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnClear.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnDelete.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnEqual.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnPeriod.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		output.setMaximumSize(new Dimension(185, 40));
		output.setFont(new Font("Monospaced", Font.BOLD, 27));
		output.setDisabledTextColor(new Color(0,0,0));
		output.setMargin(new Insets(0,5,0,0));
		output.setText("0");
		
		btnPeriod.addActionListener(numBtnHandler);
		btnDelete.addActionListener(otherBtnHandler);
		btnClear.addActionListener(otherBtnHandler);
		btnEqual.addActionListener(otherBtnHandler);
		
		btnAdd.addActionListener(opBtnHandler);
		btnMultiply.addActionListener(opBtnHandler);
		btnSubtract.addActionListener(opBtnHandler);
		btnDivide.addActionListener(opBtnHandler);
		row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
		row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
		row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
		row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
		row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));
		
		row1.add(Box.createHorizontalGlue());
		row1.add(btnClear);
		row1.add(btnDelete);
		
		row2.add(numbtns[7]);
		row2.add(numbtns[8]);
		row2.add(numbtns[9]);
		row2.add(btnMultiply);
		
		row3.add(numbtns[4]);
		row3.add(numbtns[5]);
		row3.add(numbtns[6]);
		row3.add(btnSubtract);
		
		row4.add(numbtns[1]);
		row4.add(numbtns[2]);
		row4.add(numbtns[3]);
		row4.add(btnAdd);
		
		row5.add(btnPeriod);
		row5.add(numbtns[0]);
		row5.add(btnDivide);
		row5.add(btnEqual);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(output);
		mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
		mainPanel.add(row1);
		mainPanel.add(row2);
		mainPanel.add(row3);
		mainPanel.add(row4);
		mainPanel.add(row5);
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(205, 280);
		
		
        // Initialize, style, and add action listeners to number buttons
       
	}
	
	
	public static void main(String[]args) {
		new CalculatorBox();
	}
	
	
	
}