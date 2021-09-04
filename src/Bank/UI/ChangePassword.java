package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword {

	private JFrame ChangePassword;
	private JPasswordField pfConfirmPassword;
	private JPasswordField pfNewPassword;
	private JLabel lblResult;
	private JButton btnSave;
	private JButton btnClose;
	static int creditCardID;
	String newPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, int  _creditCardID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					creditCardID = _creditCardID;
					ChangePassword window = new ChangePassword();
					window.ChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangePassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ChangePassword = new JFrame();
		ChangePassword.setTitle("Change Password");
		ChangePassword.setBounds(100, 100, 320, 263);
		ChangePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChangePassword.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setForeground(new Color(34, 139, 34));
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewLabel.setBounds(82, 11, 152, 19);
		ChangePassword.getContentPane().add(lblNewLabel);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(new Color(34, 139, 34));
		lblNewPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewPassword.setBounds(61, 41, 152, 19);
		ChangePassword.getContentPane().add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(new Color(34, 139, 34));
		lblConfirmPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblConfirmPassword.setBounds(61, 91, 152, 19);
		ChangePassword.getContentPane().add(lblConfirmPassword);
		
		pfConfirmPassword = new JPasswordField();
		pfConfirmPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		pfConfirmPassword.setBounds(61, 110, 188, 22);
		ChangePassword.getContentPane().add(pfConfirmPassword);
		
		pfNewPassword = new JPasswordField();
		pfNewPassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		pfNewPassword.setBounds(61, 60, 188, 22);
		ChangePassword.getContentPane().add(pfNewPassword);
		
		lblResult = new JLabel("");
		lblResult.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblResult.setBounds(61, 143, 188, 19);
		ChangePassword.getContentPane().add(lblResult);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPassword = pfNewPassword.getText().trim();
				if (!newPassword.equals(pfConfirmPassword.getText().trim())) {
					lblResult.setForeground(Color.red);
					lblResult.setText("Invalid confirm password");
				}
				else {
					try {
						BusinessLayerLogin.getInstance().changePassword(creditCardID, newPassword);
						lblResult.setForeground(Color.blue);
						lblResult.setText("Change password successful");
					} catch (NoSuchAlgorithmException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setBackground(new Color(34, 139, 34));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		btnSave.setBounds(61, 165, 89, 31);
		ChangePassword.getContentPane().add(btnSave);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword.setVisible(false);
			}
		});
		btnClose.setBackground(new Color(220, 20, 60));
		btnClose.setForeground(new Color(255, 255, 255));
		btnClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		btnClose.setBounds(160, 165, 89, 31);
		ChangePassword.getContentPane().add(btnClose);
	}
}
