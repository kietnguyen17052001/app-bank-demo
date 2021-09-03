package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EnterPin {

	private JFrame EnterPin;
	private JPasswordField pfPin;

	/**
	 * Launch the application.
	 */
	static String username;
	public static void main(String[] args, String _username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					username = _username;
					EnterPin window = new EnterPin();
					window.EnterPin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnterPin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	String pin;
	private void initialize() {
		EnterPin = new JFrame();
		EnterPin.setTitle("Enter Pin");
		EnterPin.setBounds(100, 100, 379, 204);
		EnterPin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EnterPin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter PIN");
		lblNewLabel.setForeground(new Color(34, 139, 34));
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewLabel.setBounds(136, 27, 88, 14);
		EnterPin.getContentPane().add(lblNewLabel);
		
		pfPin = new JPasswordField();
		pfPin.setHorizontalAlignment(SwingConstants.CENTER);
		pfPin.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		pfPin.setBounds(87, 52, 193, 31);
		EnterPin.getContentPane().add(pfPin);
		
		JLabel lbResult = new JLabel("");
		lbResult.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lbResult.setBounds(87, 94, 193, 21);
		EnterPin.getContentPane().add(lbResult);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pin = pfPin.getText().trim();
				try {
					if (BusinessLayerLogin.getInstance().checkLogin(username, false, pin)) {
						lbResult.setForeground(Color.blue);
						lbResult.setText("Login successfully");
						UIMain.main(null, username, pin);
						EnterPin.setVisible(false);
					}
					else {
						lbResult.setForeground(Color.red);
						lbResult.setText("Invalid PIN");
					}
				} catch (NoSuchAlgorithmException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(34, 139, 34));
		btnLogin.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		btnLogin.setBounds(70, 120, 89, 32);
		EnterPin.getContentPane().add(btnLogin);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UILogin.main(null);
				EnterPin.setVisible(false);
			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		btnClose.setBackground(new Color(220, 20, 60));
		btnClose.setBounds(200, 120, 89, 32);
		EnterPin.getContentPane().add(btnClose);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\pin.png"));
		lblNewLabel_1.setBounds(53, 52, 24, 31);
		EnterPin.getContentPane().add(lblNewLabel_1);
	}
}
