package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import Bank.BusinessLogicLayer.BusinessLayerLogin;
import Bank.Objects.Account;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UILogin {

	private JFrame UILogin;
	private JTextField txtUsername;
	private JPasswordField pfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UILogin window = new UILogin();
					window.UILogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UILogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	String username, password_pin;

	private void initialize() {
		UILogin = new JFrame();
		UILogin.setTitle("Login");
		UILogin.setBounds(100, 100, 535, 473);
		UILogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UILogin.getContentPane().setLayout(null);
		UILogin.setLocationRelativeTo(null); 
		
		JLabel lblNewLabel = new JLabel("Bank Demo");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewLabel.setBounds(21, 27, 94, 25);
		UILogin.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\1086741.png"));
		lblNewLabel_1.setBounds(113, 21, 49, 38);
		UILogin.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\user.png"));
		lblNewLabel_2.setBounds(228, 70, 64, 64);
		UILogin.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\username.png"));
		lblNewLabel_3.setBounds(96, 168, 24, 38);
		UILogin.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\lock.png"));
		lblNewLabel_3_1.setBounds(96, 228, 24, 38);
		UILogin.getContentPane().add(lblNewLabel_3_1);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtUsername.setBounds(143, 175, 253, 25);
		UILogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		pfPassword.setBounds(143, 235, 253, 25);
		UILogin.getContentPane().add(pfPassword);

		JLabel lbResult = new JLabel("");
		lbResult.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lbResult.setBounds(96, 277, 300, 25);
		UILogin.getContentPane().add(lbResult);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText().trim();
				password_pin = pfPassword.getText().trim();
				try {
					Account account = BusinessLayerLogin.getInstance().getAccount(username, true, password_pin);
					if (account != null) { // valid information
						lbResult.setForeground(Color.blue);
						lbResult.setText("Login successfully");
						UIMain.main(null, account);
						UILogin.setVisible(false);
					} else {
						lbResult.setForeground(Color.red);
						lbResult.setText("Login failed");
					}
				} catch (NoSuchAlgorithmException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		btnLogin.setBackground(new Color(34, 139, 34));
		btnLogin.setBounds(96, 309, 244, 38);
		UILogin.getContentPane().add(btnLogin);

		JButton btnPin = new JButton("");
		btnPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText().trim();
				try {
					if (BusinessLayerLogin.getInstance().checkUsername(username)) {
						EnterPin.main(null, username);
						UILogin.setVisible(false);
					} else {
						lbResult.setForeground(Color.red);
						lbResult.setText("Invalid Username");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnPin.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\pin.png"));
		btnPin.setBackground(new Color(34, 139, 34));
		btnPin.setBounds(350, 309, 46, 38);
		UILogin.getContentPane().add(btnPin);

		JLabel lblForgotPassword = new JLabel("Forgot Password");
		lblForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ForgotPassword.main(null);
			}
		});
		lblForgotPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblForgotPassword.setForeground(new Color(0, 128, 0));
		lblForgotPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblForgotPassword.setBounds(249, 358, 147, 25);
		UILogin.getContentPane().add(lblForgotPassword);

	}
}
