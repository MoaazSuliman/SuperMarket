package com.supermarket.view.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.supermarket.model.Method;
import com.supermarket.model.Sale;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Print extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	private double totalAmount = 0.0;
	private ArrayList<Sale> sales = new ArrayList<>();

	private double payMoney;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Print frame = new Print(null, 0);
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
	public Print(ArrayList<Sale> s, double payMoney) {
//		set throw varaibale 
		initizalizeVariables(s, payMoney);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textPane = new JTextPane();
		textPane.setBounds(10, 11, 435, 400);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(textPane);

		drowBill();
	}

	public void drowBill() {
		textPane.setText("===================================");
		textPane.setText(textPane.getText() + "\n\t");
		textPane.setText(textPane.getText() + "$" + "سوبر ماركت العربي$");
		textPane.setText(textPane.getText());
		textPane.setText(textPane.getText() + "\n\t     01146443315\n");
		textPane.setText(textPane.getText() + "===================================");
		textPane.setText(textPane.getText() + "\n\t الاسم        السعر       الكميه" + "\n");
		textPane.setText(textPane.getText() + "=$=$=$=$=$=$=$=$=$=$=$=$=$=$=$=$=$=$=");
		for (int i = 0; i < sales.size(); i++) {
			textPane.setText(textPane.getText() + "\n\t  " + sales.get(i).getQuantity() + "             "
					+ sales.get(i).getPrice() + "           " + sales.get(i).getName());
			/* Total amount = quantity*price */
			if (sales.get(i).getC())
				totalAmount += sales.get(i).getPrice();
			else
				totalAmount += sales.get(i).getPrice() * sales.get(i).getQuantity();

			textPane.setText(
					textPane.getText() + "\n-------------------------------------------------------------------------");
			System.out.println(sales.get(i).toString());
		}
		textPane.setText(textPane.getText() + "\n\t" + totalAmount + "   : الاجمالي");
		textPane.setText(textPane.getText() + "\n\t" + payMoney + "   : المدفوع");
		textPane.setText(textPane.getText() + "\n\t" + (payMoney - totalAmount) + "   : الباقي");
		textPane.setText(textPane.getText() + "\n\t" + new Method().returnDate() + "   : التاريخ");
		textPane.setText(textPane.getText() + "\n===================================");
		textPane.setText(textPane.getText() + "\n \tعايزين نشوفك تااني");
		print();
	}

	private void print() {
		try {
//			textPane.setContentType("text/html");

			boolean done = textPane.print();
//			if (done) {
//				JOptionPane.showMessageDialog(null, "Printing is done");
//			} else {
//				JOptionPane.showMessageDialog(null, "Error while printing");
//			}
		} catch (Exception pex) {
			JOptionPane.showMessageDialog(null, "Error while printing");
			pex.printStackTrace();
		}
	}

	private void initizalizeVariables(ArrayList<Sale> s, double payMoney) {
		this.sales = s;
		this.payMoney = payMoney;
	}
}
