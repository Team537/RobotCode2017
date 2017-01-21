package org.team537.robot.commands;

import org.team537.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterAutoFire extends Command {
	private Timer timer;
	
	public ShooterAutoFire() {
		requires(Robot.shooter);
		this.timer = new Timer();
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.shooter.reset();
		Robot.shooter.setEnabledWheel1(false);
		Robot.shooter.setEnabledWheel2(true);
		timer.reset();
		timer.start();
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is canceled.
	 */
	@Override
	protected void execute() {
		Robot.shooter.setEnabledWheel2(true);
		
		if (timer.get() > 0.2) {
			Robot.shooter.setEnabledWheel1(true);
			
			if (timer.get() > 0.4) {
				timer.reset();
				timer.start();
			}
		} else {
			Robot.shooter.setEnabledWheel1(false);
		}
		
		Robot.shooter.shoot(1.0);
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
		Robot.shooter.stop();
		timer.reset();
		timer.stop();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		Robot.shooter.stop();
	}
}
