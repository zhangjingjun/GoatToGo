package gtg_view_subsystem;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 */
public class LoginPage extends JPanel {
	private JTextField usernameTextField, passwordTextField;
	private JLabel adminLoginLabel, usernameLabel, passwordLabel, backgroundHolder;
	private JButton loginBtn, backBtn;
	private ImageIcon loginBtnImage, backgroundImage, backBtnImage;
	private MainView parent;
	/**
	 * Create the panel.
	 * @param mainView 
	 */
	public LoginPage(MainView mainView) {
		this.parent = mainView;
		this.setBounds(0, 67, 1366, 661);
		this.setLayout(null);

		this.adminLoginLabel = new JLabel(ViewStringLiterals.ADMIN_LOGIN);
		this.adminLoginLabel.setBounds(577, 94, 212, 30);
		this.adminLoginLabel.setFont(new Font("Meiryo", Font.PLAIN, 30));
		this.adminLoginLabel.setForeground(new Color(0xc30e2d));
		this.add(this.adminLoginLabel);

		this.usernameLabel = new JLabel(ViewStringLiterals.USERNAME);
		this.usernameLabel.setBounds(428, 222, 122, 30);
		this.usernameLabel.setFont(new Font("Meiryo", Font.PLAIN, 25));
		this.usernameLabel.setForeground(new Color(0xc30e2d));
		this.add(this.usernameLabel);

		this.usernameTextField = new JTextField();
		this.usernameTextField.setBounds(565, 211, 251, 41);
		this.usernameTextField.setColumns(10);
		this.usernameTextField.setFont(new Font("Meiryo", Font.PLAIN, 25));
		this.usernameTextField.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d),3));
		this.usernameTextField.setForeground(new Color(0xc30e2d));
		this.add(this.usernameTextField);

		this.passwordLabel = new JLabel(ViewStringLiterals.PASSWORD);
		this.passwordLabel.setBounds(433, 294, 122, 30);
		this.passwordLabel.setFont(new Font("Meiryo", Font.PLAIN, 25));
		this.passwordLabel.setForeground(new Color(0xc30e2d));
		this.add(this.passwordLabel);

		this.passwordTextField = new JPasswordField();
		this.passwordTextField.setBounds(565, 291, 251, 41);
		this.passwordTextField.setColumns(10);
		this.passwordTextField.setFont(new Font("Meiryo", Font.PLAIN, 25));
		this.passwordTextField.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d),3));
		this.passwordTextField.setForeground(new Color(0xc30e2d));
		this.add(this.passwordTextField);

		this.loginBtn = new JButton();
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameTextField.getText().equals("")){
					JOptionPane.showMessageDialog(null, ViewStringLiterals.USERNAME_NOT_ENTERED, "INVALID", JOptionPane.ERROR_MESSAGE);
				} else if(passwordTextField.getText().equals("")){
					JOptionPane.showMessageDialog(null, ViewStringLiterals.PASSWORD_NOT_ENTERED, "INVALID", JOptionPane.ERROR_MESSAGE);
				} else {
					parent.checkAdminValid(usernameTextField.getText(), passwordTextField.getText());
				}
			}
		});
		this.loginBtn.setContentAreaFilled(false);
		this.loginBtn.setBorder(null);
		this.loginBtn.setBounds(565, 404, 110, 42);
		this.loginBtnImage = new ImageIcon(ImageURLS.LOGIN_BUTTON);
		this.loginBtn.setIcon(this.loginBtnImage);
		this.add(loginBtn);

		this.backBtn = new JButton();
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showWelcomePage();
			}
		});
		this.backBtn.setContentAreaFilled(false);
		this.backBtn.setBorder(null);
		this.backBtn.setBounds(728, 404, 88, 42);
		this.backBtnImage = new ImageIcon(ImageURLS.BACK_BUTTON);
		this.backBtn.setIcon(this.backBtnImage);
		this.add(backBtn);

		this.backgroundHolder = new JLabel();
		this.backgroundHolder.setBounds(0, 0, 1366, 661);
		this.backgroundImage = new ImageIcon(ImageURLS.LOGIN_BACKGROUND);
		this.backgroundHolder.setIcon(this.backgroundImage);
		this.add(backgroundHolder);
	}
	
	/**
	 * Method showInvalidUsernameDialog.
	 * @param none
	 * Displays error popup when username and passoword is not correct
	 */
	public void showInvalidUsernameDialog(){
		JOptionPane.showMessageDialog(null, ViewStringLiterals.USERNAME_PASSWORD_NOT_CORRECT, "INVALID", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Method reset.
	 * @param none
	 * Reset the fields when the page is displayed again.
	 */
	public void reset(){
		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
	}
}
