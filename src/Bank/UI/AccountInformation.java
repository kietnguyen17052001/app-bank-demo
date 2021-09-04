package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountInformation {

	private JFrame AccountInformation;

	/**
	 * Launch the application.
	 */
	static String accountName, accountNumber;
	public static void main(String[] args, String _accountName, String _accountNumber) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountName = _accountName;
					accountNumber = _accountNumber;
					AccountInformation window = new AccountInformation();
					window.AccountInformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccountInformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AccountInformation = new JFrame();
		AccountInformation.setTitle("Account Information");
		AccountInformation.setBounds(100, 100, 450, 252);
		AccountInformation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccountInformation.getContentPane().setLayout(null);
		AccountInformation.setLocationRelativeTo(null);
		
		JPanel PersonalCustomization = new JPanel();
		PersonalCustomization.setBackground(new Color(255, 250, 250));
		PersonalCustomization.setBounds(22, 27, 388, 152);
		AccountInformation.getContentPane().add(PersonalCustomization);
		PersonalCustomization.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Personal customization");
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.BOLD, 15));
		lblNewLabel.setBounds(92, 10, 212, 20);
		PersonalCustomization.add(lblNewLabel);
		
		JPanel PersionalInformation = new JPanel();
		PersionalInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				PersonalInformation.main(null, accountNumber);
			}
		});
		PersionalInformation.setBackground(new Color(248, 248, 255));
		PersionalInformation.setBounds(10, 36, 368, 50);
		PersonalCustomization.add(PersionalInformation);
		PersionalInformation.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\account.png"));
		lblNewLabel_1.setBackground(new Color(230, 230, 250));
		lblNewLabel_1.setBounds(10, 7, 32, 39);
		PersionalInformation.add(lblNewLabel_1);
		
		JLabel lblPersionalInformation = new JLabel("Persional Information");
		lblPersionalInformation.setForeground(new Color(192, 192, 192));
		lblPersionalInformation.setFont(new Font("Fira Code Medium", Font.PLAIN, 12));
		lblPersionalInformation.setBackground(new Color(230, 230, 250));
		lblPersionalInformation.setBounds(52, 28, 165, 17);
		PersionalInformation.add(lblPersionalInformation);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\right-arrow.png"));
		lblNewLabel_1_1_1.setBackground(new Color(230, 230, 250));
		lblNewLabel_1_1_1.setBounds(326, 11, 32, 28);
		PersionalInformation.add(lblNewLabel_1_1_1);
		
		JLabel lblAccountName = new JLabel(accountName);
		lblAccountName.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblAccountName.setBackground(new Color(230, 230, 250));
		lblAccountName.setBounds(52, 9, 264, 17);
		PersionalInformation.add(lblAccountName);
		
		JPanel ChangePassword = new JPanel();
		ChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ChangePasswordInAC.main(null, accountNumber);
			}
		});
		ChangePassword.setBackground(new Color(248, 248, 255));
		ChangePassword.setLayout(null);
		ChangePassword.setBounds(10, 94, 368, 47);
		PersonalCustomization.add(ChangePassword);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\padlock.png"));
		lblNewLabel_1_1.setBackground(new Color(230, 230, 250));
		lblNewLabel_1_1.setBounds(10, 7, 32, 36);
		ChangePassword.add(lblNewLabel_1_1);
		
		JLabel lblChangePassword = new JLabel("Change password");
		lblChangePassword.setBackground(new Color(230, 230, 250));
		lblChangePassword.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblChangePassword.setBounds(52, 18, 148, 17);
		ChangePassword.add(lblChangePassword);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\right-arrow.png"));
		lblNewLabel_1_1_1_1.setBackground(new Color(230, 230, 250));
		lblNewLabel_1_1_1_1.setBounds(326, 7, 32, 28);
		ChangePassword.add(lblNewLabel_1_1_1_1);
		
		JLabel lblClose = new JLabel("Close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				AccountInformation.setVisible(false);
			}
		});
		lblClose.setForeground(new Color(220, 20, 60));
		lblClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblClose.setBounds(361, 11, 49, 12);
		AccountInformation.getContentPane().add(lblClose);
	}
}
