package org.team537.robot;

import org.team537.robot.commands.CollectorIntake;
import org.team537.robot.commands.CollectorSquarewave;
import org.team537.robot.commands.DriveArcade;
import org.team537.robot.commands.ShooterAutoFire;
import org.team537.robot.commands.ShooterClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	public final Joystick joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);

	public OI() {
		// new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.X).whileHeld(new ClimberAction(true));
		// new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.Y).whileHeld(new ClimberAction(false));
		// new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.A).whileHeld(new ShooterShoot());

		new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.INDEX_TRIGGER).whileHeld(new DriveArcade());
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.THUMB_TRIGGER).whileHeld(new DriveAngle(90.0f));
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.THUMB_TRIGGER).whenPressed(new DriveAngle(180.0f, true));
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.INDEX_TRIGGER).whileHeld(new DriveRange(60.0f));
	//		new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.THUMB_TRIGGER).whenPressed(new DriveDistance(2.0, 2.0));
	//		new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.STICK_5).whenPressed(new DriveRate(200.0, 200.0, 3.0));
	//		new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.STICK_3).whenPressed(new DriveSpeed(0.2, -0.2, 4.20));

		new JoystickButton(joystickPrimary, RobotMap.JoystickKeysX3D.INDEX_TRIGGER).whileHeld(new ShooterAutoFire());
		new JoystickButton(joystickPrimary, RobotMap.JoystickKeysX3D.BASE_10).whileHeld(new ShooterClimb());

		new JoystickButton(joystickPrimary, RobotMap.JoystickKeysX3D.STICK_6).whileHeld(new CollectorIntake(false));
		new JoystickButton(joystickPrimary, RobotMap.JoystickKeysX3D.STICK_4).whileHeld(new CollectorIntake(true));
		new JoystickButton(joystickPrimary, RobotMap.JoystickKeysX3D.STICK_3).whileHeld(new CollectorSquarewave());
	}
}
