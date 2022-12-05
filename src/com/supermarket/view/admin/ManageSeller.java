package com.supermarket.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.ManageSellerDatabase;
import com.supermarket.model.Person;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManageSeller extends JFrame {

	private JPanel contentPane;
	private JTextField usernamet;
	private JTextField idt;
	private JTable table;
	private JTextField aget;
	private JTextField phonet;
	private JTextField passwordt;
	private JScrollPane scrollPane;

	private String header[] = { "ID", "Username", "Age", "Phone", "Password", "shift" };
	private String[][] body;
	private ArrayList<Person> persons = new ArrayList<Person>();
	private ManageSellerDatabase sellerDatabase = new ManageSellerDatabase();
	private int row = -1;
	public Person person = new Person();
	private String[] shifts = { "صباحي", "مسائي" };
	private JComboBox shift;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageSeller frame = new ManageSeller();
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
	public ManageSeller() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 75, 910, 575);
		contentPane.add(scrollPane);

		setTable();

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 72, 324, 578);
		contentPane.add(panel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 41, 80, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 89, 80, 36);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("age");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 150, 80, 37);
		panel.add(lblNewLabel_3);

		JButton add = new JButton("اضافه");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkWithoutId()) {
						intizalizeWithoutId();
						System.out.println(person.toString());
						sellerDatabase.insertSeller(person.getUsername(), person.getAge(), person.getPhone(),
								person.getPassword(), person.getShift());

						JOptionPane.showMessageDialog(null, "تمت الاضافه بنجاح", "DONE",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageSeller().setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "املا الحقول", "ERROR", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "DONE", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add.setForeground(new Color(255, 69, 0));
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.setBounds(117, 397, 89, 50);
		panel.add(add);

		JButton edit = new JButton("تعديل");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkWithId()) {
						intizializeIwithId();
						sellerDatabase.updateSeller(person.getId(), person.getUsername(), person.getAge(),
								person.getPhone(), person.getPassword(), getShift());
						JOptionPane.showMessageDialog(null, "تم التعديل بنجاح", "DONE",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageSeller().setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "اختر الموظف او املا الحقول كامله", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		edit.setForeground(new Color(255, 69, 0));
		edit.setFont(new Font("Tahoma", Font.BOLD, 18));
		edit.setBounds(225, 461, 89, 50);
		panel.add(edit);

		JButton delete = new JButton("حذف");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkId()) {
						int id = Integer.parseInt(idt.getText());
						sellerDatabase.deleteSeller(id);
						JOptionPane.showMessageDialog(null, "تم الحذف بنجاح", "ERROR", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ManageSeller().setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "اختر الموظف او قم بادخال ال ID", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		delete.setForeground(new Color(255, 69, 0));
		delete.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete.setBounds(10, 461, 89, 50);
		panel.add(delete);

		usernamet = new JTextField();
		usernamet.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernamet.setColumns(10);
		usernamet.setBounds(100, 97, 200, 26);
		panel.add(usernamet);

		idt = new JTextField();
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setColumns(10);
		idt.setBounds(100, 38, 200, 26);
		panel.add(idt);

		aget = new JTextField();
		aget.setFont(new Font("Tahoma", Font.BOLD, 14));
		aget.setColumns(10);
		aget.setBounds(100, 161, 200, 26);
		panel.add(aget);

		JLabel lblNewLabel_3_1 = new JLabel("phone");
		lblNewLabel_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(10, 215, 80, 37);
		panel.add(lblNewLabel_3_1);

		phonet = new JTextField();
		phonet.setFont(new Font("Tahoma", Font.BOLD, 14));
		phonet.setColumns(10);
		phonet.setBounds(100, 226, 200, 26);
		panel.add(phonet);

		JLabel lblNewLabel_3_2 = new JLabel("password");
		lblNewLabel_3_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(10, 283, 80, 37);
		panel.add(lblNewLabel_3_2);

		passwordt = new JTextField();
		passwordt.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordt.setColumns(10);
		passwordt.setBounds(100, 294, 200, 26);
		panel.add(passwordt);

		JButton details = new JButton("التفاصيل");
		details.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (row != -1 || checkId()) {
						int id = Integer.parseInt(idt.getText());
						new Details(id).setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "ااختر الموظف او  ادخل ال ID", "ERROR",
								JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		details.setForeground(Color.RED);
		details.setFont(new Font("Tahoma", Font.BOLD, 18));
		details.setBounds(10, 517, 304, 50);
		panel.add(details);

		shift = new JComboBox();
		shift.setFont(new Font("Tahoma", Font.BOLD, 14));
		shift.setModel(new DefaultComboBoxModel(shifts));
		shift.setBounds(100, 339, 200, 22);
		panel.add(shift);

		JLabel lblNewLabel_3_2_1 = new JLabel("shift");
		lblNewLabel_3_2_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2_1.setBounds(19, 330, 80, 37);
		panel.add(lblNewLabel_3_2_1);

		JLabel lblNewLabel = new JLabel("الموظفين");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 55));
		lblNewLabel.setBounds(523, 0, 526, 67);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setIcon(new ImageIcon(ManageSeller.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(0, 0, 89, 43);
		contentPane.add(btnNewButton);

		JButton refresh = new JButton("اعاده تحميل");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new ManageSeller().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refresh.setForeground(new Color(255, 69, 0));
		refresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		refresh.setBackground(Color.BLACK);
		refresh.setBounds(1131, 11, 143, 34);
		contentPane.add(refresh);
	}

	private void setTable() throws SQLException {

		persons = sellerDatabase.getSellers();
		int len = persons.size();
		body = new String[len][6];
		for (int i = 0; i < len; i++) {
			body[i][0] = String.valueOf(persons.get(i).getId());
			body[i][1] = persons.get(i).getUsername();
			body[i][2] = String.valueOf(persons.get(i).getAge());
			body[i][3] = persons.get(i).getPhone();
			body[i][4] = persons.get(i).getPassword();
			body[i][5] = persons.get(i).getShift();
		}
		table = new JTable(body, header);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTexts();
			}
		});
		setColors();
		scrollPane.setViewportView(table);
	}

	private void setTexts() {
		row = table.getSelectedRow();
		idt.setText(String.valueOf(persons.get(row).getId()));
		usernamet.setText(persons.get(row).getUsername());
		aget.setText(String.valueOf(persons.get(row).getAge()));
		phonet.setText(persons.get(row).getPhone());
		passwordt.setText(persons.get(row).getPassword());

		String s = persons.get(row).getShift();
		if (s.equals("صباحي")) {
			shift.setSelectedIndex(0);
		} else
			shift.setSelectedIndex(1);
	}

	private void setColors() {
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(new Color(255, 69, 0));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setRowHeight(30);
	}

	private boolean checkWithoutId() {
		if (usernamet.getText().isEmpty() || passwordt.getText().isEmpty() || aget.getText().isEmpty()
				|| phonet.getText().isEmpty())
			return false;
		return true;
	}

	private boolean checkWithId() {
		if (checkWithoutId() && checkId())
			return true;
		return false;
	}

	private boolean checkId() {
		if (idt.getText().isEmpty())
			return false;
		return true;
	}

	private void intizializeIwithId() throws Exception {
		person.setId(Integer.parseInt(idt.getText()));
		intizalizeWithoutId();
	}

	private void intizalizeWithoutId() throws Exception {
		person.setAge(Integer.parseInt(aget.getText()));
		person.setPhone(phonet.getText());
		person.setUsername(usernamet.getText());
		person.setPassword(passwordt.getText());
		person.setShift(getShift());
	}

	private String getShift() {
		int index = shift.getSelectedIndex();
		return shifts[index];
	}
}
