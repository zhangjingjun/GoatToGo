package gtg_view_subsystem;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 */
public class Page extends JFrame {
	private JPanel mainPanel, headerPanel, dragpanel;
	private JLabel wpiLogoHolder, lblGoattogo;
	private ImageIcon wpiLogoImage, minimizeBtnImage, closeBtnImage;
	private JButton minimizeBtn, closeBtn, adminBtn, logoutBtn, helpBtn;
	private MainView parent;
	private int pX, pY;
	private BufferedReader buffer;
	private String helpContent = "";
	/**
	 * Create the frame.
	 * @param mainView 
	 */
	public Page(MainView mainView) {
		this.parent = mainView;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1366, 728);
		//this.setBounds(500, 200, 1057, 601);
		this.setUndecorated(true);
		this.getContentPane().setLayout(null);
		
		this.headerPanel = new JPanel();
		this.headerPanel.setBounds(0, 0, 1366, 67);
		this.headerPanel.setBackground(new Color(0xc30e2d));
		this.headerPanel.setLayout(null);
		this.getContentPane().add(this.headerPanel);

		this.wpiLogoHolder = new JLabel();
		this.wpiLogoHolder.setBounds(10, 6, 64, 56);
		this.wpiLogoImage = new ImageIcon(ImageURLS.WPI_SMALL_LOGO);
		this.wpiLogoHolder.setIcon(this.wpiLogoImage);
		this.headerPanel.add(this.wpiLogoHolder);
		
		this.lblGoattogo = new JLabel("GOAT-TO-GO");
		this.lblGoattogo.setFont(new Font("Meiryo", Font.PLAIN, 36));
		this.lblGoattogo.setForeground(Color.WHITE);
		this.lblGoattogo.setBounds(89, 16, 277, 46);
		this.headerPanel.add(this.lblGoattogo);

		this.minimizeBtn = new JButton();
		this.minimizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		this.minimizeBtn.setBounds(1285, 45, 22, 4);
		this.minimizeBtn.setContentAreaFilled(false);
		this.minimizeBtn.setBorder(null);
		this.minimizeBtnImage = new ImageIcon(ImageURLS.MINIMIZE_BUTTON);
		this.minimizeBtn.setIcon(this.minimizeBtnImage);
		this.headerPanel.add(this.minimizeBtn);

		this.closeBtn = new JButton();
		this.closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		this.closeBtn.setBounds(1322, 22, 29, 27);
		this.closeBtn.setContentAreaFilled(false);
		this.closeBtn.setBorder(null);
		this.closeBtnImage = new ImageIcon(ImageURLS.CLOSE_BUTTON);
		this.closeBtn.setIcon(this.closeBtnImage);
		this.headerPanel.add(this.closeBtn);
		
