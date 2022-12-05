package com.supermarket.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Base extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Base frame = new Base();
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
	public Base() {
		setTitle("الصفحه الرئيسيه");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Base.class.getResource("/com/supermarket/view/admin/store.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 447);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
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
		btnNewButton_2.setBackground(new Color(119, 136, 153));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2
				.setIcon(new ImageIcon(Base.class.getResource("/com/supermarket/view/admin/icons8-store-58.png")));
		btnNewButton_2.setBounds(104, 233, 163, 85);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_2_2 = new JButton("");
		btnNewButton_2_2.setBackground(new Color(119, 136, 153));
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2_2
				.setIcon(new ImageIcon(Base.class.getResource("/com/supermarket/view/admin/index student.png")));
		btnNewButton_2_2.addActionListener(new ActionListener() {
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
		btnNewButton_2_2.setBounds(277, 233, 163, 85);
		contentPane.add(btnNewButton_2_2);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Base.class.getResource("/com/supermarket/view/admin/Back.png")));
		btnNewButton.setBounds(0, 0, 59, 23);
		contentPane.add(btnNewButton);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Base.class.getResource("/com/supermarket/view/admin/store.png")));
		lblNewLabel.setBounds(0, 0, 510, 408);
		contentPane.add(lblNewLabel);
	}

}
