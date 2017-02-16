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
		public static final int SECONDARY_PORT = 1;
		public static final int TERTIARY_PORT = 2;

		public static final boolean ARCADE_DRIVE = false;
	}

	/**
	 * A class that holds static values for extra robot constants.
	 */
	public static class Robot {
		public static final double DRIVE_SPEED_MIN = 0.1; 
		public static final double DRIVE_SPEED = 1.0;
		public static final double CLIMB_SPEED = 1.0;
		public static final double COLLECT_SPEED = 1.0;
	}

	/**
	 * A class that holds static values for GRIP image processing.
	 */
	public static class GRIP {
		public static final int IMAGE_WIDTH = 320;
		public static final int IMAGE_HEIGHT = 240;
	}

	/**
	 * A class that holds static values for the robots LED strips.
	 */
	public static class LEDs {
		public static final double CHARGER_R = 0.90196078431;
		public static final double CHARGER_G = 0.08235294117;
		public static final double CHARGER_B = 0.08235294117;
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
		public static final int LEFT_Y = 1;
		public static final int TRIGGER_LEFT = 2;
		public static final int TRIGGER_RIGHT = 3;
		public static final int RIGHT_X = 4;
		public static final int RIGHT_Y = 5;
	}

	/**
	 * A class that holds static values for joystick keys (Logitech X3D Pro).
	 */
	public static class JoystickKeysX3D {
		public static final int INDEX_TRIGGER = 1;
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

	/**
	 * A class that holds static values for joystick axes (Logitech X3D Pro).
	 */
	public static class JoystickAxesX3D {
		public static final int STICK_X = 0;
		public static final int STICK_Y = 1;
		public static final int STICK_Z = 2;
		public static final int SLIDER = 3;
	}

	/**
	 * A class that holds static values for PWM Channels.
	 */
	public static class PWM {
	}

	/*
	 * A class that holds static values for CAN bus Channels.
	 */
	public static class CAN {
		public static final int DRIVE_LEFT_MASTER = 2;
		public static final int DRIVE_LEFT_NORMAL = 1;
		public static final int DRIVE_LEFT_MINI = 3;
		public static final int DRIVE_RIGHT_MASTER = 5;
		public static final int DRIVE_RIGHT_NORMAL = 4;
		public static final int DRIVE_RIGHT_MINI = 6;

		public static final int SHOOTER_MASTER = 7;
		public static final int SHOOTER_SLAVE = 8;
		
		public static final int COLLECTOR = 9;
		
		public static final int FEEDER = 10;
		
		public static final int CLIMBER = 11;
	}

	/**
	 * A class that holds static values for Digital Inputs.
	 */
	public static class Digital {
		public static final double DRIVE_IN_TO_ENCODER = 1.0 / (0.14605 * Math.PI); // Metre => Encoder Ticks: (distance 'm' / (wheel diameter 'm' * PI)).
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
	}
}
