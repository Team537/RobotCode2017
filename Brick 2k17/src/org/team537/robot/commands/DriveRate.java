package org.team537.robot.commands;

import org.team537.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveRate extends Command {
	private double rateLeft;
	private double rateRight;
	
	public DriveRate(double rateLeft, double rateRight, double time) {
		requires(Robot.drive);
		setInterruptible(true);
		setTimeout(time);
		this.rateLeft = rateLeft;
		this.rateRight = rateRight;
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
		Robot.ahrs.reset();
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
        double gyroError = Robot.ahrs.getYaw();
        double leftSpeed = rateLeft;
        double rightSpeed = rateRight;
        
        if (Math.abs(gyroError) >= 3.0) {
	        if (gyroError > 0.0)  {
	        	leftSpeed += 10*gyroError;// * (1.0f / 180.0) * 0.01;
	        	rightSpeed -= 10*gyroError;
	        }
	
	        if (gyroError < 0.0) {
	        	rightSpeed += gyroError;// * (1.0f / 180.0) * 0.01;
	        	leftSpeed -= gyroError;
	        }
        }
        
		Robot.drive.rate(leftSpeed, rightSpeed);
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
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
		super.end();
	}
}
