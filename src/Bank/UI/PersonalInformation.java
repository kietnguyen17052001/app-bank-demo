package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Bank.BusinessLogicLayer.BusinessLayerLogin;
import Bank.Objects.Account;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonalInformation {

	private JFrame PersonalInformation;
	static String accountNumber;
	static Account account;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String _accountNumber) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountNumber = _accountNumber;
					account = BusinessLayerLogin.getInstance().getAccount(accountNumber);
					PersonalInformation window = new PersonalInformation();
					window.PersonalInformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PersonalInformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		PersonalInformation = new JFrame();
		PersonalInformation.setTitle("Persional Information");
		PersonalInformation.setBounds(100, 100, 360, 525);
		PersonalInformation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PersonalInformation.getContentPane().setLayout(null);

		JLabel lblClose = new JLabel("Close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				PersonalInformation.setVisible(false);
			}
		});
		lblClose.setForeground(new Color(220, 20, 60));
		lblClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblClose.setBounds(277, 11, 49, 12);
		PersonalInformation.getContentPane().add(lblClose);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(21, 34, 301, 110);
		PersonalInformation.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\linux.png"));
		lblNewLabel.setBounds(111, 5, 64, 85);
		panel.add(lblNewLabel);

		JLabel lblChangeAvatar = new JLabel("Change avatar");
		lblChangeAvatar.setForeground(new Color(46, 139, 87));
		lblChangeAvatar.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblChangeAvatar.setBackground(Color.WHITE);
		lblChangeAvatar.setBounds(82, 88, 129, 17);
		panel.add(lblChangeAvatar);

		JLabel lblMainInformation = new JLabel("Main information");
		lblMainInformation.setForeground(new Color(0, 0, 0));
		lblMainInformation.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblMainInformation.setBackground(Color.WHITE);
		lblMainInformation.setBounds(21, 160, 162, 17);
		PersonalInformation.getContentPane().add(lblMainInformation);

		JLabel lblFullName = new JLabel("Full name");
		lblFullName.setForeground(Color.LIGHT_GRAY);
		lblFullName.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblFullName.setBackground(Color.WHITE);
		lblFullName.setBounds(21, 188, 133, 17);
		PersonalInformation.getContentPane().add(lblFullName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.LIGHT_GRAY);
		lblUsername.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblUsername.setBackground(Color.WHITE);
		lblUsername.setBounds(21, 236, 133, 17);
		PersonalInformation.getContentPane().add(lblUsername);

		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblId.setBackground(Color.WHITE);
		lblId.setBounds(21, 291, 133, 17);
		PersonalInformation.getContentPane().add(lblId);

		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setForeground(Color.LIGHT_GRAY);
		lblPhoneNumber.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblPhoneNumber.setBackground(Color.WHITE);
		lblPhoneNumber.setBounds(21, 345, 133, 17);
		PersonalInformation.getContentPane().add(lblPhoneNumber);

		JLabel lblBank = new JLabel("Bank");
		lblBank.setForeground(Color.LIGHT_GRAY);
		lblBank.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblBank.setBackground(Color.WHITE);
		lblBank.setBounds(21, 392, 133, 17);
		PersonalInformation.getContentPane().add(lblBank);

		JLabel lblFullname = new JLabel(account.accountName);
		lblFullname.setForeground(Color.BLACK);
		lblFullname.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblFullname.setBackground(Color.WHITE);
		lblFullname.setBounds(21, 208, 258, 17);
		PersonalInformation.getContentPane().add(lblFullname);

		JLabel lblUser = new JLabel(account.userName);
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblUser.setBackground(Color.WHITE);
		lblUser.setBounds(21, 260, 194, 17);
		PersonalInformation.getContentPane().add(lblUser);

		JLabel lblUserID = new JLabel(account.userID);
		lblUserID.setForeground(Color.BLACK);
		lblUserID.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblUserID.setBackground(Color.WHITE);
		lblUserID.setBounds(21, 315, 194, 17);
		PersonalInformation.getContentPane().add(lblUserID);

		JLabel lblPhone = new JLabel(account.phone);
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblPhone.setBackground(Color.WHITE);
		lblPhone.setBounds(21, 364, 194, 17);
		PersonalInformation.getContentPane().add(lblPhone);

		JLabel lblBankAC = new JLabel(account.bank);
		lblBankAC.setForeground(Color.BLACK);
		lblBankAC.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblBankAC.setBackground(Color.WHITE);
		lblBankAC.setBounds(21, 411, 194, 17);
		PersonalInformation.getContentPane().add(lblBankAC);
		
		JLabel lblDayCreate = new JLabel("Day create");
		lblDayCreate.setForeground(Color.LIGHT_GRAY);
		lblDayCreate.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblDayCreate.setBackground(Color.WHITE);
		lblDayCreate.setBounds(21, 441, 133, 17);
		PersonalInformation.getContentPane().add(lblDayCreate);
		
		JLabel lblDay = new JLabel(sdf.format(account.dayCreate));
		lblDay.setForeground(Color.BLACK);
		lblDay.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblDay.setBackground(Color.WHITE);
		lblDay.setBounds(21, 460, 194, 17);
		PersonalInformation.getContentPane().add(lblDay);
	}

}
