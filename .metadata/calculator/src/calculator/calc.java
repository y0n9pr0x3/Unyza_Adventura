package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class calc extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	double jedna;
	double dva;
	double vysledok;
	String operator;
	public String odpoved;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calc frame = new calc();
					frame.setTitle("KALKULACKA_TUPAC");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public calc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 21, 335, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btn_7 = new JButton("7");
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_7.getText();
				textField.setText(number);
			}
		});
		btn_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_7.setBounds(10, 88, 89, 76);
		contentPane.add(btn_7);
		
		JButton btn_4 = new JButton("4");
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_4.getText();
				textField.setText(number);
			}
		});
		btn_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_4.setBounds(10, 160, 89, 76);
		contentPane.add(btn_4);
		
		JButton btn_1 = new JButton("1");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_1.getText();
				textField.setText(number);
			}
		});
		btn_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_1.setBounds(10, 230, 89, 76);
		contentPane.add(btn_1);
		
		JButton btn_nula = new JButton("0");
		btn_nula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_nula.getText();
				textField.setText(number);
			}
		});
		btn_nula.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_nula.setBounds(10, 306, 89, 76);
		contentPane.add(btn_nula);
		
		JButton btn_8 = new JButton("8");
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_8.getText();
				textField.setText(number);
			}
		});
		btn_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_8.setBounds(126, 88, 89, 76);
		contentPane.add(btn_8);
		
		JButton btn_9 = new JButton("9");
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_9.getText();
				textField.setText(number);
			}
		});
		btn_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_9.setBounds(239, 88, 89, 76);
		contentPane.add(btn_9);
		
		JButton btn_plus = new JButton("+");
		btn_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jedna = Double.parseDouble(textField.getText());
				textField.setText(" ");
				operator = "+"; 
			}
		});
		btn_plus.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_plus.setBounds(362, 88, 89, 76);
		contentPane.add(btn_plus);
		
		JButton btn_5 = new JButton("5");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_5.getText();
				textField.setText(number);
			}
		});
		btn_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_5.setBounds(126, 160, 89, 76);
		contentPane.add(btn_5);
		
		JButton btn_2 = new JButton("2");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_2.getText();
				textField.setText(number);
			}
		});
		btn_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_2.setBounds(126, 230, 89, 76);
		contentPane.add(btn_2);
		
		JButton btn_bodka = new JButton(".");
		btn_bodka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText();
				double num1 = Double.parseDouble(number);
				double num2 = num1*(-1);
				textField.setText(String.valueOf(num2));
			}
		});
		btn_bodka.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_bodka.setBounds(126, 306, 89, 76);
		contentPane.add(btn_bodka);
		
		JButton btn_6 = new JButton("6");
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_6.getText();
				textField.setText(number);
			}
		});
		btn_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_6.setBounds(239, 160, 89, 76);
		contentPane.add(btn_6);
		
		JButton btn_3 = new JButton("3");
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn_3.getText();
				textField.setText(number);
			}
		});
		btn_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_3.setBounds(239, 230, 89, 76);
		contentPane.add(btn_3);
		
		JButton btn_rovnasa = new JButton("=");
		btn_rovnasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String odpoved;
				dva  = Double.parseDouble(textField.getText());
				if(operator == "+") {
					vysledok = jedna + dva;
					if(operator == ".") {
						vysledok = Double.parseDouble(textField.getText());
						vysledok = vysledok * (-1);
						odpoved = String.format("%.2f", vysledok);
						textField.setText(odpoved);
					}
					odpoved = String.format("%.2f", vysledok);
					textField.setText(odpoved);
				}
				else if(operator == "-") {
					vysledok = jedna - dva;
					odpoved = String.format("%.2f", vysledok);
					textField.setText(odpoved);
				}
				else if(operator == "*") {
					vysledok = jedna * dva;
					odpoved = String.format("%.2f", vysledok);
					textField.setText(odpoved);
				}
				else if(operator == "%") {
					vysledok = jedna / dva;
					odpoved = String.format("%.2f", vysledok);
					textField.setText(odpoved);
				}else if(operator == ".") {
					vysledok = Double.parseDouble(textField.getText());
					vysledok = vysledok * (-1);
					odpoved = String.format("%.2f", vysledok);
					textField.setText(odpoved);
				}
			}
		});
		btn_rovnasa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_rovnasa.setBounds(239, 306, 89, 76);
		contentPane.add(btn_rovnasa);
		
		JButton btn_minus = new JButton("-");
		btn_minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jedna = Double.parseDouble(textField.getText());
				textField.setText(" ");
				operator = "-"; 
			}
		});
		btn_minus.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_minus.setBounds(362, 160, 89, 76);
		contentPane.add(btn_minus);
		
		JButton btn_krat = new JButton("*");
		btn_krat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jedna = Double.parseDouble(textField.getText());
				textField.setText(" ");
				operator = "*"; 
			}
		});
		btn_krat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_krat.setBounds(362, 230, 89, 76);
		contentPane.add(btn_krat);
		
		JButton btn_deleno = new JButton("%");
		btn_deleno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jedna = Double.parseDouble(textField.getText());
				textField.setText(" ");
				operator = "%"; 
			}
		});
		btn_deleno.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_deleno.setBounds(362, 306, 89, 76);
		contentPane.add(btn_deleno);
		
		JButton btn_delete = new JButton("C");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btn_delete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_delete.setBounds(362, 11, 89, 76);
		contentPane.add(btn_delete);
	}
}
