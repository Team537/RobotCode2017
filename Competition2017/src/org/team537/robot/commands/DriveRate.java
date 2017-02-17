package org.team537.robot.commands;

import org.team537.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveRate extends Command {
	private Timer timer;
	private double rateLeft;
	private double rateRight;
	private double time;
	
	public DriveRate(double rateLeft, double rateRight, double time) {
		requires(Robot.drive);
		setInterruptible(true);
		this.timer = new Timer();
		this.rateLeft = rateLeft;
		this.rateRight = rateRight;
		this.time = time;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.setToMode(CANTalon.TalonControlMode.Speed);
		Robot.drive.setPIDF(
				0.0, 0.0, 0.0, 1023.0 / 640.0,
				0.0, 0.0, 0.0, 1023.0 / 640.0
		);
		timer.reset();
		timer.start();
		Robot.drive.rate(rateLeft, rateRight);
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
		return timer.get() > time;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		timer.stop();
		Robot.drive.stop();
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		super.end();
	}
}
