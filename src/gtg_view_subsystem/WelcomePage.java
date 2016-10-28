package gtg_view_subsystem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 */
public class WelcomePage extends JPanel {
	private JButton continueBtn;
	private JLabel backgroundHolder, wpiLogoHolder, gtgTextHolder;
	private ImageIcon wpiLogoImage, gtgTextImage, continueBtnImage, backgroundImage;
	private MainView parent;
	/**
	 * Create the panel.
	 * @param mainView 
	 */
	public WelcomePage(MainView mainView) {
		this.parent = mainView;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1366,661));
		this.setLocation(0,0);

		this.wpiLogoHolder = new JLabel();
		this.wpiLogoHolder.setBounds(607, 81, 152, 143);
		this.wpiLogoImage = new ImageIcon(ImageURLS.WPI_BIG_LOGO);
		this.wpiLogoHolder.setIcon(this.wpiLogoImage);
		this.add(this.wpiLogoHolder);

		this.gtgTextHolder = new JLabel();
		this.gtgTextHolder.setBounds(294, 260, 778, 111);
		this.gtgTextImage = new ImageIcon(ImageURLS.GOAT_TO_GO);
		this.gtgTextHolder.setIcon(this.gtgTextImage);
		this.add(this.gtgTextHolder);

		this.continueBtn = new JButton();
		this.continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.showMapPage();
			}
		});
		this.continueBtn.setBounds(616, 408, 133, 42);
		this.continueBtnImage = new ImageIcon(ImageURLS.CONTINUE_BUTTON);
		this.continueBtn.setBorder(null);
		this.continueBtn.setContentAreaFilled(false);
		this.continueBtn.setIcon(this.continueBtnImage);
		this.add(this.continueBtn);
		
		this.backgroundHolder = new JLabel();
		this.backgroundHolder.setBounds(0, 0, 1366, 661);
		this.backgroundImage = new ImageIcon(ImageURLS.WELCOME_BACKGROUND);
		this.backgroundHolder.setIcon(this.backgroundImage);
		this.add(this.backgroundHolder);
	}

}
