package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Transfer {

	private JFrame Transfer;
	private JTextField txtAccountNumber;
	private JTextField txtAmount;
	private JTextField txtAccountName;
	static String accountNumber, accountName;
	static float balance;
	String recipientAccountNumber = null, recipientAccountName = null;
	float amount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String _accountNumber, float _balance) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountNumber = _accountNumber;
					accountName = BusinessLayerLogin.getInstance().accountName(_accountNumber);
					balance = _balance;
					Transfer window = new Transfer();
					window.Transfer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public Transfer() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		Transfer = new JFrame();
		Transfer.setTitle("Transfer");
		Transfer.setBounds(100, 100, 551, 581);
		Transfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Transfer.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Transfer to account number");
		lblNewLabel.setForeground(new Color(34, 139, 34));
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewLabel.setBounds(140, 11, 253, 19);
		Transfer.getContentPane().add(lblNewLabel);

		JPanel panelSourceAccount = new JPanel();
		panelSourceAccount.setBackground(new Color(255, 250, 250));
		panelSourceAccount.setBounds(37, 56, 464, 87);
		Transfer.getContentPane().add(panelSourceAccount);
		panelSourceAccount.setLayout(null);

		JLabel lblSourceAccount = new JLabel("Source Account");
		lblSourceAccount.setForeground(new Color(0, 0, 0));
		lblSourceAccount.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblSourceAccount.setBounds(10, 11, 134, 19);
		panelSourceAccount.add(lblSourceAccount);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\credit-card.png"));
		lblNewLabel_1.setBackground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(10, 41, 39, 35);
		panelSourceAccount.add(lblNewLabel_1);

		JLabel lblBalance = new JLabel(balance + " vnd");
		lblBalance.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblBalance.setBackground(new Color(255, 250, 250));
		lblBalance.setBounds(53, 40, 106, 16);
		panelSourceAccount.add(lblBalance);

		JLabel lblNormalAccount = new JLabel("Normal Account | " + accountNumber);
		lblNormalAccount.setForeground(new Color(192, 192, 192));
		lblNormalAccount.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblNormalAccount.setBackground(new Color(255, 250, 250));
		lblNormalAccount.setBounds(53, 60, 277, 16);
		panelSourceAccount.add(lblNormalAccount);

		JPanel panelInformation = new JPanel();
		panelInformation.setBackground(new Color(255, 250, 250));
		panelInformation.setBounds(37, 165, 464, 314);
		Transfer.getContentPane().add(panelInformation);
		panelInformation.setLayout(null);

		JLabel lblReceiversInformation = new JLabel("Receiver's information");
		lblReceiversInformation.setForeground(Color.BLACK);
		lblReceiversInformation.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblReceiversInformation.setBounds(10, 11, 191, 19);
		panelInformation.add(lblReceiversInformation);

		JLabel lblRecevingBank = new JLabel("Receiving Bank");
		lblRecevingBank.setForeground(Color.LIGHT_GRAY);
		lblRecevingBank.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblRecevingBank.setBackground(new Color(255, 250, 250));
		lblRecevingBank.setBounds(10, 43, 129, 16);
		panelInformation.add(lblRecevingBank);

		JLabel lblAccountNumber = new JLabel("Account number");
		lblAccountNumber.setForeground(Color.LIGHT_GRAY);
		lblAccountNumber.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblAccountNumber.setBackground(new Color(255, 250, 250));
		lblAccountNumber.setBounds(10, 91, 129, 16);
		panelInformation.add(lblAccountNumber);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setForeground(Color.LIGHT_GRAY);
		lblAmount.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblAmount.setBackground(new Color(255, 250, 250));
		lblAmount.setBounds(10, 140, 129, 16);
		panelInformation.add(lblAmount);

		JLabel lblContent = new JLabel("Content (optional)");
		lblContent.setForeground(Color.LIGHT_GRAY);
		lblContent.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblContent.setBackground(new Color(255, 250, 250));
		lblContent.setBounds(10, 189, 159, 16);
		panelInformation.add(lblContent);

		JComboBox<Object> comboBox = new JComboBox<Object>(BusinessLayerLogin.getInstance().listBank().toArray());
		comboBox.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		comboBox.setBackground(new Color(255, 250, 250));
		comboBox.setBounds(10, 60, 201, 25);
		panelInformation.add(comboBox);

		txtAccountNumber = new JTextField();
		txtAccountNumber.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent e) {
				// TODO add code!
				recipientAccountNumber = txtAccountNumber.getText().trim();
				try {
					txtAccountName.setText(BusinessLayerLogin.getInstance().recipientAccountName(recipientAccountNumber,
							comboBox.getSelectedItem().toString()));
					recipientAccountName = txtAccountName.getText();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}

			public void insertUpdate(DocumentEvent e) {
				// TODO add code!
				recipientAccountNumber = txtAccountNumber.getText().trim();
				try {
					txtAccountName.setText(BusinessLayerLogin.getInstance().recipientAccountName(recipientAccountNumber,
							comboBox.getSelectedItem().toString()));
					recipientAccountName = txtAccountName.getText();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}

			public void changedUpdate(DocumentEvent e) {
				// TODO add code!
				recipientAccountNumber = txtAccountNumber.getText().trim();
				try {
					txtAccountName.setText(BusinessLayerLogin.getInstance().recipientAccountName(recipientAccountNumber,
							comboBox.getSelectedItem().toString()));
					recipientAccountName = txtAccountName.getText();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		txtAccountNumber.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtAccountNumber.setBounds(10, 106, 201, 25);
		panelInformation.add(txtAccountNumber);
		txtAccountNumber.setColumns(10);

		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtAmount.setColumns(10);
		txtAmount.setBounds(10, 155, 201, 25);
		panelInformation.add(txtAmount);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		textArea.setBounds(10, 216, 201, 46);
		panelInformation.add(textArea);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\dollar.png"));
		lblNewLabel_2.setBackground(new Color(255, 250, 250));
		lblNewLabel_2.setBounds(221, 146, 37, 38);
		panelInformation.add(lblNewLabel_2);

		JLabel lblAccountName = new JLabel("Account name");
		lblAccountName.setForeground(Color.LIGHT_GRAY);
		lblAccountName.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblAccountName.setBackground(new Color(255, 250, 250));
		lblAccountName.setBounds(221, 91, 129, 16);
		panelInformation.add(lblAccountName);

		txtAccountName = new JTextField();
		txtAccountName.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtAccountName.setColumns(10);
		txtAccountName.setBounds(221, 106, 233, 25);
		panelInformation.add(txtAccountName);

		JLabel lblResult = new JLabel("");
		lblResult.setForeground(Color.BLACK);
		lblResult.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblResult.setBounds(10, 284, 444, 19);
		panelInformation.add(lblResult);

		JLabel lblClose = new JLabel("Close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Transfer.setVisible(false);
			}
		});
		lblClose.setForeground(new Color(220, 20, 60));
		lblClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblClose.setBounds(452, 14, 49, 14);
		Transfer.getContentPane().add(lblClose);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount = Float.parseFloat(txtAmount.getText().trim());
				TransferConfirm.main(null, accountNumber, accountName, balance, recipientAccountNumber,
						recipientAccountName, amount, comboBox.getSelectedItem().toString(), textArea.getText().trim());
				Transfer.setVisible(false);
			}
		});
		btnContinue.setBackground(new Color(34, 139, 34));
		btnContinue.setForeground(new Color(255, 255, 255));
		btnContinue.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		btnContinue.setBounds(126, 490, 299, 43);
		Transfer.getContentPane().add(btnContinue);
	}
}
