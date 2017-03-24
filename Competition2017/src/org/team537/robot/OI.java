package org.team537.robot;

import org.team537.robot.commands.AgitatorAgitate;
import org.team537.robot.commands.CollectorIntake;
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

	public OI() {
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.COLLECTOR_IN).whileHeld(new CollectorIntake(true));
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.COLLECTOR_OUT).whileHeld(new CollectorIntake(false));
		
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.AGRIGATOR_IN).whileHeld(new AgitatorAgitate(true));
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.AGRIGATOR_OUT).whileHeld(new AgitatorAgitate(false));
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.FLYWHEEL_TOGGLE).whileHeld(new ShooterShoot());
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.FIRE_BUTTON).whileHeld(new FeederFeed());
		
		new JoystickButton(joystickSecondary, RobotMap.ControlBox.CLIMB_TOGGLE).whileHeld(new ShooterClimb());
		
		
	}
}
