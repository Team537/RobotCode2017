package org.team539.robot.commands;

import org.team539.robot.Robot;
import org.team539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveArcade extends Command {
	public DriveArcade() {
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
		double axisY = Robot.oi.joystickSecondary.getRawAxis(RobotMap.JoystickAxesX3D.STICK_Y);
		double axisX = Robot.oi.joystickSecondary.getRawAxis(RobotMap.JoystickAxesX3D.STICK_X);
		Robot.drive.speed(-(axisY + axisX), -(axisY - axisX));
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
