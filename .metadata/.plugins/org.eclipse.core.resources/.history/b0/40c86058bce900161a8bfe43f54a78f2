package org.team537.robot.commands;

import org.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterAddSpeedWheel1 extends Command {
	private boolean done;

	public ShooterAddSpeedWheel1() {
		requires(Robot.shooter);
		this.done = false;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		this.done = false;
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is canceled.
	 */
	@Override
	protected void execute() {
		if (!done) {
			int wheelSpeed1 = Robot.shooter.getWheelSpeed1() + 1;

			if (wheelSpeed1 > 7) {
				wheelSpeed1 = 0;
			}

			Robot.shooter.setWheelSpeed1(wheelSpeed1);
			done = true;
		}
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return done;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
	}
}
