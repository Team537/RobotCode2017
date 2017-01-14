package org.team537.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team537.robot.Robot;

public class ClimberAction extends Command {
	private boolean climbingUp;

	public ClimberAction(boolean climbingUp) {
		requires(Robot.climber);
		this.climbingUp = climbingUp;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.climber.reset();
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		Robot.climber.climb((climbingUp ? 1.0 : -1.0));
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
		Robot.climber.stop();
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
	}
}
