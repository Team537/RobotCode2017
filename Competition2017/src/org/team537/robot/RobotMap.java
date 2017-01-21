package org.team537.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * A class that holds static values for driver Controls.
	 */
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
		public static final int SECONDARY_PORT = 1;
	}

	/**
	 * A class that holds static values for extra robot constants.
	 */
	public static class Robot {
		public static final double WIDTH = 26.0;
		public static final double LENGTH = 26.0;
		public static final double RATIO = Math.sqrt((LENGTH * LENGTH) + (WIDTH * WIDTH));
		public static final boolean DRIVE_ENABLED = true;
		public static final double DRIVE_SPEED_MIN = 0.07; // Speeds from 0-1.
		public static final double DRIVE_SPEED = 1.0;
		public static final double CLIMB_SPEED = 1.0; // Speeds from 0-1.
	}

	/**
	 * A class that holds static values for GRIP image processing.
	 */
	public static class GRIP {
		public static final int IMAGE_WIDTH = 320;
		public static final int IMAGE_HEIGHT = 240;

		public static final double WEBCAM_FOV = 68.5;
		public static final double PERCEIVED_FOCAL_LENGTH = 0.0; // (F = (P * D)
																	// / H)
																	// Focal =
																	// (apparent
																	// height /
																	// distance
																	// real) /
																	// known
																	// height)

		public static final double BOILER_HIGH_VISION = 0.4318; // 86" - 69";
																// the distance
																// between the
																// vision
																// rectangles on
																// the boiler
																// (in metres).
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
		public static final int CLIMBER_SIM = 9;

		public static final int SHOOTER_1 = 6;
		public static final int SHOOTER_2 = 8;

		public static final int DRIVE_BACK_LEFT_ANGLE = 4;
		public static final int DRIVE_BACK_LEFT_DRIVE = 3;

		public static final int DRIVE_BACK_RIGHT_ANGLE = 5;
		public static final int DRIVE_BACK_RIGHT_DRIVE = 6;

		public static final int DRIVE_FRONT_LEFT_ANGLE = 2;
		public static final int DRIVE_FRONT_LEFT_DRIVE = 1;

		public static final int DRIVE_FRONT_RIGHT_ANGLE = 7;
		public static final int DRIVE_FRONT_RIGHT_DRIVE = 8;
	}

	/**
	 * A class that holds static values for Digital Inputs.
	 */
	public static class Digital {
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
