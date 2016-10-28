package gtg_view_subsystem;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class InfoPage extends JPanel {
	
	private JPanel infoPage;
	
	private JPanel imageHolder;
	private BufferedImage image;
	
	private JPanel rightPanel;
	private JLabel titleName;
	private JTextArea descriptionArea;
	
	private JPanel contactInfoPanel;
	private JLabel address;
	private JLabel phoneNum;
	
	public InfoPage(String buildingName, String ImageURL, String Description){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Create splitPane
        JSplitPane splitPane = new JSplitPane();
		if(!loadImage(ImageURL)){
			//System.out.println("Image Loading Failed, can't create Info page");
			return;
		}
		imageHolder = new JPanel(){
        	@Override
        	protected void paintComponent(Graphics g){
        		super.paintComponent(g);
        		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        	}
        	
        	@Override
        	public Dimension getPreferredSize(){
        		Dimension size = super.getPreferredSize();
        		size.width = image.getWidth();
        		size.height = image.getHeight();
        		return size;
        	}
        };
        splitPane.setDividerSize(5);
        splitPane.setDividerLocation(image.getWidth());
        splitPane.setLeftComponent(imageHolder);
        
        // Right PANEL
        rightPanel = new JPanel(){
        	@Override
        	 public Dimension getPreferredSize(){
         		Dimension size = super.getPreferredSize();
         		size.width = 205;
         		size.height = image.getHeight();
         		return size;
         		}
         	};
         	
        titleName = new JLabel(){
         	@Override
         	 public Dimension getPreferredSize(){
          		Dimension size = super.getPreferredSize();
          		size.width = rightPanel.getWidth();
          		size.height = 20;
          		return size;
          		} 
         };
        titleName.setBorder(null);
        titleName.setText(buildingName+" :");
         
        rightPanel.add(titleName);        

        descriptionArea = new JTextArea(){
        	@Override
        	public Dimension getPreferredSize(){
        		Dimension size = super.getPreferredSize();
        		size.width = rightPanel.getWidth();
        		size.height = 500;
        		return size;
        	}
        };
        descriptionArea.setText(Description);	
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setAutoscrolls(true);        
        JScrollPane sp = new JScrollPane(descriptionArea){
            public Dimension getPreferredSize(){
    		Dimension size = super.getPreferredSize();
    		size.width = rightPanel.getWidth();
    		size.height = rightPanel.getHeight()-titleName.getHeight();
    		return size;
    		}
    	};
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        descriptionArea.setCaretPosition(0);
        descriptionArea.setBackground(titleName.getBackground());
        descriptionArea.setBorder(null);        
        sp.setViewportView(descriptionArea);
        sp.setEnabled(true);
        sp.setBorder(null);
        
        rightPanel.add(sp);
        
        splitPane.setRightComponent(rightPanel);
        
        splitPane.setEnabled(false);

        address = new JLabel(){
            public Dimension getPreferredSize(){
    		Dimension size = super.getPreferredSize();
    		size.width = 500;
    		size.height = 20;
    		return size;
    		}
    	};
    	address.setHorizontalAlignment(SwingConstants.LEFT);
    	address.setText("Address: 100 Institute Road, Worcester, MA 01609-2280");
    	
        phoneNum = new JLabel();  
    	phoneNum.setHorizontalAlignment(SwingConstants.LEFT);
    	phoneNum.setText("Phone Num: +1-508-831-5000");
    	
    	contactInfoPanel = new JPanel(){
            public Dimension getPreferredSize(){
    		Dimension size = super.getPreferredSize();
    		size.width = address.getWidth();
    		size.height = 60;
    		return size;
    		}
    	};
    	
    	contactInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    	contactInfoPanel.add(address);
    	contactInfoPanel.add(phoneNum);
    	
    	int width = image.getWidth() + 210;
    	int height = image.getHeight() + 60;    	
    	
    	infoPage = new JPanel(){
            public Dimension getPreferredSize(){
    		Dimension size = super.getPreferredSize();
    		size.width = width;
    		size.height = height;
    		return size;
    		}
    	};
    	
    	infoPage.add(splitPane);
    	infoPage.add(contactInfoPanel);   

    	add(infoPage);
	}
	
	
	private boolean loadImage(String mapurl) {
        try {
            this.image = ImageIO.read(new File(mapurl));
            return true;
        }
        catch(MalformedURLException mue) {
            //System.out.println("URL trouble: " + mue.getMessage());
        }
        catch(IOException ioe) {
        	//System.out.println("read trouble: " + ioe.getMessage());        	
        }
        return false;
	}

}
