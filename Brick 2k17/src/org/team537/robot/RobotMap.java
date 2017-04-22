package org.team537.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers floating around.
 */
public class RobotMap {
	/**
	 * A class that holds static values for driver Controls.
	 */
	public static class Driver {
		public static final int PRIMARY_PORT = 0;

		public static final double SENSITIVITY = 0.5;
	}

	/**
	 * A class that holds static values for extra robot constants.
	 */
	public static class Robot {
		public static final double DRIVE_SPEED_MIN = 0.05; 
		public static final double DRIVE_SPEED = 1.0;
	}

	/**
	 * A class that holds static values for joystick keys (Logitech F310).
	 */
	public static class JoystickKeys {
		public static final int A = 1;
		public static final int B = 2;
		public static final int X = 3;
		public static final int Y = 4;
		public static final int BUMPER_LEFT = 5;
		public static final int BUMPER_RIGHT = 6;
		public static final int BACK = 7;
		public static final int START = 8;
		public static final int LEFT = 9;
		public static final int RIGHT = 10;
	}

	/**
	 * A class that holds static values for joystick axes (Logitech F310).
	 */
	public static class JoystickAxes {
		public static final int LEFT_X = 0;
		public static final int LEFT_Y = 5;
		public static final int TRIGGER_LEFT = 2;
		public static final int TRIGGER_RIGHT = 3;
		public static final int RIGHT_X = 4;
		public static final int RIGHT_Y = 1;
	}

	/**
	 * A class that holds static values for PWM Channels.
	 */
	public static class PWM {
	}

	/**
	 * A class that holds static values for CAN bus Channels.
	 */
	public static class CAN {
		public static final int DRIVE_LEFT_1 = 1;
		public static final int DRIVE_LEFT_2 = 3;
		public static final int DRIVE_LEFT_3 = 5;
		public static final int DRIVE_RIGHT_1 = 2;
		public static final int DRIVE_RIGHT_2 = 4;
		public static final int DRIVE_RIGHT_3 = 6;
	}

	/**
	 * A class that holds static values for Digital Inputs.
	 */
	public static class Digital {
		public static final double DRIVE_IN_TO_ENCODER = 504.0163; // 1000 Edges Per Revolution: ((ticks / 1rev) * (3rev / 1rev) * (64revs / 20rev) * (1rev / 19.047in))
	}

	/**
	 * A class that holds static values for Analog Inputs.
	 */
	public static class Analog {
	}

	/**
	 * A class that holds static values for Solenoids.
	 */
	public static class Solenoid {
		public static final int DRIVE_SHIFTER = 4;
	}
}
