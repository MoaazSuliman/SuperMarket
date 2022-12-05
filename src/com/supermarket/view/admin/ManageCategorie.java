package com.supermarket.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import com.supermarket.database.ManageCategorieDatabase;
import com.supermarket.model.Categorie;
import com.supermarket.view.seller.ManageUserSeller;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class ManageCategorie extends JFrame {

	private JPanel contentPane;
	private String header[] = { "CatId", "الاسم", "الوصف" };
	private String body[][];
	private JTable table;
	private JTextField descriptiont;
	private JTextField namet;
	private JTextField idt;
	private JScrollPane sc;
	private int id;
	private String name, description;
	private ArrayList<Categorie> values;
	private boolean validInput = true;
	private boolean valid[] = new boolean[100000000];
	// to use manageCategorieDatabase Functions
	private ManageCategorieDatabase manageCategorieDatabase = new ManageCategorieDatabase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCategorie frame = new ManageCategorie();
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
	public ManageCategorie() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 56, 310, 594);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(10, 94, 80, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("الاسم");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(10, 142, 80, 36);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("الوصف");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(10, 203, 80, 37);
		panel.add(lblNewLabel_3);

		JButton add = new JButton("اضافه");
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					try {
						intizalize();
						manageCategorieDatabase.insertCategorie(name, description);
						JOptionPane.showMessageDialog(null, "تمت الاضافه", "DONE", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageCategorie().setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "حقل فارغ", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
		});
		add.setForeground(new Color(255, 69, 0));
		add.setBounds(109, 331, 89, 50);
		panel.add(add);

		JButton edit = new JButton("تعديل");
		edit.setFont(new Font("Tahoma", Font.BOLD, 18));
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check() && checkId()) {
					try {
						intizalize();
						manageCategorieDatabase.updateCategorie(id, name, description);
						JOptionPane.showMessageDialog(null, "تم التعديل", "DONE", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageCategorie().setVisible(true);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage().toUpperCase(), "ERROR",
								JOptionPane.WARNING_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "الحقل فارغ", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
		});
		edit.setForeground(new Color(255, 69, 0));
		edit.setBounds(109, 414, 89, 50);
		panel.add(edit);

		JButton delete = new JButton("حذف");
		delete.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// i just want id to delete this categorie
				if (checkId()) {

					try {
						id = Integer.parseInt(idt.getText());
						manageCategorieDatabase.deleteCategorie(id);
						JOptionPane.showMessageDialog(null, "تم الحذف", "DONE", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageCategorie().setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage().toUpperCase(), "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "اختر الصنف او ادخل ال ID", "ERROR",
							JOptionPane.INFORMATION_MESSAGE);
			}
		});
		delete.setForeground(new Color(255, 69, 0));
		delete.setBounds(109, 497, 89, 50);
		panel.add(delete);

		descriptiont = new JTextField();
		descriptiont.setFont(new Font("Tahoma", Font.BOLD, 14));
		descriptiont.setColumns(10);
		descriptiont.setBounds(100, 204, 200, 40);
		panel.add(descriptiont);

		namet = new JTextField();
		namet.setFont(new Font("Tahoma", Font.BOLD, 14));
		namet.setColumns(10);
		namet.setBounds(100, 150, 200, 26);
		panel.add(namet);

		idt = new JTextField();
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setColumns(10);
		idt.setBounds(100, 91, 200, 26);
		panel.add(idt);

		// define table values
		try {
			insertTableValues();
		} catch (Exception e) {

		}
		// ********************************************
		setTableColors();
		sc = new JScrollPane(table);
		sc.setSize(925, 594);
		sc.setLocation(349, 56);
		getContentPane().add(sc);

		JLabel lblNewLabel = new JLabel("اداره الاصناف");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(736, 10, 246, 35);
		contentPane.add(lblNewLabel);

		JButton refresh = new JButton("اعاده تحميل");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				new ManageCategorie().setVisible(true);
			}

		});
		refresh.setForeground(new Color(255, 69, 0));
		refresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		refresh.setBackground(Color.BLACK);
		refresh.setBounds(1131, 11, 143, 34);
		contentPane.add(refresh);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Base().setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageCategorie.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(0, 0, 89, 43);
		contentPane.add(btnNewButton);

		JButton add_1 = new JButton("المخزن");
		add_1.addActionListener(new ActionListener() {
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
		add_1.setForeground(Color.RED);
		add_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		add_1.setBackground(Color.BLACK);
		add_1.setBounds(99, 0, 98, 50);
		contentPane.add(add_1);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(new Color(255, 69, 0));

	}

// Functions That Service This Frame

	// if fields is empty.
	private boolean check() {
		if (namet.getText().isEmpty() || descriptiont.getText().isEmpty())
			return false;
		return true;
	}

	private void intizalize() throws Exception {

		if (checkId())
			id = Integer.parseInt(idt.getText());
		name = namet.getText();
		description = descriptiont.getText();

	}

	private void insertTableValues() throws SQLException {
		values = manageCategorieDatabase.getCategories();
		int len = values.size();
		body = new String[len][3];
		for (int i = 0; i < len; i++) {
			// to check if Id Is in database before delete and edit.
			valid[values.get(i).getId()] = true;
			body[i][0] = String.valueOf(values.get(i).getId());
			body[i][1] = values.get(i).getName();
			body[i][2] = values.get(i).getDescription();
		}
		table = new JTable(body, header);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		// to set values in text fields when admin select row
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				idt.setText(String.valueOf(values.get(row).getId()));
				namet.setText(values.get(row).getName());
				descriptiont.setText(values.get(row).getDescription());
			}
		});

		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
	}

	// to know if i will call method with id or without.
	private boolean checkId() {
		if (idt.getText().isEmpty())
			return false;
		return true;
	}

	public void setTableColors() {
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(new Color(255, 69, 0));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setRowHeight(30);
	}
}
