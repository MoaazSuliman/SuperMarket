package com.supermarket.view.seller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.supermarket.database.KiloSaleDatabase;
import com.supermarket.database.ManageProductDatabase;
import com.supermarket.database.ManageSellerDatabase;
import com.supermarket.model.KiloSale;
import com.supermarket.model.Product;
import com.supermarket.model.Sale;
import com.supermarket.view.admin.ManageProduct;
import com.supermarket.view.admin.Notification;
import com.supermarket.view.admin.Print;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.autocomplete.AbstractAutoCompleteAdaptor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class ManageUserSeller extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String header1[] = { "ProdId", "الاسم", "االكميه", "السعر", "الصنف", "barcode" };
	private String body1[][];
	private ManageProductDatabase manageProductDatabase = new ManageProductDatabase();
	private ManageSellerDatabase sellerDatabase = new ManageSellerDatabase();
	private ArrayList<Product> products;
	public ArrayList<Sale> sales = new ArrayList<>();
	private ArrayList<KiloSale> salesK;
	private int rowForTable1 = -1;
	private int rowForTable2 = -1;
	private double allTotal = 0;
	private double payMoney = 0;
	private String names[];
	private KiloSaleDatabase saleDatabase = new KiloSaleDatabase();

	private JPanel panel = new JPanel();
	JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	private JButton addProduct;
	private JTextField quantityt;
	private JTextField idt;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton refresh;
	private JScrollPane scrollPane_2;
	private JButton search;
	private JTextField conditiont;
	private JTable table1;
	private JLabel lblNewLabel_3;
	private JTextField namet;
	private JLabel allTotalt;
	JTable table2 = new JTable();
	private JButton addProduct_1;
	private JButton done;
	private JTextField barcodet;
	private JTextField quantityt2;

	private JButton notification = new JButton();
	private JButton btnNewButton;

	private boolean flag = false;
	private JTextField textField;
	private JButton addProduct2;
	private JTextField payMoneyt;
	private JTextField gramt;
	private JTextField kilopricet;
	private JLabel lblBarcode_5;
	private JLabel kilogramt;
	private JLabel lblBarcode_4;
	private JButton addKilo;
	private JComboBox namesC;
	private JButton addProduct2_1;
	private JTextField test;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUserSeller frame = new ManageUserSeller(2);
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
	public ManageUserSeller(int sellerId) {

		setBackground(Color.LIGHT_GRAY);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.DARK_GRAY, 10, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(311, 50, 953, 206);
		contentPane.add(scrollPane_1);

		table1 = new JTable();
		try {
			setTable1();
		} catch (SQLException e) {
		}
		scrollPane_1.setViewportView(table1);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(30, 377, 253, 240);
		contentPane.add(panel_1);

		addProduct = new JButton("بيع");
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!idt.getText().isEmpty() || rowForTable1 != -1) {

						int quantityForSeller = Integer.parseInt(quantityt.getText());

						double total = (double) (products.get(rowForTable1).getPrice()) * quantityForSeller;
						Sale sale = new Sale(products.get(rowForTable1).getId(), products.get(rowForTable1).getName(),
								products.get(rowForTable1).getPrice(), quantityForSeller, total);
						allTotal += total;
						sales.add(sale);

						try {
							setTable2();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else
						JOptionPane.showMessageDialog(null, "اختر منتج ", "DONE", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});
		addProduct.setForeground(new Color(255, 69, 0));
		addProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		addProduct.setBackground(Color.BLACK);
		addProduct.setBounds(49, 143, 158, 34);
		panel_1.add(addProduct);

		quantityt = new JTextField();
		quantityt.setText("1");
		quantityt.setFont(new Font("Tahoma", Font.BOLD, 14));
		quantityt.setColumns(10);
		quantityt.setBounds(95, 100, 148, 20);
		panel_1.add(quantityt);

		idt = new JTextField();
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setColumns(10);
		idt.setBounds(95, 28, 148, 20);
		panel_1.add(idt);

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 31, 108, 14);
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("الكميه");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 102, 108, 17);
		panel_1.add(lblNewLabel_1);

		lblNewLabel_3 = new JLabel("الاسم");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 75, 108, 14);
		panel_1.add(lblNewLabel_3);

		namet = new JTextField();
		namet.setFont(new Font("Tahoma", Font.BOLD, 14));
		namet.setColumns(10);
		namet.setBounds(95, 69, 148, 20);
		panel_1.add(namet);

		addProduct_1 = new JButton("استرجاع");
		addProduct_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					rowForTable2 = table2.getSelectedRow();
					allTotal -= sales.get(rowForTable2).getTotal();
					sales.remove(rowForTable2);
					if (sales.size() == 0) {
						refresh.doClick();
					}
					setTable2();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		addProduct_1.setForeground(new Color(255, 69, 0));
		addProduct_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		addProduct_1.setBackground(Color.BLACK);
		addProduct_1.setBounds(49, 195, 158, 34);
		panel_1.add(addProduct_1);

		lblNewLabel_2 = new JLabel("البيع");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(578, 11, 170, 35);
		contentPane.add(lblNewLabel_2);

		refresh = new JButton("اعاده تحميل");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageUserSeller(sellerId).setVisible(true);
			}
		});
		refresh.setForeground(new Color(255, 69, 0));
		refresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		refresh.setBackground(Color.BLACK);
		refresh.setBounds(1121, 16, 143, 34);
		contentPane.add(refresh);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(311, 376, 953, 177);
		contentPane.add(scrollPane_2);

		search = new JButton("بحث");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setTable1AfterSearch();
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});
		search.setForeground(new Color(255, 69, 0));
		search.setFont(new Font("Tahoma", Font.BOLD, 18));
		search.setBackground(Color.BLACK);
		search.setBounds(1031, 23, 80, 23);
		contentPane.add(search);

		conditiont = new JTextField();
		conditiont.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					search.doClick();
			}
		});
		conditiont.setFont(new Font("Tahoma", Font.BOLD, 14));
		conditiont.setColumns(10);
		conditiont.setBounds(823, 23, 198, 23);
		contentPane.add(conditiont);

		allTotalt = new JLabel("السعر");
		allTotalt.setForeground(Color.RED);
		allTotalt.setFont(new Font("Algerian", Font.BOLD, 18));
		allTotalt.setBounds(648, 333, 509, 41);
		contentPane.add(allTotalt);

		done = new JButton("تمت المهمه");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sales.size() != 0) {
					try {
						payMoney = Double.parseDouble(payMoneyt.getText());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "ادخل المبلغ المدفوع");
					}
					new Print(sales, payMoney);
