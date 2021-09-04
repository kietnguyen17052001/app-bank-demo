package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordInAC {

	private JFrame ChangePasswordInAC;
	private JPasswordField pfOldPassword;
	private JPasswordField pfNewPassword;
	private JPasswordField pfRetypePassword;
	static String password, accountNumber;
	String oldPassword, newPassword, retypePassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String _accountNumber) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					password = BusinessLayerLogin.getInstance().password(_accountNumber);
					accountNumber = _accountNumber;
					ChangePasswordInAC window = new ChangePasswordInAC();
					window.ChangePasswordInAC.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangePasswordInAC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ChangePasswordInAC = new JFrame();
		ChangePasswordInAC.setTitle("Change password");
		ChangePasswordInAC.setBounds(100, 100, 347, 378);
		ChangePasswordInAC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChangePasswordInAC.getContentPane().setLayout(null);
		ChangePasswordInAC.setLocationRelativeTo(null);
		
		JLabel lblClose = new JLabel("Close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ChangePasswordInAC.setVisible(false);
			}
		});
		lblClose.setForeground(new Color(220, 20, 60));
		lblClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblClose.setBounds(259, 11, 49, 12);
		ChangePasswordInAC.getContentPane().add(lblClose);

		JLabel lblEnterPasswordTo = new JLabel("Enter password to change password");
		lblEnterPasswordTo.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblEnterPasswordTo.setBackground(new Color(255, 255, 255));
		lblEnterPasswordTo.setBounds(10, 44, 313, 17);
		ChangePasswordInAC.getContentPane().add(lblEnterPasswordTo);

		JLabel lblOldPassword = new JLabel("Old password");
		lblOldPassword.setForeground(new Color(192, 192, 192));
		lblOldPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblOldPassword.setBackground(Color.WHITE);
		lblOldPassword.setBounds(10, 87, 133, 17);
		ChangePasswordInAC.getContentPane().add(lblOldPassword);

		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setForeground(Color.LIGHT_GRAY);
		lblNewPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewPassword.setBackground(Color.WHITE);
		lblNewPassword.setBounds(10, 148, 133, 17);
		ChangePasswordInAC.getContentPane().add(lblNewPassword);

		JLabel lblRetypeNewPassword = new JLabel("Retype new password");
		lblRetypeNewPassword.setForeground(Color.LIGHT_GRAY);
		lblRetypeNewPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblRetypeNewPassword.setBackground(Color.WHITE);
		lblRetypeNewPassword.setBounds(10, 211, 177, 17);
		ChangePasswordInAC.getContentPane().add(lblRetypeNewPassword);

		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblResult.setBackground(Color.WHITE);
		lblResult.setBounds(10, 270, 313, 18);
		ChangePasswordInAC.getContentPane().add(lblResult);

		pfOldPassword = new JPasswordField();
		pfOldPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		pfOldPassword.setBounds(10, 117, 228, 22);
		ChangePasswordInAC.getContentPane().add(pfOldPassword);

		pfNewPassword = new JPasswordField();
		pfNewPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		pfNewPassword.setBounds(10, 176, 228, 22);
		ChangePasswordInAC.getContentPane().add(pfNewPassword);

		pfRetypePassword = new JPasswordField();
		pfRetypePassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		pfRetypePassword.setBounds(10, 239, 228, 22);
		ChangePasswordInAC.getContentPane().add(pfRetypePassword);

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldPassword = pfOldPassword.getText().trim();
				newPassword = pfNewPassword.getText().trim();
				retypePassword = pfRetypePassword.getText().trim();
				try {
					if (!BusinessLayerLogin.getInstance().encrypt(oldPassword).equals(password)) {
						lblResult.setForeground(Color.red);
						lblResult.setText("Invalid old password");
					}
					else {
						if (!BusinessLayerLogin.getInstance().encrypt(newPassword)
								.equals(BusinessLayerLogin.getInstance().encrypt(retypePassword))) {
							lblResult.setForeground(Color.red);
							lblResult.setText("Invalid retype new password");
						}
						else {
							BusinessLayerLogin.getInstance().changePassword(BusinessLayerLogin.getInstance().creditCardID(accountNumber), newPassword);
							lblResult.setForeground(Color.blue);
							lblResult.setText("Change password successful");
						}
					}
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnNewButton.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(46, 139, 87));
		btnNewButton.setBounds(10, 292, 313, 38);
		ChangePasswordInAC.getContentPane().add(btnNewButton);

	}
}
