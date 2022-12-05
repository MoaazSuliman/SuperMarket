package com.supermarket.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.ManageCategorieDatabase;
import com.supermarket.database.ManageProductDatabase;
import com.supermarket.model.Categorie;
import com.supermarket.model.Product;
import com.supermarket.view.seller.ManageUserSeller;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManageProduct extends JFrame {

	private JPanel contentPane;
	private String header[] = { "ProdId", "الاسم", "االكميه", "السعر", "النوع", "التاريخ", "barcode" };
	private String body[][];
	private JTable table;
	private JTextField quantityt;
	private JTextField namet;
	private JTextField idt;
	private JTextField pricet;
	private JButton delete;
	private JScrollPane sc;
	private JButton notification = new JButton();

	private ArrayList<Categorie> categories;
	private ArrayList<Product> products;
	private JComboBox comboBox = new JComboBox();
	private ManageProductDatabase manageProductDatabase = new ManageProductDatabase();
	private String name, categorie, barcode;
	private int id, quantity;
	private double price;
	private boolean valid[] = new boolean[10000000];
	private JTextField conditiont;
	private JTextField barcodet;

	private double allTotal;
	private JTextField textField;
	private JTextField searchBarcodet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProduct frame = new ManageProduct();
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
	public ManageProduct() throws SQLException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 1401, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(23, 92, 310, 558);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(10, 85, 81, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("الاسم");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(10, 130, 90, 31);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("الكميه");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(10, 183, 90, 31);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("السعر");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setBounds(10, 240, 90, 31);
		panel.add(lblNewLabel_4);

		JButton add = new JButton("اضافه");
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					try {
						intialize();
						manageProductDatabase.insertProduct(name, quantity, price, categorie, barcode);
						JOptionPane.showMessageDialog(null, "تمت الاضافه بنجاح", "DONE",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageProduct().setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "حقل فاارغ", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
		});
		add.setForeground(new Color(255, 69, 0));
		add.setBounds(106, 436, 89, 50);
		panel.add(add);

		JButton edit = new JButton("تعديل");
		edit.setFont(new Font("Tahoma", Font.BOLD, 18));
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check() && checkId()) {
					try {
						intialize();
						manageProductDatabase.updateProduct(id, name, quantity, price, categorie, barcode);
						JOptionPane.showMessageDialog(null, "تم التعديل", "DONE", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageProduct().setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "حقل فارغ", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
		});
		edit.setForeground(new Color(255, 69, 0));
		edit.setBounds(35, 497, 89, 50);
		panel.add(edit);

		delete = new JButton("حذف");
		delete.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (checkId()) {
					try {
						id = Integer.parseInt(idt.getText());
						manageProductDatabase.deleteProduct(id);
						JOptionPane.showMessageDialog(null, "DELETED PRODUCT", "DONE", JOptionPane.WARNING_MESSAGE);
						dispose();
						new ManageProduct().setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage().toUpperCase(), "ERROR",
								JOptionPane.WARNING_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "اختر المنتج او قوم بادخال ال ID", "ERROR",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		delete.setForeground(new Color(255, 69, 0));
		delete.setBounds(188, 497, 89, 50);
		panel.add(delete);

		quantityt = new JTextField();
		quantityt.setFont(new Font("Tahoma", Font.BOLD, 14));
		quantityt.setColumns(10);
		quantityt.setBounds(101, 183, 199, 34);
		panel.add(quantityt);

		namet = new JTextField();
		namet.setFont(new Font("Tahoma", Font.BOLD, 14));
		namet.setColumns(10);
		namet.setBounds(101, 130, 199, 36);
		panel.add(namet);

		idt = new JTextField();
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setColumns(10);
		idt.setBounds(101, 82, 199, 20);
		panel.add(idt);

		pricet = new JTextField();
		pricet.setFont(new Font("Tahoma", Font.BOLD, 14));
		pricet.setColumns(10);
		pricet.setBounds(101, 237, 199, 34);
		panel.add(pricet);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));

		comboBox.setBounds(99, 310, 201, 31);
		panel.add(comboBox);

		sc = new JScrollPane();
		sc.setSize(1017, 558);
		sc.setLocation(343, 56);
		getContentPane().add(sc);
		// define table
		try {
			getCategories();
			// INSERT VALUE IN CATEGORIE COMBOBOX.
			insertTableValues();// INSERT TABLE VALUES .

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//		sc = new JScrollPane();
//		sc.setSize(931, 594);
//		sc.setLocation(343, 56);
//		getContentPane().add(sc);
//		sc.setViewportView(table);

		JButton refresh = new JButton("اعاده تحميل");
		refresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new ManageProduct().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refresh.setBackground(new Color(0, 0, 0));
		refresh.setForeground(new Color(255, 69, 0));
		refresh.setBounds(1217, 11, 143, 34);
		contentPane.add(refresh);

		JLabel lblNewLabel = new JLabel("اداره المخزن");
		lblNewLabel.setBounds(531, 11, 199, 42);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_5 = new JLabel("المنتج");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_5.setBounds(95, 23, 182, 35);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_4_1 = new JLabel("النوع");
		lblNewLabel_4_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(10, 309, 90, 31);
		panel.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("barcode");
		lblNewLabel_4_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_2.setBounds(10, 381, 90, 31);
		panel.add(lblNewLabel_4_2);

		barcodet = new JTextField();
		barcodet.setFont(new Font("Tahoma", Font.BOLD, 14));
		barcodet.setColumns(10);
		barcodet.setBounds(101, 378, 199, 34);
		panel.add(barcodet);
		lblNewLabel.setForeground(new Color(255, 0, 0));

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));

		JButton search = new JButton("");
		search.setIcon(new ImageIcon(ManageProduct.class.getResource("/com/supermarket/view/admin/search.png")));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertTableValuesAfterSearchByName();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		search.setForeground(new Color(255, 69, 0));
		search.setFont(new Font("Tahoma", Font.BOLD, 18));
		search.setBackground(Color.BLACK);
		search.setBounds(1173, 33, 34, 20);
		contentPane.add(search);

		conditiont = new JTextField();
		conditiont.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					search.doClick();
			}
		});
		conditiont.setFont(new Font("Tahoma", Font.BOLD, 14));
		conditiont.setBounds(980, 33, 183, 20);
		contentPane.add(conditiont);
		conditiont.setColumns(10);

		notification = new JButton("");

		notification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Notification().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		notification.setForeground(new Color(255, 69, 0));
		notification.setFont(new Font("Tahoma", Font.BOLD, 15));
		if (new Notification().checkNot()) {
			notification
					.setIcon(new ImageIcon(ManageProduct.class.getResource("/com/supermarket/view/admin/not2.png")));
			notification.setBounds(192, 0, 71, 81);
			notification.setBackground(Color.RED);
			contentPane.add(notification);
		}
		JButton add_1 = new JButton("الانواع");
		add_1.setBackground(Color.BLACK);
		add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageCategorie().setVisible(true);
			}
		});
		add_1.setForeground(Color.RED);
		add_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		add_1.setBounds(93, 3, 89, 50);
		contentPane.add(add_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Base().setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageProduct.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton.setBounds(0, 0, 89, 45);
		contentPane.add(btnNewButton);

		JButton delete_1 = new JButton("اضافه كميه");
		delete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddQuantity().setVisible(true);
			}
		});
		delete_1.setForeground(new Color(255, 69, 0));
		delete_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete_1.setBounds(368, 3, 143, 50);
		contentPane.add(delete_1);

		JButton searchBarcode = new JButton("");
		searchBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertTableValuesAfterSearchByBarcode();
				} catch (Exception ex) {

				}
			}
		});
		searchBarcode.setIcon(new ImageIcon(ManageProduct.class.getResource("/com/supermarket/view/admin/search.png")));
		searchBarcode.setForeground(new Color(255, 69, 0));
		searchBarcode.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchBarcode.setBackground(Color.BLACK);
		searchBarcode.setBounds(936, 33, 34, 20);
		contentPane.add(searchBarcode);

		searchBarcodet = new JTextField();
		searchBarcodet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					searchBarcode.doClick();
			}
		});
		searchBarcodet.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBarcodet.setColumns(10);
		searchBarcodet.setBounds(743, 33, 183, 20);
		contentPane.add(searchBarcodet);

		JLabel lblNewLabel_2_1 = new JLabel("الاسم");
		lblNewLabel_2_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(1036, 10, 90, 20);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("barcode");
		lblNewLabel_2_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(790, 10, 90, 20);
		contentPane.add(lblNewLabel_2_1_1);

		setTableColors();

	}

	// Functions That Service This Frame

	private void getCategories() throws SQLException {
		categories = new ManageCategorieDatabase().getCategories();
		insertCategories();
	}

	private void insertCategories() {
		String categorieValues[];
		int len = categories.size();
		categorieValues = new String[len];
		for (int i = 0; i < len; i++) {
			categorieValues[i] = categories.get(i).getName();
		}
		comboBox.setModel(new DefaultComboBoxModel(categorieValues));
	}

	private void insertTableValues() throws SQLException {
		products = manageProductDatabase.getProducts();
		setDefaultTable();
	}

	private void insertTableValuesAfterSearchByName() throws SQLException {
		products = manageProductDatabase.getProductsAfterSearch(conditiont.getText());
		setDefaultTable();
	}

	private void insertTableValuesAfterSearchByBarcode() throws SQLException {
		products = manageProductDatabase.getProductsAfterSearchByBarcode(searchBarcodet.getText());
		setDefaultTable();
	}

	private void setDefaultTable() {
		int len = products.size();
		body = new String[len][7];
		allTotal = 0;
		for (int i = 0; i < len; i++) {
			valid[products.get(i).getId()] = true;
			body[i][0] = String.valueOf(products.get(i).getId());
			body[i][1] = products.get(i).getName();
			body[i][2] = String.valueOf(products.get(i).getQuantity());
			body[i][3] = String.valueOf(products.get(i).getPrice());
			body[i][4] = String.valueOf(products.get(i).getCategorie_name());
			body[i][5] = String.valueOf(products.get(i).getDate());
			body[i][6] = products.get(i).getBarcode();
			allTotal += products.get(i).getPrice() * products.get(i).getQuantity();
		}
		setTotal();
		table = new JTable(body, header);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		// to set values in text fields when admin select row
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int categorieIndex = findCategorieIndex(products.get(row).getCategorie_name());
				idt.setText(String.valueOf(products.get(row).getId()));
				namet.setText(products.get(row).getName());
				quantityt.setText(String.valueOf(products.get(row).getQuantity()));
				pricet.setText(String.valueOf(products.get(row).getPrice()));
				comboBox.setSelectedIndex(categorieIndex);
				barcodet.setText(products.get(row).getBarcode());

			}
		});
		setTableColors();

		setScreen();
