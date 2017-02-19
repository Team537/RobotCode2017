package org.team537.robot.commands;

import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSpecial extends Command {
	public DriveSpecial() {
		requires(Robot.drive);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.setToMode(CANTalon.TalonControlMode.PercentVbus);
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		double x = Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_X);
		double y = -Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_Y);
		double t = Maths.normalizeAngle(Math.toDegrees(Math.atan2(y, x)));
		
		if (Robot.oi.joystickPrimary.getRawButton(RobotMap.JoystickKeys.INDEX_TRIGGER)) {
			Robot.drive.angle(t);
			System.out.println(x + ", " + y + " = " + t);
		}
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
