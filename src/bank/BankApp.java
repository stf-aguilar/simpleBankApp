package bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class BankApp extends javax.swing.JFrame{
	ArrayList<Account> accounts = Utility.readFile("accounts.txt");
	Account currentAccount;
	
	private void initiateAccounts() {
		for(Account a: accounts) {
			accountDropDown.addItem(a);
		}
	}
	
	private JPanel contentPane;
	private JTextField customerField;
	private JTextField dateField;
	private JTextField balanceField;
	private JComboBox accountDropDown;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankApp frame = new BankApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public BankApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Account Number");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 52, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 33, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -192, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -393, SpringLayout.EAST, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, 113, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Open date");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Balance");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox, 37, SpringLayout.NORTH, contentPane);
		contentPane.add(comboBox);
		
		JButton DepositButton = new JButton("Deposit");
		DepositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amountString = JOptionPane.showInputDialog("Enter an amount to deposit: ");
				double amount = Double.parseDouble(amountString);
				currentAccount.deposit(amount);
				balanceField.setText(String.valueOf(currentAccount.getBalance()));
				Utility.writeFile(accounts,"accounts.txt");
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, DepositButton, 417, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, DepositButton, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox, 230, SpringLayout.EAST, DepositButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBox, -344, SpringLayout.WEST, DepositButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, DepositButton, -5, SpringLayout.NORTH, lblNewLabel);
		contentPane.add(DepositButton);
		
		accountDropDown = new JComboBox();
		accountDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentAccount = (Account) accountDropDown.getSelectedItem();
				customerField.setText(currentAccount.getAccountHolder());
				dateField.setText(currentAccount.getOpenDate());
				balanceField.setText(String.valueOf(currentAccount.getBalance()));
			}
		});
		accountDropDown.setToolTipText("Account");
		accountDropDown.setEditable(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, accountDropDown, -5, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, accountDropDown, 175, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, accountDropDown, -144, SpringLayout.EAST, contentPane);
		contentPane.add(accountDropDown);
		
		customerField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, customerField, 175, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 2, SpringLayout.NORTH, customerField);
		sl_contentPane.putConstraint(SpringLayout.NORTH, customerField, 100, SpringLayout.NORTH, contentPane);
		contentPane.add(customerField);
		customerField.setColumns(10);
		
		dateField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 2, SpringLayout.NORTH, dateField);
		sl_contentPane.putConstraint(SpringLayout.WEST, dateField, 175, SpringLayout.WEST, contentPane);
		contentPane.add(dateField);
		dateField.setColumns(10);
		
		balanceField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, dateField, -36, SpringLayout.NORTH, balanceField);
		sl_contentPane.putConstraint(SpringLayout.WEST, balanceField, 89, SpringLayout.EAST, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 2, SpringLayout.NORTH, balanceField);
		contentPane.add(balanceField);
		balanceField.setColumns(10);
		
		JButton withdrawButton = new JButton("Withdraw");
		sl_contentPane.putConstraint(SpringLayout.EAST, customerField, -35, SpringLayout.WEST, withdrawButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, withdrawButton, 417, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, withdrawButton, 25, SpringLayout.SOUTH, DepositButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, withdrawButton, -12, SpringLayout.EAST, contentPane);
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amountString = JOptionPane.showInputDialog("Enter an amount to withdraw: ");
				double amount = Double.parseDouble(amountString);
				currentAccount.withdraw(amount);
				balanceField.setText(String.valueOf(currentAccount.getBalance()));
				Utility.writeFile(accounts,"accounts.txt");
			}
		});
		contentPane.add(withdrawButton);
		
		JButton Transfer = new JButton("Transfer");
		Transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String accountNumber = JOptionPane.showInputDialog("Enter an account to transfer to: ");
				Account transferAccount = null;
				
				for(Account a: accounts) {
					if(a.toString().contentEquals(accountNumber)) {
						transferAccount = a;
						break;
					}
				}
				String amountString = JOptionPane.showInputDialog("Enter an amount to transfer: ");
				double amount = Double.parseDouble(amountString);
				
				if(transferAccount != null) {
					currentAccount.transfer(transferAccount, amount);
					balanceField.setText(String.valueOf(currentAccount.getBalance()));
					Utility.writeFile(accounts,"accounts.txt");
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.EAST, dateField, -35, SpringLayout.WEST, Transfer);
		sl_contentPane.putConstraint(SpringLayout.WEST, Transfer, 0, SpringLayout.WEST, DepositButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, Transfer, -77, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Transfer, -5, SpringLayout.EAST, DepositButton);
		contentPane.add(Transfer);
		
		JButton Exit = new JButton("Exit");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, balanceField, 3, SpringLayout.NORTH, Exit);
		sl_contentPane.putConstraint(SpringLayout.EAST, balanceField, -35, SpringLayout.WEST, Exit);
		sl_contentPane.putConstraint(SpringLayout.WEST, Exit, 0, SpringLayout.WEST, DepositButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, Exit, -22, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Exit, -5, SpringLayout.EAST, DepositButton);
		contentPane.add(Exit);
		
		initiateAccounts();
	}
}
