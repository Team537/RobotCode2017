package org.team537.robot;

import org.team537.robot.commands.ClimberAction;
import org.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickPrimary;
	public final Joystick joystickSecondary;

	public OI() {
		joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
		joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);

	//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.X).whileHeld(new ClimberAction(true));
	//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.Y).whileHeld(new ClimberAction(false));
	//	new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.A).whileHeld(new ShooterShoot());
	}
}
