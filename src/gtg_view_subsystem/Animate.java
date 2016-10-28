package gtg_view_subsystem;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Animate {
    private JPanel panel;
    private int from;
    private int to;
    private MapPage parent;
    private long startTime;

    /**
	 * Method Animate
	 * @param none
	 * Empty constructor.
	 */
    public Animate() {
    }

    /**
	 * Method setAnimationPanel
	 * @param parent MapPage
	 * 		  panel JPanel
	 * 		  from int
	 * 		  to int
	 * This method initilaizes the panel to be moved.
	 * Stores the from and to location for animation effect.
	 */
    public void setAnimationPanel(MapPage parent, JPanel panel, int from, int to){
    	this.parent = parent;
    	this.panel = panel;
    	this.from = from;
    	this.to = to;
    	
    }

    /**
	 * Method startAnimationLeft
	 * @param none
	 * Slides the panel to the left.
	 */
    public void startAnimationLeft() {
    	parent.animationStarted();
    	Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	from = from - 1;
            	panel.setLocation(from, 0);
               if(from <= to){
            	   ((Timer)e.getSource()).stop();
               		parent.animationEnd();
               }
            }
        });
        timer.start();
       
    }
    
    /**
   	 * Method startAnimationRight
   	 * @param none
   	 * Slides the panel to the right.
   	 */
    public void startAnimationRight() {
    	parent.animationStarted();
    	Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	from = from + 1;
            	panel.setLocation(from, 0);
               if(from >= to){
            	   ((Timer)e.getSource()).stop();
            	   parent.animationEnd();
               }
            }
        });
        timer.start();
       
    }
}
