package org.team537.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	public final Joystick joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);

	public OI() {
		//// Space for creating buttons.
		// One type of button is a joystick button which is any button on a joystick. You create one by telling it which joystick it's on and which button number it is.
		// Joystick joystick = new Joystick(port);
		// Button button = new JoystickButton(joystick, buttonNumber);

		// There are a few additional built in buttons you can use. Additionally, by subclassing Button you can create custom triggers and bind those to commands the same as any other Button.

		//// TRIGGERING COMMANDS WITH BUTTONS.
		// Once you have a button, it's trivial to bind it to a button in one of three ways:

		// Start the command when the button is pressed and let it run the command until it is finished as determined by it's isFinished method.
		// button.whenPressed(new CommandExample());

		// Run the command while the button is being held down and interrupt it once the button is released.
		// button.whileHeld(new CommandExample());

		// Start the command when the button is released and let it run the command until it is finished as determined by it's isFinished method.
		// button.whenReleased(new CommandExample());
	}
}
