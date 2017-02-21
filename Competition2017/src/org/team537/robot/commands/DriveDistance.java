package org.team537.robot.commands;

import org.team537.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	private double distanceLeft;
	private double distanceRight;
	
	public DriveDistance(double distanceLeft, double distanceRight) {
		requires(Robot.drive);
		setInterruptible(false);
		this.distanceLeft = distanceLeft;
		this.distanceRight = distanceRight;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.setToMode(CANTalon.TalonControlMode.Position);
		Robot.drive.setPIDF(
				0.085, 0.0, 0.0, 0.0,
				0.085, 0.0, 0.0, 0.0
		);
		Robot.drive.reset();
		Robot.drive.distance(distanceLeft, distanceRight);
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return Robot.drive.atTarget();
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