//				try {
//					MessageFormat footer = new MessageFormat(" االمبلغ الكلي :" + String.valueOf(allTotal));
//					boolean complete = table2.print(JTable.PrintMode.NORMAL, null, footer);
//					if (complete) {
//					} else {
//					}
//				} catch (PrinterException pe) {
//					System.out.println(pe.getMessage());
//				}
					try {
						sellerDatabase.setSalesForSeller(sellerId, allTotal);
						table2 = new JTable();
						setTableColor2();
						allTotalt.setText("السعر");
						scrollPane_2.setViewportView(table2);
						JOptionPane.showMessageDialog(null, "تم البيع بنجاح");
						setNewQuantityInDatabase();
						dispose();
						new ManageUserSeller(sellerId).setVisible(true);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "لا يوجد مبيعات");
					refresh.doClick();
				}
			}
		});
		done.setForeground(new Color(255, 69, 0));
		done.setFont(new Font("Tahoma", Font.BOLD, 18));
		done.setBackground(Color.BLACK);
		done.setBounds(578, 616, 253, 34);
		contentPane.add(done);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(30, 16, 253, 240);
		contentPane.add(panel_1_1);

		addProduct2 = new JButton("بيع");
		addProduct2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!barcodet.getText().isEmpty()) {
						String barcode = barcodet.getText();
						Product product = manageProductDatabase.getProductWithBarcode(barcode);
						if (product != null) {
							int quantity = Integer.parseInt(quantityt2.getText());
							int product_quantity = product.getQuantity();
							// check if there enough products
							if (quantity <= product_quantity) {
								Sale sale = new Sale(product.getId(), product.getName(), product.getPrice(), quantity,
										product.getPrice() * (quantity));
								sales.add(sale);
								allTotal += product.getPrice() * quantity;
								setTable2();
								barcodet.requestFocus();
								barcodet.setText("");
								quantityt2.setText("1");
							} else
								JOptionPane.showMessageDialog(null, "الكميه غير كافيه");
						} else
							JOptionPane.showMessageDialog(null, "BARCODE خطا");
					} else
						JOptionPane.showMessageDialog(null, "ادخل ال BARCODE");
				} catch (SQLException ex) {
					// TODO: handle exception
				}
			}
		});
		addProduct2.setForeground(new Color(255, 69, 0));
		addProduct2.setFont(new Font("Tahoma", Font.BOLD, 18));
		addProduct2.setBackground(Color.BLACK);
		addProduct2.setBounds(47, 173, 158, 34);
		panel_1_1.add(addProduct2);

		barcodet = new JTextField();
		barcodet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					addProduct2.doClick();
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					quantityt2.requestFocus();
				}

			}
		});
		barcodet.setFont(new Font("Tahoma", Font.BOLD, 14));
		barcodet.setColumns(10);
		barcodet.requestFocus();
		barcodet.setBounds(95, 28, 148, 20);
		panel_1_1.add(barcodet);

		JLabel lblBarcode = new JLabel("barcode");
		lblBarcode.setForeground(Color.RED);
		lblBarcode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBarcode.setBounds(10, 31, 108, 14);
		panel_1_1.add(lblBarcode);

		JLabel lblBarcode_1 = new JLabel("الكميه");
		lblBarcode_1.setForeground(Color.RED);
		lblBarcode_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBarcode_1.setBounds(10, 99, 108, 17);
		panel_1_1.add(lblBarcode_1);

		quantityt2 = new JTextField();
		quantityt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					addProduct2.doClick();
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					kilopricet.requestFocus();

			}
		});
		quantityt2.setText("1");
		quantityt2.setFont(new Font("Tahoma", Font.BOLD, 14));
		quantityt2.setColumns(10);
		quantityt2.setBounds(95, 97, 148, 20);
		panel_1_1.add(quantityt2);

		payMoneyt = new JTextField();
		payMoneyt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					done.doClick();
			}
		});
		payMoneyt.setFont(new Font("Tahoma", Font.BOLD, 18));
		payMoneyt.setColumns(10);
		payMoneyt.setBounds(535, 570, 178, 35);
		contentPane.add(payMoneyt);

		JLabel lblBarcode_2 = new JLabel("المبلغ المدفوع");
		lblBarcode_2.setForeground(Color.RED);
		lblBarcode_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBarcode_2.setBounds(723, 574, 143, 31);
		contentPane.add(lblBarcode_2);

		gramt = new JTextField();
		gramt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					namesC.requestFocus();
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					namesC.requestFocus();
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					payMoneyt.requestFocus();
			}
		});
		gramt.setFont(new Font("Tahoma", Font.BOLD, 14));
		gramt.setColumns(10);
		gramt.setBounds(558, 302, 148, 20);
		contentPane.add(gramt);

		kilopricet = new JTextField();
		kilopricet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					gramt.requestFocus();
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					gramt.requestFocus();
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					payMoneyt.requestFocus();
			}
		});
		kilopricet.setFont(new Font("Tahoma", Font.BOLD, 14));
		kilopricet.setColumns(10);
		kilopricet.setBounds(314, 302, 148, 20);
		contentPane.add(kilopricet);

		lblBarcode_5 = new JLabel("الاسم");
		lblBarcode_5.setForeground(Color.RED);
		lblBarcode_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBarcode_5.setBounds(1031, 291, 108, 31);
		contentPane.add(lblBarcode_5);

		kilogramt = new JLabel("الجرام");
		kilogramt.setForeground(Color.RED);
		kilogramt.setFont(new Font("Tahoma", Font.BOLD, 14));
		kilogramt.setBounds(723, 291, 80, 31);
		contentPane.add(kilogramt);

		lblBarcode_4 = new JLabel("السعر");
		lblBarcode_4.setForeground(Color.RED);
		lblBarcode_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBarcode_4.setBounds(483, 297, 65, 31);
		contentPane.add(lblBarcode_4);

		addKilo = new JButton("اضافة");
		addKilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkKilos()) {
						String name = getNameFromNamesC();
						double price = Double.parseDouble(kilopricet.getText());
						double gram = Double.parseDouble(gramt.getText());
						Sale sale = new Sale(0, name, price, gram, price);
						sale.setC(true);
						sales.add(sale);
						allTotal += price;
						setTable2();
						kilopricet.setText("");
						gramt.setText("");
						barcodet.requestFocus();
					} else
						JOptionPane.showMessageDialog(null, "حقل فارغ");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		addKilo.setForeground(new Color(255, 69, 0));
		addKilo.setFont(new Font("Tahoma", Font.BOLD, 18));
		addKilo.setBackground(Color.BLACK);
		addKilo.setBounds(1106, 294, 158, 34);
		contentPane.add(addKilo);

		namesC = new JComboBox();
		namesC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//					namesC.setFocusable(false);
