package org.team537.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
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
     * A class that holds static values for PWM Channels.
     */
    public static class PWM {
    }

    /**
     * A class that holds static values for CAN bus Channels.
     */
    public static class CAN {
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