//		sc = new JScrollPane(table);
//		sc.setSize(799, 594);
//		sc.setLocation(475, 56);
//		getContentPane().add(sc);
	}

// initialize information in varivables
	private void intialize() throws Exception {

		int index = comboBox.getSelectedIndex();
		categorie = categories.get(index).getName();
		if (checkId())
			id = Integer.parseInt(idt.getText());
		name = namet.getText();
		price = Double.parseDouble(pricet.getText());
		quantity = Integer.parseInt(quantityt.getText());
		barcode = barcodet.getText();

	}

	// to check all textfeild not empty
	private boolean check() {

		if (namet.getText().isEmpty() || pricet.getText().isEmpty() || quantityt.getText().isEmpty()
				|| barcodet.getText().isEmpty())
			return false;
		return true;
	}

	// to know if i will call method with id or without.
	private boolean checkId() {
		if (idt.getText().isEmpty())
			return false;
		return true;
	}

	// to set comboBox index when admn select product
	private int findCategorieIndex(String categorieName) {
		for (int i = 0; i < categories.size(); i++)
			if (categories.get(i).getName().equals(categorieName))
				return i;
		return 0;
	}

	public void setScreen() {

		sc.setViewportView(table);
	}

	public void setTableColors() {
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(new Color(255, 69, 0));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setRowHeight(30);

	}

	private void setTotal() {

		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setColumns(10);
		textField.setBounds(554, 616, 580, 34);
		textField.setText(String.valueOf(allTotal));
		contentPane.add(textField);
	}
}
//insertTableValues();
//setTableColors();
//setScreen();