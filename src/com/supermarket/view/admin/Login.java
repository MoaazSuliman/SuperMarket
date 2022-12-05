package com.supermarket.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermarket.database.LoginDatabase;
import com.supermarket.model.Person;
import com.supermarket.view.seller.ManageUserSeller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private String user, pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 561);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 414, 64);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_4_2 = new JLabel("سوبر ماركت العربي");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setForeground(new Color(255, 69, 0));
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_4_2.setBackground(Color.WHITE);
		lblNewLabel_4_2.setBounds(0, 0, 414, 64);
		panel.add(lblNewLabel_4_2);

		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					password.requestFocus();
			}
		});
		username.setFont(new Font("Tahoma", Font.BOLD, 14));
		username.setBounds(189, 157, 206, 20);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(40, 157, 139, 20);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(40, 214, 139, 20);
		contentPane.add(lblNewLabel_3);

		JButton login = new JButton("دخول");
		login.setFont(new Font("Tahoma", Font.BOLD, 18));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// check login
				user = username.getText();
				pass = password.getText();
				if (user.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "FILL USERNAME AND PASSWORD", "ERROR",
							JOptionPane.WARNING_MESSAGE);
				} else {
					goTo();// admin pages or seller page.
				}

			}
		});
		login.setForeground(new Color(255, 69, 0));
		login.setBackground(new Color(0, 0, 0));
		login.setBounds(174, 254, 116, 31);
		contentPane.add(login);

		JButton clear = new JButton("مسح");
		clear.setFont(new Font("Tahoma", Font.BOLD, 18));
		clear.setForeground(new Color(255, 69, 0));
		clear.setBackground(new Color(0, 0, 0));
		clear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");

			}
		});
		clear.setBounds(174, 296, 116, 35);
		contentPane.add(clear);

		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					login.doClick();
			}
		});
		password.setFont(new Font("Tahoma", Font.BOLD, 14));
		password.setBounds(189, 214, 206, 20);
		contentPane.add(password);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(20, 342, 404, 140);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel(" By Programmer Moaaz");
		lblNewLabel_4.setForeground(new Color(255, 69, 0));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Algerian", Font.BOLD, 30));
		lblNewLabel_4.setBounds(10, 11, 384, 74);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_4_3 = new JLabel("Whatsapp:01211074252");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setForeground(new Color(255, 69, 0));
		lblNewLabel_4_3.setFont(new Font("Algerian", Font.BOLD, 30));
		lblNewLabel_4_3.setBackground(Color.WHITE);
		lblNewLabel_4_3.setBounds(10, 83, 394, 57);
		panel_1.add(lblNewLabel_4_3);

		JLabel lblNewLabel_4_1 = new JLabel("Login");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(new Color(255, 69, 0));
		lblNewLabel_4_1.setFont(new Font("Algerian", Font.BOLD, 30));
		lblNewLabel_4_1.setBackground(Color.WHITE);
		lblNewLabel_4_1.setBounds(0, 82, 439, 64);
		contentPane.add(lblNewLabel_4_1);

	}

	// To check if username and password in database .
	// Then see person role (Admin Or Seller).
	private void goTo() {
		try {

			Person person = new LoginDatabase().Login(user, pass);
			if (person == null) {
				JOptionPane.showMessageDialog(null, "WRONG USERNAME OR PASSWORD");
			} else if (person.getRole() == 1) {
				dispose();
				new Base().setVisible(true);
			} else {
				dispose();
				new ManageUserSeller(person.getId()).setVisible(true);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
