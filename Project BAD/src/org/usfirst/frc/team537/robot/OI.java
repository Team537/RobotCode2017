package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.AgitatorPercentCommand;
import org.usfirst.frc.team537.robot.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	public final Joystick joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);
	
	Button agitatorUpButton = new JoystickButton(joystickSecondary, RobotMap.Boxkeys.SECOND_SWITCH_UP);
	
	public OI() {
		agitatorUpButton.whileHeld(new AgitatorPercentCommand());
	}
}
