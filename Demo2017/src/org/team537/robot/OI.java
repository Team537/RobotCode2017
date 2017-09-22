package org.team537.robot;

import org.team537.robot.commands.AgitationAuto;
import org.team537.robot.commands.AgitatorAgitate;
import org.team537.robot.commands.CollectorIntake;
import org.team537.robot.commands.FeederFeed;
import org.team537.robot.commands.LightsColour;
import org.team537.robot.commands.LightsCycle;
import org.team537.robot.subsystems.Lights;
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
	public final Joystick magicBox = new Joystick(RobotMap.Driver.TRINARY_PORT);

	public OI() {
		new JoystickButton(magicBox, RobotMap.ControlBox.COLLECTOR_IN).whileHeld(new CollectorIntake(true));
		new JoystickButton(magicBox, RobotMap.ControlBox.COLLECTOR_OUT).whileHeld(new CollectorIntake(false));
		
		new JoystickButton(magicBox, RobotMap.ControlBox.AGRIGATOR_IN).whileHeld(new AgitatorAgitate(false));
		new JoystickButton(magicBox, RobotMap.ControlBox.AGRIGATOR_OUT).whileHeld(new AgitationAuto());
		new JoystickButton(magicBox, RobotMap.ControlBox.FLYWHEEL_TOGGLE).whileHeld(new ShooterShoot());
		new JoystickButton(magicBox, RobotMap.ControlBox.FIRE_BUTTON).whileHeld(new FeederFeed());
		
		new JoystickButton(magicBox, RobotMap.ControlBox.CLIMB_TOGGLE).whileHeld(new ShooterClimb());
		
		new JoystickButton(joystickPrimary, RobotMap.JoystickKeys.A_BUTTON).whileHeld(new LightsCycle(null, 5, Lights.Colour.WHITE, Lights.Colour.BLUE, Lights.Colour.YELLOW, Lights.Colour.GREEN, Lights.Colour.RED, Lights.Colour.MAGENTA));
	}
}
