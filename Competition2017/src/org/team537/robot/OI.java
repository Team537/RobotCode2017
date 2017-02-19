package org.team537.robot;

import org.team537.robot.commands.AgitatorAgitate;
import org.team537.robot.commands.CollectorIntake;
import org.team537.robot.commands.DriveArcade;
import org.team537.robot.commands.FeederFeed;
import org.team537.robot.commands.ShooterClimb;
import org.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	public final Joystick joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);
	public final Joystick joystickTertiary = new Joystick(RobotMap.Driver.TERTIARY_PORT);

	public OI() {
		// new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.X).whileHeld(new ClimberAction(true));
		// new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.Y).whileHeld(new ClimberAction(false));
		// new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.A).whileHeld(new ShooterShoot());

		new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.INDEX_TRIGGER).whileHeld(new DriveArcade());
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.THUMB_TRIGGER).whileHeld(new DriveAngle(90.0f));
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.THUMB_TRIGGER).whenPressed(new DriveAngle(180.0f, true));
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.INDEX_TRIGGER).whileHeld(new DriveRange(60.0f));
	//		new JoystickButton(joystickSecondary, RobotMap.JoystickKeysX3D.THUMB_TRIGGER).whenPressed(new DriveDistance(2.0, 2.0));
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.BASE_8).whenPressed(new DriveRate(200.0, 200.0, 3.0));
		//	new JoystickButton(joystickSecondary, RobotMap.JoystickKeys.BASE_7).whenPressed(new DriveSpeed(0.2, -0.2, 4.20));

		new JoystickButton(joystickTertiary, RobotMap.ControlBox.COLLECTOR_IN).whileHeld(new CollectorIntake(true));
		new JoystickButton(joystickTertiary, RobotMap.ControlBox.COLLECTOR_OUT).whileHeld(new CollectorIntake(false));
		
		new JoystickButton(joystickTertiary, RobotMap.ControlBox.AGRIGATOR_IN).whileHeld(new AgitatorAgitate(true));
		new JoystickButton(joystickTertiary, RobotMap.ControlBox.AGRIGATOR_OUT).whileHeld(new AgitatorAgitate(false));
		new JoystickButton(joystickTertiary, RobotMap.ControlBox.FLYWHEEL_TOGGLE).whileHeld(new ShooterShoot());
		new JoystickButton(joystickTertiary, RobotMap.ControlBox.FIRE_BUTTON).whileHeld(new FeederFeed());
		
		new JoystickButton(joystickTertiary, RobotMap.ControlBox.CLIMB_TOGGLE).whileHeld(new ShooterClimb());
	}
}
