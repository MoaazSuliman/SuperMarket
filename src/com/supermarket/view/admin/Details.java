package com.supermarket.view.admin;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.ManageSellerDatabase;
import com.supermarket.model.SellerSale;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Details extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;

	private ArrayList<SellerSale> salesForSeller = new ArrayList<>();
	private ArrayList<SellerSale> salesForSellerFromAndTo = new ArrayList<>();
	private String header[] = { "التاريخ", "االمبلغ الكلي لزبون محدد" };
	private String[][] body;
	private ManageSellerDatabase sellerDatabase = new ManageSellerDatabase();
	private int sellerId;
	private double totalAmoutForSeller = 0;

	private JTextField datet;
	private JTable table;
	private JLabel totalt;
	private JTextField fromt;
	private JTextField tot;
	private JButton refresh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Details frame = new Details(3);
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
	public Details(int seller_id) {
		setUndecorated(true);
		sellerId = seller_id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 657, 600	);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 621, 457);
		contentPane.add(scrollPane);

		try {
			setTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		datet = new JTextField();
		datet.setFont(new Font("Tahoma", Font.BOLD, 14));
		datet.setBounds(115, 44, 135, 20);
		contentPane.add(datet);
		datet.setColumns(10);

		JButton search = new JButton("");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setTableAfterSearch();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		search.setIcon(new ImageIcon(Details.class.getResource("/com/supermarket/view/admin/search.png")));
		search.setBounds(260, 44, 30, 23);
		contentPane.add(search);

		JLabel lblNewLabel = new JLabel("التاريخ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(47, 46, 58, 17);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Details.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton.setBounds(0, 0, 74, 29);
		contentPane.add(btnNewButton);

		JButton search2 = new JButton("");
		search2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					contentPane.remove(totalt);
					setTableAfterSearchFromAndTo();
				} catch (Exception ex) {

				}
			}
		});
		search2.setIcon(new ImageIcon(Details.class.getResource("/com/supermarket/view/admin/search.png")));
		search2.setBounds(420, 44, 30, 23);
		contentPane.add(search2);

		fromt = new JTextField();
		fromt.setFont(new Font("Tahoma", Font.BOLD, 14));
		fromt.setColumns(10);
		fromt.setBounds(455, 9, 135, 20);
		contentPane.add(fromt);

		tot = new JTextField();
		tot.setFont(new Font("Tahoma", Font.BOLD, 14));
		tot.setColumns(10);
		tot.setBounds(456, 47, 135, 20);
		contentPane.add(tot);

		JLabel lblNewLabel_1 = new JLabel("من");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(600, 11, 46, 18);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("الي");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(601, 49, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		refresh = new JButton("اعاده تحميل");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Details(sellerId).setVisible(true);
			}
		});
		refresh.setForeground(new Color(255, 69, 0));
		refresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		refresh.setBackground(Color.BLACK);
		refresh.setBounds(241, 1, 143, 34);
		contentPane.add(refresh);

	}

	private void setTableAfterSearch() throws SQLException {
		salesForSeller = sellerDatabase.getAllSalesForSeller(sellerId, datet.getText());
		contentPane.remove(totalt);
		setDefaultTable();
	}

	private void setTableAfterSearchFromAndTo() throws SQLException {
		salesForSeller = sellerDatabase.getAllSalesForSeller(sellerId);
		String from = fromt.getText(), to = tot.getText();
		int i = 0;
		int counter = 0;
		boolean flag = false;
		boolean flag2 = false;
		for (; i < salesForSeller.size(); i++) {
			if (salesForSeller.get(i).getDate().equals(from)) {
				flag = true;
			}

			if (!flag2 && salesForSeller.get(i).getDate().equals(to))
				flag2 = true;
			if (flag2 && !salesForSeller.get(i).getDate().equals(to))
				break;
			if (flag) {
				salesForSellerFromAndTo.add(salesForSeller.get(i));
				counter++;
			}
		}
		setDefaultTableFromAndTo();
	}

	private void setTable() throws SQLException {
		salesForSeller = sellerDatabase.getAllSalesForSeller(sellerId);
		setDefaultTable();
	}

	private void setDefaultTable() {
		totalAmoutForSeller = 0;

		int len = salesForSeller.size();
		body = new String[len][2];
		totalAmoutForSeller = 0;
		for (int i = 0; i < len; i++) {
			body[i][0] = salesForSeller.get(i).getDate();
			body[i][1] = String.valueOf(salesForSeller.get(i).getMoney());
			totalAmoutForSeller += salesForSeller.get(i).getMoney();
		}
		table = new JTable(body, header);
		setTableColor();
		setTotalAmount();
		scrollPane.setViewportView(table);
	}

	private void setDefaultTableFromAndTo() {
		totalAmoutForSeller = 0;
		int len = salesForSellerFromAndTo.size();
		body = new String[len][2];
		for (int i = 0; i < len; i++) {
			body[i][0] = salesForSeller.get(i).getDate();
			body[i][1] = String.valueOf(salesForSeller.get(i).getMoney());
			totalAmoutForSeller += salesForSeller.get(i).getMoney();
		}
		table = new JTable(body, header);
		setTableColor();
		setTotalAmount();
		scrollPane.setViewportView(table);
	}

	private void setTableColor() {
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(new Color(255, 69, 0));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setRowHeight(30);
		table.setFont(new Font("Thaoma", Font.BOLD, 25));
	}

	private void setTotalAmount() {

		totalt = new JLabel("");
		totalt.setForeground(Color.RED);
		totalt.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalt.setBounds(211, 546, 210, 43);
		contentPane.add(totalt);
		totalt.setText(String.valueOf(totalAmoutForSeller));
	}
}
