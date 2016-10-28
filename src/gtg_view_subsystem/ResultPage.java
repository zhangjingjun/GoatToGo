package gtg_view_subsystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 */
public class ResultPage extends JPanel {
	private JTextField fromTextField, toTextField, currentMapName, totalMaps, totalDistanceValue, totalTimeValue;
	private JPanel leftPanel, rightPanel;
	private JButton zoomInBtn, zoomOutBtn, newSearchBtn, nextBtn ,previousBtn;
	private ImageIcon zoomInBtnImage, zoomOutBtnImage, newSearchBtnImage, nextBtnImage, previousBtnImage, fromLocationIconImage, toLocationIconImage;
	private JLabel fromLabel, toLabel, noPathAvailable, fromLocationIcon, toLocationIcon, totalDistance, totalTime;
	private MainView parent;
	private JScrollPane mapPanelHolder;
	private JLayeredPane layeredPane;
	private double MAX_ZOOM_IN = 2.0;
	private double MAX_ZOOM_OUT = 1.0;
	private double currentZoomValue = 1.0;
	private double zoomFactor = 0.1;
	private ResultMapDisplayPanel resultMapDisplayPanel;
	private int index = 0;
	private int totalMapsValue = 0;
	/**
	 * Create the panel.
	 * @param mainView 
	 */
	public ResultPage(MainView mainView) {
		this.parent = mainView;
		this.setBounds(0, 67, 1366, 661);
		this.setBackground(new Color(0xf0e6e6));
		this.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d), 5));
		this.setLayout(null);

		this.leftPanel = new JPanel();
		this.leftPanel.setBounds(5, 5, 950, 650);
		this.leftPanel.setLayout(null);
		this.leftPanel.setBackground(new Color(0xe0dede));
		this.add(this.leftPanel);

		this.layeredPane = new JLayeredPane();
		this.layeredPane.setBounds(0, 0, 950, 650);
		this.leftPanel.add(this.layeredPane);

		this.noPathAvailable = new JLabel(ViewStringLiterals.NO_PATH_AVAILABLE);
		this.noPathAvailable.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.noPathAvailable.setForeground(new Color(0x5b1010));
		this.noPathAvailable.setBounds(250, 310, 522, 30);
		this.noPathAvailable.setVisible(false);
		this.layeredPane.add(this.noPathAvailable, new Integer(1));

		this.zoomInBtn = new JButton();
		zoomInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(resultMapDisplayPanel != null){
					if(currentZoomValue + zoomFactor <= MAX_ZOOM_IN){
						currentZoomValue = currentZoomValue + zoomFactor;
						resultMapDisplayPanel.setScale(currentZoomValue);
					}
				}
			}
		});
		this.zoomInBtn.setBounds(895, 5, 50, 50);
		this.zoomInBtn.setContentAreaFilled(false);
		this.zoomInBtn.setBorder(null);
		this.zoomInBtnImage = new ImageIcon(ImageURLS.ZOOM_IN_BUTTON);
		this.zoomInBtn.setIcon(this.zoomInBtnImage);
		this.layeredPane.add(this.zoomInBtn, new Integer(1));

		this.zoomOutBtn = new JButton();
		zoomOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(resultMapDisplayPanel != null){
					if(currentZoomValue - zoomFactor >= MAX_ZOOM_OUT){
						currentZoomValue = currentZoomValue - zoomFactor;
						resultMapDisplayPanel.setScale(currentZoomValue);
					}
				}
			}
		});
		this.zoomOutBtn.setBounds(895, 60, 51, 50);
		this.zoomOutBtn.setContentAreaFilled(false);
		this.zoomOutBtn.setBorder(null);
		this.zoomOutBtnImage = new ImageIcon(ImageURLS.ZOOM_OUT_BUTTON);
		this.zoomOutBtn.setIcon(this.zoomOutBtnImage);
		this.layeredPane.add(this.zoomOutBtn, new Integer(1));

		this.mapPanelHolder = new JScrollPane();
		this.mapPanelHolder.setBounds(0, 0, 950, 650);
		this.mapPanelHolder.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.mapPanelHolder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.layeredPane.add(this.mapPanelHolder, new Integer(0));

		Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		this.mapPanelHolder.setViewportBorder(border);
		this.mapPanelHolder.setBorder(border);

		this.rightPanel = new JPanel();
		this.rightPanel.setBounds(955, 5, 405, 650);
		this.rightPanel.setLayout(null);
		this.rightPanel.setBackground(null);
		this.add(this.rightPanel);

		this.fromLabel = new JLabel(ViewStringLiterals.FROM + " :");
		this.fromLabel.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.fromLabel.setBounds(30, 41, 98, 25);
		this.fromLabel.setForeground(new Color(0x5b1010));
		this.rightPanel.add(this.fromLabel);
		
		this.fromLocationIcon = new JLabel();
		this.fromLocationIcon.setBounds(30, 80, 20, 25);
		this.fromLocationIconImage = new ImageIcon(ImageURLS.LOCATION_IMAGE);
		this.fromLocationIcon.setIcon(this.fromLocationIconImage);
		this.rightPanel.add(this.fromLocationIcon);

		this.toLabel = new JLabel(ViewStringLiterals.TO + " :");
		this.toLabel.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.toLabel.setBounds(30, 165, 57, 25);
		this.toLabel.setForeground(new Color(0x5b1010));
		this.rightPanel.add(this.toLabel);
		
		this.toLocationIcon = new JLabel();
		this.toLocationIcon.setBounds(30, 201, 20, 25);
		this.toLocationIconImage = new ImageIcon(ImageURLS.LOCATION_END_ICON);
		this.toLocationIcon.setIcon(this.toLocationIconImage);
		this.rightPanel.add(this.toLocationIcon);

		this.fromTextField = new JTextField();
		this.fromTextField.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.fromTextField.setEditable(false);
		this.fromTextField.setBounds(60, 70, 310, 47);
		this.fromTextField.setColumns(10);
		this.fromTextField.setForeground(new Color(0x5b1010));
		this.fromTextField.setBorder(null);
		this.rightPanel.add(this.fromTextField);

		this.toTextField = new JTextField();
		this.toTextField.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.toTextField.setEditable(false);
		this.toTextField.setColumns(10);
		this.toTextField.setBounds(60, 191, 310, 47);
		this.toTextField.setForeground(new Color(0x5b1010));
		this.toTextField.setBorder(null);
		this.rightPanel.add(this.toTextField);

		this.nextBtn = new JButton();
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				parent.getNextPrevPath(index);
			}
		});
		this.nextBtn.setBounds(330, 300, 56, 90);
		this.nextBtn.setContentAreaFilled(false);
		this.nextBtn.setBorder(null);
		this.nextBtnImage = new ImageIcon(ImageURLS.NEXT_BUTTON);
		this.nextBtn.setIcon(this.nextBtnImage);
		this.rightPanel.add(this.nextBtn);


		this.previousBtn = new JButton();
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index--;
				parent.getNextPrevPath(index);
			}
		});
		this.previousBtn.setBounds(10, 300, 58, 90);
		this.previousBtn.setContentAreaFilled(false);
		this.previousBtn.setBorder(null);
		this.previousBtnImage = new ImageIcon(ImageURLS.PREVIOUS_BUTTON);
		this.previousBtn.setIcon(this.previousBtnImage);
		this.rightPanel.add(this.previousBtn);
		
		this.currentMapName = new JTextField();
		this.currentMapName.setBackground(null);
		this.currentMapName.setEditable(false);
		this.currentMapName.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.currentMapName.setBounds(70, 320, 260, 37);
		this.currentMapName.setForeground(new Color(0x5b1010));
		this.currentMapName.setBorder(null);
		this.currentMapName.setHorizontalAlignment(JTextField.CENTER);
		this.rightPanel.add(this.currentMapName);
		this.currentMapName.setColumns(10);
		
		this.totalMaps = new JTextField();
		this.totalMaps.setBackground(null);
		this.totalMaps.setEditable(false);
		this.totalMaps.setFont(new Font("Meiryo", Font.PLAIN, 16));
		this.totalMaps.setForeground(new Color(0x5b1010));
		this.totalMaps.setBounds(160, 355, 98, 26);
		this.totalMaps.setBorder(null);
		this.totalMaps.setHorizontalAlignment(JTextField.CENTER);
		this.rightPanel.add(this.totalMaps);
		this.totalMaps.setColumns(10);

		this.totalDistance = new JLabel(ViewStringLiterals.TOTAL_DISTANCE + " :");
		this.totalDistance.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.totalDistance.setBounds(10, 425, 217, 25);
		this.totalDistance.setForeground(new Color(0x5b1010));
		this.rightPanel.add(this.totalDistance);
		
		this.totalTime = new JLabel(ViewStringLiterals.TOTAL_TIME + " :");
		this.totalTime.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.totalTime.setBounds(10, 487, 185, 25);
		this.totalTime.setForeground(new Color(0x5b1010));
		this.rightPanel.add(this.totalTime);

		this.totalDistanceValue = new JTextField();
		this.totalDistanceValue.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.totalDistanceValue.setEditable(false);
		this.totalDistanceValue.setBounds(210, 415, 190, 47);
		this.totalDistanceValue.setColumns(10);
		this.totalDistanceValue.setForeground(new Color(0x5b1010));
		this.totalDistanceValue.setBorder(null);
		this.rightPanel.add(this.totalDistanceValue);

		this.totalTimeValue = new JTextField();
		this.totalTimeValue.setFont(new Font("Meiryo", Font.PLAIN, 24));
		this.totalTimeValue.setEditable(false);
		this.totalTimeValue.setColumns(10);
		this.totalTimeValue.setBounds(210, 476, 190, 47);
		this.totalTimeValue.setForeground(new Color(0x5b1010));
		this.totalTimeValue.setBorder(null);
		this.rightPanel.add(this.totalTimeValue);

		this.newSearchBtn = new JButton();
		this.newSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showMapPage();
			}
		});

		this.newSearchBtn.setBounds(128, 558, 173, 42);
		this.newSearchBtn.setContentAreaFilled(false);
		this.newSearchBtn.setBorder(null);
		this.newSearchBtnImage = new ImageIcon(ImageURLS.NEW_SEARCH_BUTTON);
		this.newSearchBtn.setIcon(this.newSearchBtnImage);
		this.rightPanel.add(this.newSearchBtn);
	}
	/**
	 * Method displayPath.
	 * @param path PathData
	 */
	public void displayPath(PathData path, int currentMapIndex) {
		this.noPathAvailable.setVisible(false);
		this.index = currentMapIndex;

		Point tempPoint = path.getStartPoint();
		this.fromTextField.setText(parent.getStartEndNodeDescription("FROM"));
		tempPoint = path.getEndPoint();
		this.toTextField.setText(parent.getStartEndNodeDescription("TO"));
		
		this.totalMapsValue = path.getArrayOfMapNames().size();
		this.currentMapName.setText(parent.mapNameToHuman(path.getArrayOfMapNames().get(currentMapIndex)));
		this.currentMapName.setName(path.getArrayOfMapNames().get(currentMapIndex));
		this.totalMaps.setText((currentMapIndex + 1) + " / " + this.totalMapsValue);
		

		this.resultMapDisplayPanel = new ResultMapDisplayPanel(this, this.mapPanelHolder, this.currentMapName.getName(), path.getMapURL());
		this.resultMapDisplayPanel.setVisible(true);
		this.mapPanelHolder.setViewportView(resultMapDisplayPanel);
		this.currentZoomValue = 1.0;
		
		this.resultMapDisplayPanel.displayPoints(path.getWayPoints());
		this.resultMapDisplayPanel.customScrollRect();

		if(totalMapsValue == 1){
			this.nextBtn.setEnabled(false);
			this.previousBtn.setEnabled(false);
		} else {
			if(this.index <= 0){
				this.nextBtn.setEnabled(true);
				this.previousBtn.setEnabled(false);
			} else if(this.index >= this.totalMapsValue - 1){
				this.nextBtn.setEnabled(false);
				this.previousBtn.setEnabled(true);
			} else {
				this.nextBtn.setEnabled(true);
				this.previousBtn.setEnabled(true);
			}
				
		}
	}
	
	/*
	 * Method pathNotAvailable.
	 * @param none
	 * This method displays error text in the map area if path is not available
	 */
	public void pathNotAvailable(){
		this.noPathAvailable.setVisible(true);
		this.fromTextField.setText("");
		this.toTextField.setText("");
		this.totalDistanceValue.setText("");
		this.totalTimeValue.setText("");
		this.nextBtn.setEnabled(false);
		this.previousBtn.setEnabled(false);
		if(this.resultMapDisplayPanel != null){
			this.resultMapDisplayPanel.setVisible(false);
		}
	}
	
	/*
	 * Method timeConversion.
	 * @param totalSeconds int
	 * This method converts the seconds into hours, minutes, second
	 */
	private static String timeConversion(int totalSeconds) {
		String time = "";
	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
	    int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
	    int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
	    int hours = totalMinutes / MINUTES_IN_AN_HOUR;

	    if(hours != 0){
	    	time += hours + " hr ";
	    }
	    
	    if(minutes != 0){
	    	time += minutes + " min ";
	    }
	    
	    if(seconds != 0){
	    	time += seconds + " sec ";
	    }
	    return time;
	}

	/*
	 * Method displayTimeDistance.
	 * @param pathTime int
	 * 		  pathLength double
	 * This method displays the total time and distance for the calculated path
	 */
	public void displayTimeDistance(double pathLength, int pathTime) {
		int distance = (int) Double.parseDouble(""+pathLength);
		this.totalDistanceValue.setText("" + distance + " " + ViewStringLiterals.METERS);
		this.totalTimeValue.setText(timeConversion(pathTime));
	}
}
