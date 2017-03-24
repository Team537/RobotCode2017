package org.team537.robot.commands;

import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.toolbox.Maths;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDefault extends Command {
	public DriveDefault() {
		requires(Robot.drive);
		this.setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		double left = Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.LEFT_Y);
		double right = Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.RIGHT_Y);
		left = ((1.0 - RobotMap.Driver.SENSITIVITY) * left) + (RobotMap.Driver.SENSITIVITY * Math.pow(left, 3.0));
		right = ((1.0 - RobotMap.Driver.SENSITIVITY) * right) + (RobotMap.Driver.SENSITIVITY * Math.pow(right, 3.0));
		left = Maths.deadband(RobotMap.Robot.DRIVE_SPEED_MIN, left);
		right = Maths.deadband(RobotMap.Robot.DRIVE_SPEED_MIN, right);
		Robot.drive.speed(left, right);
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
