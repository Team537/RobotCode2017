package org.team537.robot.commands;

import org.team537.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveAngle extends Command {
	private Timer timer;
	private double angle;
	private boolean addToNavX;
	
	public DriveAngle(double angle, boolean addToNavX) {
		requires(Robot.drive);
		setInterruptible(false);
		this.timer = new Timer();
		this.angle = angle;
		this.addToNavX = addToNavX;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.reset();
		Robot.drive.setToMode(CANTalon.TalonControlMode.PercentVbus);

		if (addToNavX) {
			Robot.ahrs.reset();
		}
		
		timer.reset();
		timer.start();
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
		return Robot.drive.atTarget() || timer.get() > 2.0;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
		timer.reset();
		timer.stop();
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
