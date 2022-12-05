package com.supermarket.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.ManageProductDatabase;
import com.supermarket.model.Product;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Notification extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane = new JScrollPane();

	private String header[] = { "المنتج", "الكميه" };
	private String body[][];
	private ManageProductDatabase productDatabase = new ManageProductDatabase();
	private ArrayList<Product> products = new ArrayList();
	private ArrayList<Product> productsThatQuantityLessThanTen = new ArrayList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notification frame = new Notification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Notification() throws SQLException {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 480, 424);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnNewButton.setIcon(new ImageIcon(Notification.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton.setBounds(10, 11, 89, 43);
		contentPane.add(btnNewButton);
		setTable();

	}

	private void setTable() throws SQLException {
		products = productDatabase.getProducts();
		int len = products.size();
		for (int i = 0; i < len; i++) {
			if (products.get(i).getQuantity() <= 10)
				productsThatQuantityLessThanTen.add(products.get(i));
		}
		len = productsThatQuantityLessThanTen.size();
		body = new String[len][2];
		for (int i = 0; i < len; i++) {
			body[i][0] = productsThatQuantityLessThanTen.get(i).getName();
			body[i][1] = String.valueOf(productsThatQuantityLessThanTen.get(i).getQuantity());
		}
		table = new JTable(body, header);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		setTableColors();
		scrollPane.setViewportView(table);

	}

	private void setTableColors() {
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(new Color(255, 69, 0));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setRowHeight(30);
	}

	public  boolean checkNot() {
		if (productsThatQuantityLessThanTen.size() >= 1)
			return true;
		return false;
	}
}
