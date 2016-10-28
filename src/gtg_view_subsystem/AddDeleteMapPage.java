package gtg_view_subsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 */
public class AddDeleteMapPage extends JPanel{
	private JButton addBtn, deleteBtn, editBtn, previewBtn, browseFileBtn;
	private ImageIcon addBtnImage, deleteBtnImage, editBtnImage, previewBtnImage, browseFileBtnImage;
	private JLabel availableMapListLabel, mapPreviewLabel, mapPreviewHolder, buildingNameLabel, floorLabel, selectImageLabel, mapListEmpty, isCampusLabel;
	private MainView parent;
	private JScrollPane mapListscrollPane;
	private JList mapList;
	private ArrayList<String> mapListArray;
	private JPanel addDialogPanel;
	private JTextField buildingNameTextField, mapURLTextField, floorTextField;
	private String addMapURLPath = "";
	private JRadioButton yesBtn, noBtn;
	private ButtonGroup group;
	private String mapType = ViewStringLiterals.FLOOR;
	private JComboBox floorComboBox;
	/*
	 * Initialize the contents of the page.
	 */
	/**
	 * Constructor for AddDeleteMapPage.
	 * @param mainView MainView
	 */
	public AddDeleteMapPage(MainView mainView) {
		this.parent = mainView;
		this.setBounds(0, 67, 1366, 661);
		this.setBackground(new Color(0xf0e6e6));
		this.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d), 5));
		this.setLayout(null);

		this.availableMapListLabel = new JLabel(ViewStringLiterals.AVAILABLE_MAP_LIST);
		this.availableMapListLabel.setBounds(50, 40, 271, 30);
		this.availableMapListLabel.setFont(new Font("Meiryo", Font.PLAIN, 25));
		this.availableMapListLabel.setForeground(new Color(0xc30e2d));
		this.add(this.availableMapListLabel);

		this.addBtn = new JButton();
		
		// Handles the add button functionality.
		this.addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPopup();
				int result = JOptionPane.showConfirmDialog(((JButton)e.getSource()).getParent(), addDialogPanel, "Add new map",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					if(buildingNameTextField.getText().equals("")){
						JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), ViewStringLiterals.MAP_NAME_EMPTY, "INVALID", JOptionPane.ERROR_MESSAGE);
					} else if(mapURLTextField.getText().equals("")){
						JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), ViewStringLiterals.MAP_URL_EMPTY, "INVALID", JOptionPane.ERROR_MESSAGE);
					} /*else if(floorTextField.getText().equals("")){
						JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), ViewStringLiterals.FLOOR_NUMBER_EMPTY, "INVALID", JOptionPane.ERROR_MESSAGE);
					}*/ else {
						//parent.sendAddMapData(buildingNameTextField.getText() + "_" + floorTextField.getText(), addMapURLPath, mapType);
						if(mapType.equals(ViewStringLiterals.CAMPUS)){
							parent.sendAddMapData(buildingNameTextField.getText() + "_Campus", addMapURLPath, mapType);
						} else {
							parent.sendAddMapData(buildingNameTextField.getText() + "_" + floorComboBox.getSelectedItem(), addMapURLPath, mapType);
						}
					}
				}
			}
		});
		this.addBtn.setContentAreaFilled(false);
		this.addBtn.setBorder(null);
		this.addBtn.setBounds(536, 194, 72, 42);
		this.addBtnImage = new ImageIcon(ImageURLS.ADD_BUTTON);
		this.addBtn.setIcon(this.addBtnImage);
		this.add(addBtn);

		this.deleteBtn = new JButton();
		
		// Handles the delete button functionality.
		this.deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapList.getSelectedIndex() != -1){
					int result = JOptionPane.showConfirmDialog(null, ViewStringLiterals.DELETE_MAP_CONFIRM_MESSAGE + " " + mapList.getSelectedValue().toString() + "?",ViewStringLiterals.DELETE_MAP,
							JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION){
						parent.deleteMap(mapList.getSelectedValue().toString());
					}
				} else {
					JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), ViewStringLiterals.MAP_NOT_SELECTED, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.deleteBtn.setContentAreaFilled(false);
		this.deleteBtn.setBorder(null);
		this.deleteBtn.setBounds(526, 273, 90, 42);
		this.deleteBtnImage = new ImageIcon(ImageURLS.DELETE_BUTTON);
		this.deleteBtn.setIcon(this.deleteBtnImage);
		this.add(deleteBtn);
		
		this.editBtn = new JButton();
		
		// Handles the edit button functionality.
		this.editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapList.getSelectedIndex() != -1){
					parent.showAdminMapEditPage(mapList.getSelectedValue().toString());
				} else {
					JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), ViewStringLiterals.MAP_NOT_SELECTED, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.editBtn.setContentAreaFilled(false);
		this.editBtn.setBorder(null);
		this.editBtn.setBounds(536, 354, 68, 42);
		this.editBtnImage = new ImageIcon(ImageURLS.EDIT_BUTTON);
		this.editBtn.setIcon(this.editBtnImage);
		this.add(editBtn);
		
		this.previewBtn = new JButton();
		
		// Handles the preview button functionality.
		this.previewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapList.getSelectedIndex() != -1){
					parent.fetchMapURLAdmin(mapList.getSelectedValue().toString());
				} else {
					JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), ViewStringLiterals.MAP_NOT_SELECTED, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.previewBtn.setContentAreaFilled(false);
		this.previewBtn.setBorder(null);
		this.previewBtn.setBounds(521, 433, 100, 42);
		this.previewBtnImage = new ImageIcon(ImageURLS.PREVIEW_BUTTON);
		this.previewBtn.setIcon(this.previewBtnImage);
		this.add(previewBtn);

		this.mapPreviewLabel = new JLabel(ViewStringLiterals.MAP_PREVIEW);
		this.mapPreviewLabel.setBounds(689, 40, 159, 30);
		this.mapPreviewLabel.setFont(new Font("Meiryo", Font.PLAIN, 25));
		this.mapPreviewLabel.setForeground(new Color(0xc30e2d));
		this.add(this.mapPreviewLabel);
		
		this.mapListEmpty = new JLabel(ViewStringLiterals.MAP_LIST_EMPTY);
		this.mapListEmpty.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.mapListEmpty.setBounds(90, 325, 337, 20);
		this.mapListEmpty.setVisible(false);
		this.add(this.mapListEmpty);

		this.mapList = new JList();
		this.mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.mapList.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.mapList.setForeground(new Color(0xc30e2d));

		this.mapListscrollPane = new JScrollPane(mapList);
		this.mapListscrollPane.setBounds(50, 86, 401, 539);
		this.mapListscrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d), 2));
		this.add(this.mapListscrollPane);
		
		this.mapPreviewHolder = new JLabel();
		this.mapPreviewHolder.setBounds(689, 86, 642, 539);
		this.mapPreviewHolder.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d), 2));
		this.add(this.mapPreviewHolder);
		
		this.addDialogPanel = new JPanel();
		this.addDialogPanel.setLayout(null);
		this.addDialogPanel.setPreferredSize(new Dimension(500,250));
		
		this.isCampusLabel = new JLabel(ViewStringLiterals.IS_CAMPUS_MAP);
		this.isCampusLabel.setBounds(10, 10, 250, 30);
		this.isCampusLabel.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.isCampusLabel.setForeground(new Color(0xc30e2d));
	
		this.yesBtn = new JRadioButton();
		this.yesBtn.setText(ViewStringLiterals.YES);
		this.yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mapType = ViewStringLiterals.CAMPUS;
				buildingNameTextField.setEditable(false);
				//floorTextField.setEditable(false);
				floorComboBox.setEnabled(false);
				buildingNameTextField.setText(ViewStringLiterals.CAMPUS);
				//floorTextField.setText(ViewStringLiterals.DEFAULT_CAMPUS_FLOOR_NUMBER);
			}
		});
		this.yesBtn.setBounds(250, 10, 70, 25);
		this.yesBtn.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.yesBtn.setForeground(new Color(0xc30e2d));
		this.yesBtn.setBackground(null);

		this.noBtn = new JRadioButton();
		this.noBtn.setText(ViewStringLiterals.NO);
		this.noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mapType = ViewStringLiterals.FLOOR;
				buildingNameTextField.setEditable(true);
				//floorTextField.setEditable(true);
				floorComboBox.setEnabled(true);
				buildingNameTextField.setText("");
				//floorTextField.setText("");
			}
		});
		this.noBtn.setBounds(350, 10, 70, 25);
		this.noBtn.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.noBtn.setForeground(new Color(0xc30e2d));
		this.noBtn.setBackground(null);
		this.noBtn.setSelected(true);

		this.group = new ButtonGroup();
	    this.group.add(yesBtn);
	    this.group.add(noBtn);
	    
		this.buildingNameLabel = new JLabel(ViewStringLiterals.BUILDING_NAME + ":");
		this.buildingNameLabel.setBounds(10, 60, 185, 30);
		this.buildingNameLabel.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.buildingNameLabel.setForeground(new Color(0xc30e2d));
		
		this.buildingNameTextField = new JTextField();
		this.buildingNameTextField.setBounds(220, 55, 200, 41);
		this.buildingNameTextField.setColumns(10);
		this.buildingNameTextField.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.buildingNameTextField.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d),3));
		this.buildingNameTextField.setForeground(new Color(0xc30e2d));
		
		this.floorLabel = new JLabel(ViewStringLiterals.FLOOR + ":");
		this.floorLabel.setBounds(70, 120, 80, 30);
		this.floorLabel.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.floorLabel.setForeground(new Color(0xc30e2d));
		
		/*this.floorTextField = new JTextField();
		this.floorTextField.setBounds(220, 115, 200, 41);
		this.floorTextField.setColumns(10);
		this.floorTextField.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.floorTextField.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d),3));
		this.floorTextField.setForeground(new Color(0xc30e2d));*/
		
		String[] floorList = {ViewStringLiterals.FIRST_FLOOR, ViewStringLiterals.SECOND_FLOOR, ViewStringLiterals.THIRD_FLOOR, ViewStringLiterals.FOURTH_FLOOR, ViewStringLiterals.FIFTH_FLOOR, ViewStringLiterals.BASEMENT, ViewStringLiterals.SUB_BASEMENT};
		this.floorComboBox = new JComboBox(floorList);
		this.floorComboBox.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.floorComboBox.setBackground(null);
		this.floorComboBox.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d),3));
		this.floorComboBox.setBounds(220, 115, 200, 41);

		this.selectImageLabel = new JLabel(ViewStringLiterals.SELECT_MAP_IMAGE + ":");
		this.selectImageLabel.setBounds(10, 180, 195, 30);
		this.selectImageLabel.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.selectImageLabel.setForeground(new Color(0xc30e2d));
		
		this.mapURLTextField = new JTextField();
		this.mapURLTextField.setBounds(220, 175, 200, 41);
		this.mapURLTextField.setColumns(10);
		this.mapURLTextField.setFont(new Font("Meiryo", Font.PLAIN, 20));
		this.mapURLTextField.setBorder(BorderFactory.createLineBorder(new Color(0xc30e2d),3));
		this.mapURLTextField.setForeground(new Color(0xc30e2d));

		this.browseFileBtn = new JButton();
		
		// Handles the browse file button functionality.
		this.browseFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
				 FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "PNG & JPG Images", "jpg", "png");
				 fileChooser.setFileFilter(filter);
				 int returnValue = fileChooser.showOpenDialog(null);
				 if (returnValue == JFileChooser.APPROVE_OPTION) {
			          File selectedFile = fileChooser.getSelectedFile();
			          addMapURLPath = selectedFile.getAbsolutePath();
			          mapURLTextField.setText(selectedFile.getName());
				 }
			}
		});
		this.browseFileBtn.setContentAreaFilled(false);
		this.browseFileBtn.setBorder(null);
		this.browseFileBtn.setBounds(440, 165, 50, 50);
		this.browseFileBtnImage = new ImageIcon(ImageURLS.BROWSE_FILE_BUTTON);
		this.browseFileBtn.setIcon(this.browseFileBtnImage);

		this.addDialogPanel.add(this.buildingNameLabel);
		this.addDialogPanel.add(this.selectImageLabel);
		this.addDialogPanel.add(this.browseFileBtn);
		this.addDialogPanel.add(this.buildingNameTextField);
		this.addDialogPanel.add(this.mapURLTextField);
		this.addDialogPanel.add(this.isCampusLabel);
		this.addDialogPanel.add(this.yesBtn);
		this.addDialogPanel.add(this.noBtn);
		this.addDialogPanel.add(this.floorLabel);
		this.addDialogPanel.add(this.floorComboBox);
	}
	
	/*
	 * Displays the list of map names.
	 * input: ArrayList of mapnames.
	 */
	/**
	 * Method showMapList.
	 * @param listofMaps ArrayList<String>
	 */
	public void showMapList(ArrayList<String> listofMaps){
		if(listofMaps.size() == 0){
			this.mapListEmpty.setVisible(true);
		} else {
			this.mapListEmpty.setVisible(false);
		}

		this.mapListArray = listofMaps;
		this.mapList.setListData(listofMaps.toArray());
		this.mapListscrollPane.revalidate();
		this.mapListscrollPane.repaint();
		this.mapPreviewHolder.setIcon(null);
	}

	/*
	 * Displays the map in the map preview holder.
	 * input: Map url.
	 */
	/**
	 * Method showMapImage.
	 * @param mapurl String
	 */
	public void showMapImage(String mapurl) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(mapurl));
			Image scaled = img.getScaledInstance(this.mapPreviewHolder.getWidth(), this.mapPreviewHolder.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon mapImage = new ImageIcon(scaled);
			this.mapPreviewHolder.setIcon(mapImage);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, ViewStringLiterals.IMAGE_CANNOT_BE_LOADED, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * Displays error message if map cannot be added to the .txt file.
	 */
	public void showAddMapError() {
		JOptionPane.showMessageDialog(this, ViewStringLiterals.MAP_CANNOT_ADDED, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	/*
	 * Displays error message if map cannot be delete from the .txt file.
	 */
	public void showDeleteMapError() {
		JOptionPane.showMessageDialog(this, ViewStringLiterals.MAP_CANNOT_DELETE, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	/*
	 * Resets the values in the popup on click of add button
	 */
	public void resetPopup(){
		this.addMapURLPath = "";
		this.buildingNameTextField.setText("");
		this.floorComboBox.setEnabled(true);
		//this.floorTextField.setText("");
		this.mapURLTextField.setText("");
		this.noBtn.setSelected(true);
		this.buildingNameTextField.setEditable(true);
		//this.floorTextField.setEditable(true);
		
	}
}
