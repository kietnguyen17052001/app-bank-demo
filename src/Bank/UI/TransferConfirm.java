package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransferConfirm {

	private JFrame TransferConfirm;

	/**
	 * Launch the application.
	 */
	static String accountNumber, accountName, recipientAccountNumber, recipientAccountName, bank, content;
	static float balance, amount;
	private JTextField txtRecipientName;
	private JTextField txtTransferAmount;
	private JTextField txtFee;

	public static void main(String[] args, String _accountNumber, String _accountName, float _balance,
			String _recipientAccountNumber, String _recipientAccountName, float _amount, String _bank,
			String _content) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountNumber = _accountNumber;
					accountName = _accountName;
					recipientAccountNumber = _recipientAccountNumber;
					recipientAccountName = _recipientAccountName;
					balance = _balance;
					amount = _amount;
					bank = _bank;
					content = _content;
					TransferConfirm window = new TransferConfirm();
					window.TransferConfirm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TransferConfirm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TransferConfirm = new JFrame();
		TransferConfirm.setTitle("Transfer Confirm");
		TransferConfirm.setBounds(100, 100, 551, 581);
		TransferConfirm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TransferConfirm.getContentPane().setLayout(null);
		TransferConfirm.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Transfer to account number");
		lblNewLabel.setForeground(new Color(34, 139, 34));
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblNewLabel.setBounds(143, 12, 253, 19);
		TransferConfirm.getContentPane().add(lblNewLabel);

		JLabel lblClose = new JLabel("Close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Transfer.main(null, accountNumber, balance);
				TransferConfirm.setVisible(false);
			}
		});
		lblClose.setForeground(new Color(220, 20, 60));
		lblClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblClose.setBounds(461, 14, 49, 14);
		TransferConfirm.getContentPane().add(lblClose);

		JPanel panelAccount = new JPanel();
		panelAccount.setBackground(new Color(255, 250, 250));
		panelAccount.setBounds(36, 57, 461, 167);
		TransferConfirm.getContentPane().add(panelAccount);
		panelAccount.setLayout(null);

		JLabel lblSourceAccount = new JLabel("Source Account");
		lblSourceAccount.setForeground(Color.BLACK);
		lblSourceAccount.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblSourceAccount.setBounds(10, 11, 134, 19);
		panelAccount.add(lblSourceAccount);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\credit-card.png"));
		lblNewLabel_1.setBackground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(10, 37, 39, 45);
		panelAccount.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\credit-card (1).png"));
		lblNewLabel_1_1.setBackground(new Color(255, 250, 250));
		lblNewLabel_1_1.setBounds(10, 111, 39, 45);
		panelAccount.add(lblNewLabel_1_1);

		JLabel lblNormalAccount_1 = new JLabel("Normal Account");
		lblNormalAccount_1.setForeground(Color.LIGHT_GRAY);
		lblNormalAccount_1.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblNormalAccount_1.setBackground(new Color(255, 250, 250));
		lblNormalAccount_1.setBounds(50, 41, 124, 16);
		panelAccount.add(lblNormalAccount_1);

		JLabel lblRecipientBank = new JLabel(bank);
		lblRecipientBank.setForeground(Color.LIGHT_GRAY);
		lblRecipientBank.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblRecipientBank.setBackground(new Color(255, 250, 250));
		lblRecipientBank.setBounds(50, 113, 124, 16);
		panelAccount.add(lblRecipientBank);

		JLabel lblAccountNumber = new JLabel(accountNumber);
		lblAccountNumber.setForeground(Color.LIGHT_GRAY);
		lblAccountNumber.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblAccountNumber.setBackground(new Color(255, 250, 250));
		lblAccountNumber.setBounds(50, 62, 124, 16);
		panelAccount.add(lblAccountNumber);

		JLabel lblRecipientAccountNumber = new JLabel(recipientAccountNumber);
		lblRecipientAccountNumber.setForeground(Color.LIGHT_GRAY);
		lblRecipientAccountNumber.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblRecipientAccountNumber.setBackground(new Color(255, 250, 250));
		lblRecipientAccountNumber.setBounds(50, 136, 124, 16);
		panelAccount.add(lblRecipientAccountNumber);

		JLabel lblToTheAccount = new JLabel("To the account");
		lblToTheAccount.setForeground(Color.BLACK);
		lblToTheAccount.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblToTheAccount.setBounds(10, 89, 134, 19);
		panelAccount.add(lblToTheAccount);

		JLabel lblBalance = new JLabel(balance + " vnd");
		lblBalance.setForeground(Color.LIGHT_GRAY);
		lblBalance.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblBalance.setBackground(new Color(255, 250, 250));
		lblBalance.setBounds(291, 37, 143, 29);
		panelAccount.add(lblBalance);

		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\down-arrow.png"));
		lblNewLabel_1_1_1.setBackground(new Color(255, 250, 250));
		lblNewLabel_1_1_1.setBounds(204, 62, 30, 43);
		panelAccount.add(lblNewLabel_1_1_1);

		JPanel panelInformation = new JPanel();
		panelInformation.setBackground(new Color(255, 250, 250));
		panelInformation.setBounds(36, 235, 461, 240);
		TransferConfirm.getContentPane().add(panelInformation);
		panelInformation.setLayout(null);

		JLabel lblRecipientName = new JLabel("Recipient's name");
		lblRecipientName.setForeground(Color.LIGHT_GRAY);
		lblRecipientName.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblRecipientName.setBackground(new Color(255, 250, 250));
		lblRecipientName.setBounds(10, 11, 143, 16);
		panelInformation.add(lblRecipientName);

		JLabel lblTransferAmount = new JLabel("Transfer Amount");
		lblTransferAmount.setForeground(Color.LIGHT_GRAY);
		lblTransferAmount.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblTransferAmount.setBackground(new Color(255, 250, 250));
		lblTransferAmount.setBounds(10, 61, 143, 16);
		panelInformation.add(lblTransferAmount);

		JLabel lblFee = new JLabel("Fee");
		lblFee.setForeground(Color.LIGHT_GRAY);
		lblFee.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblFee.setBackground(new Color(255, 250, 250));
		lblFee.setBounds(10, 110, 143, 16);
		panelInformation.add(lblFee);

		JLabel lblContent = new JLabel("Content");
		lblContent.setForeground(Color.LIGHT_GRAY);
		lblContent.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		lblContent.setBackground(new Color(255, 250, 250));
		lblContent.setBounds(10, 159, 143, 16);
		panelInformation.add(lblContent);

		txtRecipientName = new JTextField(recipientAccountName);
		txtRecipientName.setEnabled(false);
		txtRecipientName.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtRecipientName.setBounds(10, 30, 212, 22);
		panelInformation.add(txtRecipientName);
		txtRecipientName.setColumns(10);

		txtTransferAmount = new JTextField(String.valueOf(amount));
		txtTransferAmount.setEnabled(false);
		txtTransferAmount.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtTransferAmount.setColumns(10);
		txtTransferAmount.setBounds(10, 77, 212, 22);
		panelInformation.add(txtTransferAmount);

		txtFee = new JTextField("0");
		txtFee.setEnabled(false);
		txtFee.setFont(new Font("Fira Code Medium", Font.PLAIN, 13));
		txtFee.setColumns(10);
		txtFee.setBounds(10, 126, 212, 22);
		panelInformation.add(txtFee);

		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		if (content == "") {
			textArea.setText(accountName + " transfers money");
		} else {
			textArea.setText(accountName + " transfers money " + content);
		}
		textArea.setBounds(10, 182, 212, 43);
		panelInformation.add(textArea);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\dangkiet@1705\\Source_Kiet\\Java\\Icon\\dollar.png"));
		lblNewLabel_2.setBackground(new Color(255, 250, 250));
		lblNewLabel_2.setBounds(232, 67, 37, 38);
		panelInformation.add(lblNewLabel_2);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusinessLayerLogin.getInstance().confirmTransfer(accountNumber, recipientAccountNumber, amount,
							balance, textArea.getText());
					JOptionPane.showMessageDialog(null, "Transfer successful", null, JOptionPane.INFORMATION_MESSAGE);
					Transfer.main(null, accountNumber, balance - amount);
					TransferConfirm.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Transfer failed", null, JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		btnConfirm.setBackground(new Color(34, 139, 34));
		btnConfirm.setBounds(117, 486, 299, 43);
		TransferConfirm.getContentPane().add(btnConfirm);
	}

}
