package gtg_view_subsystem;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 */
public class ImageURLS {
	private static final Path currentRelativePath = Paths.get("");
	private static final String s = currentRelativePath.toAbsolutePath().toString() + System.getProperty("file.separator");
	private static final String filePath = s + "images"+System.getProperty("file.separator")+"UI_icons"+System.getProperty("file.separator");
	public static final String LOGIN_BUTTON =filePath +"sign_in_btn.png";
	public static final String LOGIN_BACKGROUND =filePath+"login_background_new.png";
	public static final String BACK_BUTTON = filePath+"back_btn.png";
	public static final String WPI_SMALL_LOGO = filePath+"wpi_small_logo.png";
	public static final String MINIMIZE_BUTTON = filePath+"minimize_btn.png";
	public static final String CLOSE_BUTTON = filePath+"close_btn.png";
	public static final String ZOOM_IN_BUTTON = filePath+"zoom_in_btn.png";
	public static final String ZOOM_OUT_BUTTON = filePath+"zoom_out_btn.png";
	public static final String GET_DIRECTIONS_BUTTON = filePath+"get_drections_btn.png";
	public static final String CLEAR_BUTTON = filePath+"clear_btn.png";
	public static final String NEW_SEARCH_BUTTON = filePath+"new_search_btn.png";
	public static final String NEXT_BUTTON = filePath+"next_btn.png";
	public static final String PREVIOUS_BUTTON = filePath+"previous_btn.png";
	public static final String WPI_BIG_LOGO = filePath+"wpi_logo.png";
	public static final String GOAT_TO_GO = filePath+"goto_to_go_text.png";
	public static final String CONTINUE_BUTTON = filePath+"continue_btn.png";
	public static final String WELCOME_BACKGROUND = filePath+"login_background_new.png";
	public static final String CLEAR_ALL_BUTTON = filePath+"clear_all_btn.png";
	public static final String SAVE_BUTTON = filePath+"save_btn.png";
	public static final String LOCATION_IMAGE = filePath+"location_image.png";
	public static final String ADD_BUTTON = filePath+"add_btn.png";
	public static final String DELETE_BUTTON = filePath+"delete_btn.png";
	public static final String EDIT_BUTTON = filePath+"edit_btn.png";
	public static final String PREVIEW_BUTTON = filePath+"preview_btn.png";
	public static final String BROWSE_FILE_BUTTON = filePath+"browse_file_btn.png";
	public static final String LOCATION_END_ICON = filePath+"location_end_image.png";
	public static final String CAFE_BUTTON = filePath+"cafe.png";
	public static final String CLASSROOM_BUTTON = filePath+"classroom.png";
	public static final String ELEVATOR_BUTTON = filePath+"elevator.png";
	public static final String MENS_RESTROOM_BUTTON = filePath+"mens_restroom.png";
	public static final String WOMENS_RESTROOM_BUTTON = filePath+"womens_restroom.png";
	public static final String OFFICE_BUTTON = filePath+"office.png";
	public static final String VENDING_BUTTON = filePath+"vending.png";
	public static final String PARKING_LOT_BUTTON = filePath+"parking_lot.png";
	public static final String BUILDING_BUTTON = filePath+"building.png";
	public static final String STAIRS_BUTTON = filePath+"stairs.png";
}
