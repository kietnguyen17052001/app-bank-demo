package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ForgotPassword {

	private JFrame ForgotPassword;
	private JTextField txtUsername;
	private JTextField txtUserID;
	private JTextField txtPhone;
	private JTextField txtCreditCardID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword window = new ForgotPassword();
					window.ForgotPassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ForgotPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	String username, phoneNumber, userID;
	int creditCardID;

	private void initialize() {
		ForgotPassword = new JFrame();
		ForgotPassword.setTitle("Forgot Password");
		ForgotPassword.setBounds(100, 100, 379, 366);
		ForgotPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ForgotPassword.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(34, 139, 34));
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblNewLabel.setBounds(68, 42, 76, 20);
		ForgotPassword.getContentPane().add(lblNewLabel);

		JLabel lblUseid = new JLabel("UseID");
		lblUseid.setForeground(new Color(34, 139, 34));
		lblUseid.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblUseid.setBounds(68, 87, 76, 20);
		ForgotPassword.getContentPane().add(lblUseid);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(new Color(34, 139, 34));
		lblPhoneNumber.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(68, 134, 96, 20);
		ForgotPassword.getContentPane().add(lblPhoneNumber);

		JLabel lblCreditcardid = new JLabel("CreditCardID");
		lblCreditcardid.setForeground(new Color(34, 139, 34));
		lblCreditcardid.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblCreditcardid.setBounds(68, 182, 96, 20);
		ForgotPassword.getContentPane().add(lblCreditcardid);
		
		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblResult.setBounds(68, 236, 231, 20);
		ForgotPassword.getContentPane().add(lblResult);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText().trim();
				phoneNumber = txtPhone.getText().trim();
				userID = txtUserID.getText().trim();
				creditCardID = Integer.parseInt(txtCreditCardID.getText().trim());
				try {
					if (BusinessLayerLogin.getInstance().checkConfirm(username, phoneNumber, userID, creditCardID)) {
						lblResult.setForeground(Color.blue);
						lblResult.setText("Valid informaion");
						ChangePassword.main(null, creditCardID);
					}
					else {
						lblResult.setForeground(Color.red);
						lblResult.setText("Invalid informaion");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setBackground(new Color(34, 139, 34));
		btnConfirm.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		btnConfirm.setBounds(68, 265, 106, 34);
		ForgotPassword.getContentPane().add(btnConfirm);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForgotPassword.setVisible(false);
			}
		});
		btnClose.setForeground(new Color(255, 255, 255));
		btnClose.setBackground(new Color(220, 20, 60));
		btnClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		btnClose.setBounds(193, 265, 106, 34);
		ForgotPassword.getContentPane().add(btnClose);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtUsername.setBounds(68, 60, 231, 23);
		ForgotPassword.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtUserID = new JTextField();
		txtUserID.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtUserID.setColumns(10);
		txtUserID.setBounds(68, 105, 231, 23);
		ForgotPassword.getContentPane().add(txtUserID);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtPhone.setColumns(10);
		txtPhone.setBounds(68, 152, 231, 23);
		ForgotPassword.getContentPane().add(txtPhone);

		txtCreditCardID = new JTextField();
		txtCreditCardID.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtCreditCardID.setColumns(10);
		txtCreditCardID.setBounds(68, 202, 231, 23);
		ForgotPassword.getContentPane().add(txtCreditCardID);

		JLabel lblForgotPassword = new JLabel("Forgot Password");
		lblForgotPassword.setForeground(new Color(34, 139, 34));
		lblForgotPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblForgotPassword.setBounds(119, 11, 130, 20);
		ForgotPassword.getContentPane().add(lblForgotPassword);
	}
}
