package com.supermarket.view.seller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.KiloSaleDatabase;
import com.supermarket.model.KiloSale;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManageKiloSale extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField namet;
	private JTextField idt;
	private JScrollPane scrollPane;
	private JButton refresh, add, update, delete;

	private String[] header = { "ID", "الاسم" };
	private String body[][];
	private KiloSaleDatabase saleDatabase = new KiloSaleDatabase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageKiloSale frame = new ManageKiloSale();
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
	public ManageKiloSale() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 821, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setBounds(409, 24, 1, 1);

		namet = new JTextField();
		namet.requestFocus();
		namet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					add.doClick();
			}
		});
		namet.setFont(new Font("Tahoma", Font.BOLD, 14));
		namet.setBounds(10, 179, 188, 29);
		contentPane.add(namet);
		namet.setColumns(10);

		JLabel lblNewLabel = new JLabel("الاسم");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 139, 133, 29);
		contentPane.add(lblNewLabel);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setBounds(60, 51, 113, 29);
		contentPane.add(lblId);

		idt = new JTextField();
		idt.requestFocus();
		idt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					namet.requestFocus();
				}
			}
		});
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setColumns(10);
		idt.setBounds(10, 86, 188, 29);
		contentPane.add(idt);

		add = new JButton("اضافة");
		add.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					update.requestFocus();
				}
			}
		});
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkWithoutid()) {
						saleDatabase.insertKiloSale(new KiloSale(namet.getText()));
						JOptionPane.showMessageDialog(null, "تمت الاضافة بنجاح");
						setTable();
						empty();
						namet.requestFocus();

					} else {
						JOptionPane.showMessageDialog(null, "ادخل الاسم");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		add.setFont(new Font("Tahoma", Font.BOLD, 20));
		add.setBounds(28, 299, 100, 40);
		contentPane.add(add);

		update = new JButton("تعديل");
		update.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					delete.requestFocus();
				}
			}
		});
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkWithId()) {
						System.out.println(idt.getText());
						int id = Integer.parseInt(idt.getText());
						System.out.println(id);
						saleDatabase.updateKiloSale(new KiloSale(id, namet.getText()));
						JOptionPane.showMessageDialog(null, "تم التعديل بنجاح");
						refresh.doClick();
						empty();
					} else {
						JOptionPane.showMessageDialog(null, "حثل فارغ!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		update.setFont(new Font("Tahoma", Font.BOLD, 20));
		update.setBounds(28, 364, 100, 40);
		contentPane.add(update);

		delete = new JButton("حذف");
		delete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkId()) {
						saleDatabase.deleteKiloSale(Integer.parseInt(idt.getText()));
						JOptionPane.showMessageDialog(null, "تم الحذف بنجاح");
						refresh.doClick();
						empty();
					} else {
						JOptionPane.showMessageDialog(null, "حثل فارغ!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		delete.setFont(new Font("Tahoma", Font.BOLD, 20));
		delete.setBounds(28, 441, 100, 40);
		contentPane.add(delete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 51, 548, 599);
		contentPane.add(scrollPane);
		setTable();

		refresh = new JButton("اعاده التحميل");
		refresh.setFont(new Font("Tahoma", Font.BOLD, 20));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageKiloSale().setVisible(true);
			}
		});
		refresh.setBounds(597, 5, 198, 43);
		contentPane.add(refresh);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ManageKiloSale.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton_1.setBounds(10, 7, 58, 23);
		contentPane.add(btnNewButton_1);

		idt.requestFocus();
		// notFocus();
	}

	/****************************************************/
	private void setTable() {
		try {
			ArrayList<KiloSale> sales = saleDatabase.getAllKillSales();
			int len = sales.size();
			body = new String[len][2];
			for (int i = 0; i < len; i++) {
				body[i][0] = String.valueOf(sales.get(i).getId());
				body[i][1] = sales.get(i).getName();
			}
			table = new JTable(body, header);
			table.setRowHeight(25);
			table.setFont(new Font("Tahoma", Font.BOLD, 20));
			scrollPane.setViewportView(table);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableMouseClicked();
				}
			});
			table.getTableHeader().setForeground(Color.red);
			table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 40));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean checkWithoutid() {
		return (namet.getText().isEmpty()) ? false : true;
	}

	private boolean checkWithId() {
		return (idt.getText().isEmpty() || !checkWithoutid()) ? false : true;
	}

	private boolean checkId() {
		return (idt.getText().isEmpty()) ? false : true;
	}

	private void tableMouseClicked() {
		int index = table.getSelectedRow();
		idt.setText(body[index][0]);
		namet.setText(body[index][1]);

	}

	private void empty() {
		idt.setText("");
		namet.setText("");
	}

	private void notFocus() {
		idt.setFocusable(false);
		namet.setFocusable(false);
		add.setFocusable(false);
		update.setFocusable(false);
		delete.setFocusable(false);
	}
}
