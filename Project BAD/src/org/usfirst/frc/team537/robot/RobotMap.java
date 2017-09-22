package org.usfirst.frc.team537.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
		public static final int SECONDARY_PORT = 1;
		
		public static final double SENSITIVITY = 0.5;
	}
	
	public static final double DRIVE_SPEED_MIN = 0.05;
	public static final double DRIVE_SPEED = 1;
		
	public static class JoystickKeys {
		public static final int A_BUTTON = 1;
		public static final int THUMB_TRIGGER = 2;
		public static final int STICK_3 = 3;
		public static final int STICK_4 = 4;
		public static final int STICK_5 = 5;
		public static final int STICK_6 = 6;
		public static final int BASE_7 = 7;
		public static final int BASE_8 = 8;
		public static final int BASE_9 = 9;
		public static final int BASE_10 = 10;
		public static final int BASE_11 = 11;
		public static final int BASE_12 = 12;
	}
	
	public static class JoystickAxes {
		public static final int STICK_LEFT_X = 0;
		public static final int STICK_LEFT_Y = 1;
		public static final int STICK_RIGHT_X = 4;
		public static final int STICK_RIGHT_Y = 5;
		public static final int THROTTLE = 3;
	}
	
	public static class Boxkeys {
		public static final int RED_BUTTON = 1;
		public static final int COVERED_SWITCH = 2;
		public static final int FIRST_SWITCH_UP = 4;
		public static final int SECOND_SWITCH_UP = 8;
		public static final int SECOND_SWITCH_DOWN = 7;
		public static final int RED_SWITCH_UP = 5;
		public static final int RED_SWITCH_DOWN = 6;
	}
	
	public static class CAN {
		public static final int DRIVE_LEFT_MASTER = 2;
		public static final int DRIVE_LEFT_NORMAL = 1;
		public static final int DRIVE_LEFT_MINI = 3;
		public static final int DRIVE_RIGHT_MASTER = 5;
		public static final int DRIVE_RIGHT_NORMAL = 4;
		public static final int DRIVE_RIGHT_MINI = 6;
	}

}
