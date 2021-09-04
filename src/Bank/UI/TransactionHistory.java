package Bank.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

import Bank.BusinessLogicLayer.BusinessLayerLogin;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JScrollBar;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class TransactionHistory {

	private JFrame TransactionHistory;
	private JTable tblTransaction;
	static String accountNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String _accountNumber) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountNumber = _accountNumber;
					TransactionHistory window = new TransactionHistory();
					window.TransactionHistory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TransactionHistory() {
		initialize();
	}

	JPanel panelTransaction;
	Object[] columns = { "Day Trading", "Time", "Amount", "Balance", "Transaction Content" };

	public void showDataTransaction() throws SQLException {
		Object[][] data = BusinessLayerLogin.getInstance().dataTransaction(accountNumber, columns);
		tblTransaction = new JTable(data, columns);
		panelTransaction.add(tblTransaction.getTableHeader(), BorderLayout.NORTH);
		panelTransaction.add(tblTransaction, BorderLayout.CENTER);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TransactionHistory = new JFrame();
		TransactionHistory.setTitle("Transaction History");
		TransactionHistory.setBounds(100, 100, 792, 485);
		TransactionHistory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TransactionHistory.getContentPane().setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(405, 345, 352, 92);
		TransactionHistory.getContentPane().add(textArea);

		panelTransaction = new JPanel();
		panelTransaction.setBounds(20, 34, 737, 268);
		TransactionHistory.getContentPane().add(panelTransaction);
		panelTransaction.setLayout(new BorderLayout(0, 0));
		try {
			showDataTransaction();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tblTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				textArea.setText((String) tblTransaction.getValueAt(tblTransaction.getSelectedRow(), 4));
			}
		});

		JScrollBar scrollBar = new JScrollBar();
		panelTransaction.add(scrollBar, BorderLayout.EAST);

		JLabel lblNewLabel = new JLabel("Transaction content");
		lblNewLabel.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblNewLabel.setBounds(404, 313, 183, 21);
		TransactionHistory.getContentPane().add(lblNewLabel);

		JLabel lblBalanceChangeNotification = new JLabel("Balance change notification");
		lblBalanceChangeNotification.setForeground(new Color(46, 139, 87));
		lblBalanceChangeNotification.setFont(new Font("Fira Code Medium", Font.PLAIN, 14));
		lblBalanceChangeNotification.setBounds(20, 11, 250, 21);
		TransactionHistory.getContentPane().add(lblBalanceChangeNotification);

		JLabel lblClose = new JLabel("Close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				TransactionHistory.setVisible(false);
			}
		});
		lblClose.setForeground(new Color(220, 20, 60));
		lblClose.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		lblClose.setBounds(708, 11, 49, 12);
		TransactionHistory.getContentPane().add(lblClose);
	}
}
