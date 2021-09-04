package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import Bank.BusinessLogicLayer.BusinessLayerLogin;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIMain {

	private JFrame UIMain;
	static String username, password_pin;
	static String accountNumber, accountName;
	static float balance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String _username, String _password_pin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					username = _username;
					password_pin = _password_pin;
					accountNumber = BusinessLayerLogin.getInstance().accountNumber(username, password_pin);
					accountName = BusinessLayerLogin.getInstance().accountName(accountNumber);
					balance = BusinessLayerLogin.getInstance().balance(accountNumber); 
					UIMain window = new UIMain();
					window.UIMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public UIMain(){
		try {
			initialize();
		} catch (NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() throws NoSuchAlgorithmException, SQLException {
		UIMain = new JFrame();
		UIMain.setTitle("Home page");
		UIMain.setBounds(100, 100, 741, 476);
		UIMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIMain.getContentPane().setLayout(null);

		JPanel panelAccount = new JPanel();
		panelAccount.setBackground(new Color(34, 139, 34));
		panelAccount.setBounds(10, 11, 707, 68);
		UIMain.getContentPane().add(panelAccount);
		panelAccount.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\account.png"));
		lblNewLabel.setBounds(10, 11, 32, 46);
		panelAccount.add(lblNewLabel);

		
		JLabel lblAccount = new JLabel("Main Account - " + accountNumber);
		lblAccount.setForeground(new Color(255, 255, 255));
		lblAccount.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblAccount.setBounds(52, 17, 238, 20);
		panelAccount.add(lblAccount);

		JLabel lblBalance = new JLabel("");
		lblBalance.setText(String.valueOf(balance) + " vnd");
		lblBalance.setForeground(Color.WHITE);
		lblBalance.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblBalance.setBounds(52, 35, 202, 20);
		panelAccount.add(lblBalance);

		JLabel lblBankDemo = new JLabel("Bank Demo");
		lblBankDemo.setForeground(Color.WHITE);
		lblBankDemo.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblBankDemo.setBounds(619, 17, 78, 20);
		panelAccount.add(lblBankDemo);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\1086741.png"));
		lblNewLabel_1.setBounds(567, 11, 42, 46);
		panelAccount.add(lblNewLabel_1);

		JPanel panelTool = new JPanel();
		panelTool.setBackground(new Color(255, 250, 250));
		panelTool.setBounds(50, 90, 625, 94);
		UIMain.getContentPane().add(panelTool);
		panelTool.setLayout(null);

		JButton btnTransfer = new JButton("");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.main(null, accountNumber, balance);
			}
		});
		btnTransfer.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\money.png"));
		btnTransfer.setBackground(new Color(255, 250, 250));
		btnTransfer.setBounds(37, 11, 56, 48);
		panelTool.add(btnTransfer);

		JButton btnTransactionHistory = new JButton("");
		btnTransactionHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionHistory.main(null, accountNumber);
			}
		});
		btnTransactionHistory
				.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\business-and-finance.png"));
		btnTransactionHistory.setBackground(new Color(255, 250, 250));
		btnTransactionHistory.setBounds(203, 11, 56, 48);
		panelTool.add(btnTransactionHistory);

		JButton btnAccount = new JButton("");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountInformation.main(null, accountName, accountNumber);
			}
		});
		btnAccount.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\bank-account.png"));
		btnAccount.setBackground(new Color(255, 250, 250));
		btnAccount.setBounds(389, 11, 56, 48);
		panelTool.add(btnAccount);

		JLabel lblTransfer = new JLabel("Transfer");
		lblTransfer.setBackground(new Color(255, 250, 250));
		lblTransfer.setForeground(new Color(0, 0, 0));
		lblTransfer.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblTransfer.setBounds(31, 63, 69, 20);
		panelTool.add(lblTransfer);

		JLabel lblTransactionHistory = new JLabel("Transaction History");
		lblTransactionHistory.setForeground(Color.BLACK);
		lblTransactionHistory.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblTransactionHistory.setBackground(new Color(255, 250, 250));
		lblTransactionHistory.setBounds(147, 63, 165, 20);
		panelTool.add(lblTransactionHistory);

		JLabel lblAccountInformation = new JLabel("Account Information");
		lblAccountInformation.setForeground(Color.BLACK);
		lblAccountInformation.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblAccountInformation.setBackground(new Color(255, 250, 250));
		lblAccountInformation.setBounds(340, 63, 171, 20);
		panelTool.add(lblAccountInformation);

		JButton btnLogout = new JButton("");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UILogin.main(null);
				UIMain.setVisible(false);
			}
		});
		btnLogout.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\logout.png"));
		btnLogout.setBackground(new Color(255, 250, 250));
		btnLogout.setBounds(546, 11, 56, 48);
		panelTool.add(btnLogout);

		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setForeground(Color.BLACK);
		lblLogout.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblLogout.setBackground(new Color(255, 250, 250));
		lblLogout.setBounds(548, 63, 56, 20);
		panelTool.add(lblLogout);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\java.png"));
		lblNewLabel_2.setBounds(64, 195, 600, 224);
		UIMain.getContentPane().add(lblNewLabel_2);
	}
}
