package gtg_view_subsystem;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 */
public class MapDisplayPanel extends JPanel implements MouseListener, MouseMotionListener{
	private Point origin;
	private JScrollPane mapPanelHolder;
	private double scale;
	private BufferedImage image;
	/**
	 * Create the panel.
	
	
	 * @param mapPanelHolder JScrollPane
	 * @param mapurl String
	 */
	public MapDisplayPanel(JScrollPane mapPanelHolder, String mapurl) {
		this.mapPanelHolder = mapPanelHolder;
		addMouseListener(this);
		addMouseMotionListener(this);
		this.loadImage(mapurl);
	    this.scale = 1.0;
	}

	/**
	 * Method getPreferredSize.
	 * @return Dimension
	 */
	@Override
	public Dimension getPreferredSize() {
		int w = 0;
		int h = 0;
		if(this.image != null) {
	        w = (int)(this.scale * this.image.getWidth());
	        h = (int)(this.scale * this.image.getHeight());
		}
		
		return new Dimension(w, h);
    }
	  
	 /**
	  * Method setScale.
	  * @param s double
	  */
	 public void setScale(double s) {
        this.scale = s;
        this.revalidate();
        this.repaint();
	 }
	    
	/**
	 * Method paintComponent.
	 * @param g Graphics
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        AffineTransform at = AffineTransform.getTranslateInstance(0,0);
        g2.scale(this.scale, this.scale);
        g2.drawRenderedImage(this.image, at);
	}
	
	/**
	 * Method mousePressed.
	 * @param me MouseEvent
	 * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent me){
		this.origin = new Point(me.getPoint());
	}

	/**
	 * Method mouseClicked.
	 * @param me MouseEvent
	 * @see java.awt.event.MouseListener#mouseClicked(MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
	}

	/**
	 * Method mouseEntered.
	 * @param arg0 MouseEvent
	 * @see java.awt.event.MouseListener#mouseEntered(MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method mouseExited.
	 * @param arg0 MouseEvent
	 * @see java.awt.event.MouseListener#mouseExited(MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method mouseReleased.
	 * @param me MouseEvent
	 * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
	}

	/**
	 * Method mouseDragged.
	 * @param me MouseEvent
	 * @see java.awt.event.MouseMotionListener#mouseDragged(MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		 if (this.origin != null) {
             JViewport viewPort = this.mapPanelHolder.getViewport();
             if (viewPort != null) {
                 int deltaX = this.origin.x - me.getX();
                 int deltaY = this.origin.y - me.getY();

                 Rectangle view = viewPort.getViewRect();
                 view.x += deltaX;
                 view.y += deltaY;

                 this.scrollRectToVisible(view);
             }
         }
	}

	/**
	 * Method mouseMoved.
	 * @param arg0 MouseEvent
	 * @see java.awt.event.MouseMotionListener#mouseMoved(MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Method loadImage.
	 * @param mapurl String
	 */
	public void loadImage(String mapurl) {
        try {
            this.image = ImageIO.read(new File(mapurl));
        }
        catch(MalformedURLException mue) {
            //System.out.println("URL trouble: " + mue.getMessage());
        }
        catch(IOException ioe) {
        	//System.out.println("read trouble: " + ioe.getMessage());
        }
	}

	/**
	 * Method getScale.
	 * @return double
	 */
	public double getScale() {
		// TODO Auto-generated method stub
		return this.scale;
	}
	
	/**
	 * Method customScrollRect
	 * @param scrollPoint
	 */
	public void customScrollRect(Point scrollPoint){
		JViewport viewPort = this.mapPanelHolder.getViewport();
        if (viewPort != null) {
            Rectangle view = viewPort.getViewRect();
            view.x = scrollPoint.x - 50;
            view.y = scrollPoint.y - 50;

            this.scrollRectToVisible(view);
        }
	}
}
