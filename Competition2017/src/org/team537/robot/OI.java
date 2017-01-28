package org.team537.robot;

import org.team537.robot.commands.ShooterAddSpeedWheel1;
import org.team537.robot.commands.ShooterAutoFire;
import org.team537.robot.commands.ShooterDefault;
import org.team537.robot.commands.ShooterEnableWheel1;
import org.team537.robot.commands.ShooterEnableWheel2;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	public final Joystick joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);

	public OI() {
		// new JoystickButton(joystickSecondary,
		// RobotMap.JoystickKeys.X).whileHeld(new ClimberAction(true));
		// new JoystickButton(joystickSecondary,
		// RobotMap.JoystickKeys.Y).whileHeld(new ClimberAction(false));
		// new JoystickButton(joystickPrimary,
		new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.A).toggleWhenPressed(new ShooterDefault());

		new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.B).whenPressed(new ShooterEnableWheel1());
		//new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.X).whenPressed(new ShooterEnableWheel2());
		//new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.START).whenPressed(new ShooterAddSpeedWheel1());

		//new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.A).whileHeld(new ShooterAutoFire());
	}
}