//					kilogramt.requestFocus();
					addKilo.doClick();
				}
			}
		});
		setNames();
		namesC.setModel(new DefaultComboBoxModel(names));
		namesC.setBounds(781, 297, 219, 22);
		AutoCompleteDecorator.decorate(namesC);
		contentPane.add(namesC);

		addProduct2_1 = new JButton("الكيلوهات");
		addProduct2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageKiloSale().setVisible(true);
			}
		});
		addProduct2_1.setForeground(new Color(255, 69, 0));
		addProduct2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		addProduct2_1.setBackground(Color.BLACK);
		addProduct2_1.setBounds(140, 288, 143, 34);
		contentPane.add(addProduct2_1);

		try {
			setTable1();
//			setTable2();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// tables
		setTableColors();

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
		try {
			if (new Notification().checkNot()) {
				notification.setIcon(
						new ImageIcon(ManageProduct.class.getResource("/com/supermarket/view/admin/not2.png")));
				notification.setBounds(40, 277, 71, 81);
				notification.setBackground(Color.RED);
				contentPane.add(notification);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// Functions That Service This Frame

	private void setTable1() throws SQLException {
		products = manageProductDatabase.getProducts();
		setDefaultTable1();

	}

	private void setTable1AfterSearch() throws SQLException {
		products = manageProductDatabase.getProductsAfterSearch(conditiont.getText());
		setDefaultTable1();
	}

	private void setDefaultTable1() {
		int len = products.size();
		body1 = new String[len][6];
		for (int i = 0; i < len; i++) {
			body1[i][0] = String.valueOf(products.get(i).getId());
			body1[i][1] = products.get(i).getName();
			body1[i][2] = String.valueOf(products.get(i).getQuantity());
			body1[i][3] = String.valueOf(products.get(i).getPrice());
			body1[i][4] = products.get(i).getCategorie_name();
			body1[i][5] = products.get(i).getBarcode();
		}
		table1 = new JTable(body1, header1);
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setValuesInTexts();
			}
		});
		setTableColors();
		scrollPane_1.setViewportView(table1);
	}

	public void setTableColors() {
		table1.setBackground(Color.LIGHT_GRAY);
		table1.setForeground(Color.black);
		table1.getTableHeader().setBackground(Color.black);
		table1.getTableHeader().setForeground(new Color(255, 69, 0));
		table1.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table1.setRowHeight(30);
		table1.setFont(new Font("Thaoma", Font.BOLD, 25));
	}

	private void setScrollPaneForTable1() {
		scrollPane.setBounds(10, 38, 1234, 272);

	}

	private void setValuesInTexts() {
		rowForTable1 = table1.getSelectedRow();
		idt.setText(String.valueOf(products.get(rowForTable1).getId()));
		namet.setText(products.get(rowForTable1).getName());
		barcodet.setText((products.get(rowForTable1).getBarcode()));
	}

	// every thing about table 2
	private void setTable2() throws SQLException {
		int len = sales.size();
		String header[] = { "الاسم", "السعر", "الكميه", "الاجمالي" };
		String body[][] = new String[len][4];
		for (int i = 0; i < len; i++) {
			body[i][0] = sales.get(i).getName();
			body[i][1] = String.valueOf(sales.get(i).getPrice());
			body[i][2] = String.valueOf(sales.get(i).getQuantity());
			body[i][3] = String.valueOf(sales.get(i).getTotal());

		}
		allTotalt.setText(String.valueOf(allTotal));
		table2 = new JTable(body, header);
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		setTableColor2();
		scrollPane_2.setViewportView(table2);
	}

	private void setTableColor2() {
		table2.setBackground(Color.LIGHT_GRAY);
		table2.setForeground(Color.black);
		table2.getTableHeader().setBackground(Color.black);
		table2.getTableHeader().setForeground(new Color(255, 69, 0));
		table2.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table2.setRowHeight(30);
		table2.setFont(new Font("Thaoma", Font.BOLD, 25));
	}

	private void setNewQuantityInDatabase() throws SQLException {
		for (int i = 0; i < sales.size(); i++) {
			sales.get(i).getProduct_id();
			Product product = manageProductDatabase.getProductById(sales.get(i).getProduct_id());
			int quantityForProduct = product.getQuantity();
			double quantityForSale = sales.get(i).getQuantity();
			double quantity = quantityForProduct - quantityForSale;
			manageProductDatabase.updateQuantity(product.getId(), quantity);
		}

	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				addProduct2.doClick();
				System.out.println("zoza");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void setNames() {
		try {
			salesK = saleDatabase.getAllKillSales();
			int len = salesK.size();
			names = new String[len];
			for (int i = 0; i < len; i++)
				names[i] = salesK.get(i).getName();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean checkKilos() {
		return (kilogramt.getText().isEmpty() || kilopricet.getText().isEmpty()) ? false : true;
	}

	private String getNameFromNamesC() {
		int index = namesC.getSelectedIndex();
		return salesK.get(index).getName();
	}
}
