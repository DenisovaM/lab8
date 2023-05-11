package package1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class Calculator {
	public static void main(String[] args) {
		CalFrame frame = new CalFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class CalFrame extends JFrame {
	public CalFrame() {
		setTitle("");
		CalcPanel panel = new CalcPanel();
		add(panel);
		pack();
	}
}

class CalcPanel extends JPanel {
	private JButton out;
	private JPanel panel;
	private double result;
	private String oldCommand;
	private boolean start_operation;	
	
	public CalcPanel() {
		setLayout(new BorderLayout());

		result = 0;
		oldCommand = "=";
		start_operation = true;

		Font fontout = new Font("TimesRoman", Font.BOLD, 50);

		out = new JButton("0");
		out.setEnabled(false);
		add(out, BorderLayout.NORTH);
		out.setFont(fontout);

		ActionListener OpOne = new OperationOne();
		ActionListener OpTwo = new OperationTwo();

		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 5));

		newButton("7", OpOne);
		newButton("8", OpOne);
		newButton("9", OpOne);
		newButton("+", OpTwo);

		newButton("4", OpOne);
		newButton("5", OpOne);
		newButton("6", OpOne);
		newButton("-", OpTwo);

		newButton("1", OpOne);
		newButton("2", OpOne);
		newButton("3", OpOne);
		newButton("*", OpTwo);
		
		newButton("0", OpOne);
		newButton("C", OpOne);
		newButton("=", OpTwo);
		newButton("/", OpTwo);

		add(panel, BorderLayout.CENTER);

	}

	private void newButton(String label, ActionListener listener) {
		Font fontButton = new Font("TimesRoman", Font.BOLD, 20);
		JButton button = new JButton(label);
		button.addActionListener(listener);
		button.setFont(fontButton);
		panel.add(button);
	}

	private class OperationOne implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String input = event.getActionCommand();
			if (start_operation) {
				out.setText("");
				start_operation = false;
			}
			if (input.equals("C")) {
				out.setText("0");
				start_operation = true;
			} else {
				out.setText(out.getText() + input);
			}
		}
	}

	private class OperationTwo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String complexOp = event.getActionCommand();

			if (complexOp.equals("1/x") || complexOp.equals("sqrt(x)")) {
				oldCommand = complexOp;
				operation(Double.parseDouble(out.getText()));
				oldCommand = "=";
				start_operation = true;
			} else {
				if (start_operation) {
					if (complexOp.equals("-")) {
						out.setText(complexOp);
						start_operation = false;
					} else
						oldCommand = complexOp;
				} else {
					operation(Double.parseDouble(out.getText()));
					oldCommand = complexOp;
					start_operation = true;
				}
			}
		}
	}

	public void operation(double x) {
	
		switch (oldCommand) {
		case "+":
			result += x;
			break;
		case "-":
			result -= x;
			break;
		case "*":
			result *= x;
			break;
		case "/":
			result /= x;
			break;
		case "=":
			result = x;
			break;
		}
		out.setText("" + result);
	}

	
}


