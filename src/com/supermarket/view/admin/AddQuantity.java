package com.supermarket.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.ManageProductDatabase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddQuantity extends JFrame {

	private JPanel contentPane;
	private JTextField barcodet;
	private JTextField quantityt;
	private JTextField textField;
	private JButton addQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddQuantity frame = new AddQuantity();
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
	public AddQuantity() {
		setUndecorated(true);
		setTitle("اضافه كميه");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("barcode");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(27, 117, 135, 14);
		contentPane.add(lblNewLabel);

		barcodet = new JTextField();
		barcodet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					quantityt.requestFocus();
			}
		});
		barcodet.setFont(new Font("Tahoma", Font.BOLD, 16));
		barcodet.setBounds(171, 114, 237, 20);
		contentPane.add(barcodet);
		barcodet.setColumns(10);
		barcodet.requestFocus();

		JLabel lblNewLabel_1 = new JLabel("الكميه");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(27, 171, 135, 20);
		contentPane.add(lblNewLabel_1);

		quantityt = new JTextField();
		quantityt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					addQuantity.doClick();
			}
		});
		quantityt.setFont(new Font("Tahoma", Font.BOLD, 16));
		quantityt.setColumns(10);
		quantityt.setBounds(171, 168, 237, 23);
		contentPane.add(quantityt);

		addQuantity = new JButton("اضافه الكميه");
		addQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (check()) {
						int quantity = Integer.parseInt(quantityt.getText());
						String barcode = barcodet.getText();
						new ManageProductDatabase().addQuantityForProductByBarcode(barcode, quantity);
						JOptionPane.showMessageDialog(null, "تم الاضافه بنجاح");
						dispose();
						new AddQuantity().setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "الحقل فارغ");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		addQuantity.setForeground(Color.RED);
		addQuantity.setBackground(new Color(0, 51, 51));
		addQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		addQuantity.setBounds(42, 231, 366, 45);
		contentPane.add(addQuantity);

		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(55, 54, 353, 36);
		textField.setText("من هنا يمكنك اضافه \n كمبه لمنتج معين عن طريق الباركود");
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AddQuantity.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton_1.setBounds(0, 0, 67, 51);
		contentPane.add(btnNewButton_1);
	}

	private boolean check() {
		if (quantityt.getText().isEmpty() || barcodet.getText().isEmpty())
			return false;
		return true;
	}
}