		String helpText = "<html><u>" + "Help" +"</u></html>";
		this.helpBtn = new JButton(helpText);
		this.helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 File myFile = new File("ModelFiles"+System.getProperty("file.separator")+ "GoattoGoUserManual.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    	showHelpPopup();
				    }
			}
		});
		this.helpBtn.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.helpBtn.setBackground(null);
		this.helpBtn.setForeground(Color.WHITE);
		this.helpBtn.setBounds(1180, 18, 70, 44);
		this.helpBtn.setBorder(null);
		this.helpBtn.setFocusPainted(false);
		headerPanel.add(this.helpBtn);

		String adminText = "<html><u>" + ViewStringLiterals.ADMIN +"</u></html>";
		this.adminBtn = new JButton(adminText);
		this.adminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showAdminLoginPage();
			}
		});
		this.adminBtn.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.adminBtn.setBackground(null);
		this.adminBtn.setForeground(Color.WHITE);
		this.adminBtn.setBounds(1100, 18, 70, 44);
		this.adminBtn.setBorder(null);
		this.adminBtn.setFocusPainted(false);
		headerPanel.add(this.adminBtn);
		
		String logoutText = "<html><u>" + ViewStringLiterals.LOGOUT +"</u></html>";
		this.logoutBtn = new JButton(logoutText);
		this.logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showWelcomePage();
			}
		});
		this.logoutBtn.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.logoutBtn.setBackground(null);
		this.logoutBtn.setForeground(Color.WHITE);
		this.logoutBtn.setBounds(1100, 18, 70, 44);
		this.logoutBtn.setBorder(null);
		this.logoutBtn.setFocusPainted(false);
		this.logoutBtn.setVisible(false);
		headerPanel.add(this.logoutBtn);

		this.dragpanel = new JPanel();
		this.dragpanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				setLocation(getLocation().x+mouseEvent.getX()-pX,getLocation().y+mouseEvent.getY()-pY);
			}
		});
		this.dragpanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				pX=mouseEvent.getX();
                pY=mouseEvent.getY();
			}
		});
		this.dragpanel.setBounds(0, 0, 1095, 67);
		this.dragpanel.setBackground(null);
		headerPanel.add(this.dragpanel);

		this.mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		this.mainPanel.setBounds(0, 67, 1366, 661);
		this.getContentPane().add(mainPanel);

		this.setVisible(true);
	}
	
	/**
	 * Method addPage.
	 * @param page JPanel
	 */
	public void addPage(JPanel page){
		this.mainPanel.setLayout(new java.awt.GridLayout());
		this.mainPanel.add(page);
		this.mainPanel.revalidate();
	}
	
	/**
	 * Method removePage.
	 * @param page JPanel
	 */
	public void removePage(JPanel page){
		this.mainPanel.remove(page);
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}
	
	/**
	 * Method hideAdminButton.
	 * @param none
	 * This method hides the admin button when the user is already logged in as admin.
	 */
	public void hideAdminButton(){
		this.adminBtn.setVisible(false);
	}
	
	/**
	 * Method showAdminButton.
	 * @param none
	 * This method shows the admin button on all the pages of a normal user.
	 */
	public void showAdminButton(){
		this.adminBtn.setVisible(true);
	}
	
	/**
	 * Method showLogoutButton.
	 * @param none
	 * This method shows the logout button so that admin can logout.
	 * This button is shown for add/delete map and edit map page.
	 */
	public void showLogoutButton(){
		this.logoutBtn.setVisible(true);
	}
	
	/**
	 * Method hideLogoutButton.
	 * @param none
	 */
	public void hideLogoutButton(){
		this.logoutBtn.setVisible(false);
	}
	
	/**
	 * Method showHelpPopup.
	 * @param none
	 * Create the UI to display the help document.
	 */
	public void showHelpPopup(){
		if(helpContent.equals("")){
			this.helpContent = loadHelpFile();
			if(this.helpContent.equals("FileNotFound")){
				JOptionPane.showMessageDialog(this, "Help not available");
			}else {
				HelpPage helpPage = new HelpPage(helpContent); 
				JOptionPane.showMessageDialog(this, helpPage, "HELP", JOptionPane.PLAIN_MESSAGE,null);
			}
		} else {
			HelpPage helpPage = new HelpPage(helpContent); 
			JOptionPane.showMessageDialog(this, helpPage, "HELP", JOptionPane.PLAIN_MESSAGE,null);
		}
	}
	
	/**
	 * Method loadHelpFile.
	 * @param none
	 * Loads the help document only for the first time.
	 */
	public String loadHelpFile(){
		String helpFileURL = "ModelFiles"+System.getProperty("file.separator")+ "helpDocument.txt";
		File file = null;
		buffer = null;
		String line;
		String helpContent = "";
		try{
			file = new File(helpFileURL);
			buffer = new BufferedReader(new FileReader(file));
			 if (file.exists()) {
				 while((line = buffer.readLine()) != null){
					 helpContent += line;
				 }
			 }else {
				 helpContent = "FileNotFound";  
			 }
		}catch (Exception ex) {
			 //System.out.println("Unable to load file");
			 helpContent = "FileNotFound"; 
		 }
		return helpContent;
	}
}
