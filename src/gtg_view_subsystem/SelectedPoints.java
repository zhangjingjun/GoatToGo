package gtg_view_subsystem;

/**
 */
public class SelectedPoints {
	private int startX = 0;
	private int startY = 0;
	private String startMapName = "";
	private int endX = 0;
	private int endY = 0;
	private String endMapName = "";
	private Boolean isStartSelected = false;
	private Boolean isEndSelected = false;
	public SelectedPoints(){
		this.resetStart();
		this.resetEnd();
	}

	public void resetStart(){
		this.startX = 0;
		this.startY = 0;
		this.startMapName = "";
		this.isStartSelected = false;
	}

	public void resetEnd(){
		this.endX = 0;
		this.endY = 0;
		this.endMapName = "";
		this.isEndSelected = false;
	}

	/**
	 * Method setStartLocation.
	 * @param x int
	 * @param y int
	 * @param mapName String
	 */
	public void setStartLocation(int x, int y, String mapName){
		this.startX = x;
		this.startY = y;
		this.startMapName = mapName;
		this.isStartSelected = true;
	}
	
	/**
	 * Method setEndLocation.
	 * @param x int
	 * @param y int
	 * @param mapName String
	 */
	public void setEndLocation(int x, int y, String mapName){
		this.endX = x;
		this.endY = y;
		this.endMapName = mapName;
		this.isEndSelected = true;
	}
	
	/**
	 * Method getStartX.
	 * @return int
	 */
	public int getStartX(){
		return this.startX;
	}
	
	/**
	 * Method getStartY.
	 * @return int
	 */
	public int getStartY(){
		return this.startY;
	}
	
	/**
	 * Method getStartMapName.
	 * @return String
	 */
	public String getStartMapName(){
		return this.startMapName;
	}
	
	/**
	 * Method getEndX.
	 * @return int
	 */
	public int getEndX(){
		return this.endX;
	}
	
	/**
	 * Method getEndY.
	 * @return int
	 */
	public int getEndY(){
		return this.endY;
	}
	
	/**
	 * Method getEndMapName.
	 * @return String
	 */
	public String getEndMapName(){
		return this.endMapName;
	}
	
	/**
	 * Method arePointsSelected.
	 * @return Boolean
	 */
	public Boolean arePointsSelected(){
		return this.isStartSelected && this.isEndSelected;
	}
	
	/**
	 * Method isStartSelected.
	 * @return Boolean
	 */
	public Boolean isStartSelected(){
		return this.isStartSelected;
	}
	
	/**
	 * Method isEndSelected.
	 * @return Boolean
	 */
	public Boolean isEndSelected(){
		return this.isEndSelected;
	}
}
