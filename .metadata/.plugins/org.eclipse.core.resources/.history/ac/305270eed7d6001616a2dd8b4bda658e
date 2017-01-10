package org.team537.robot.commands;

import org.team537.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command written for the swerve subsystem, rotates the angle to tune the POTs for 10 seconds, and then tunes the PIDs.
 */
public class SwerveTune extends Command {
	private static final double LENGTH_TUNE_POT = 15.0;
	private static final double LENGTH_TUNE_PID = 45.0;
	
	private Timer timer;
	private double lastTime;
	private double angle;
	private boolean tunedPOTs, tunedPIDs;
	
	public SwerveTune() {
		requires(Robot.drive);
		this.timer = new Timer();
		this.angle = 0.0;
		this.tunedPOTs = false;
		this.tunedPIDs = false;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.reset();
		this.timer.reset();
		this.timer.start();
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		if (timer.get() < LENGTH_TUNE_POT) {
			double delta = timer.get() - lastTime;
			this.lastTime = timer.get();
			angle += delta * 72.0; // 1 rotation per 5 second cycles.
			Robot.drive.drive(0.0, Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle)), 0.0);
		} else if (timer.get() < LENGTH_TUNE_POT + LENGTH_TUNE_PID) {
			angle = 0.0;
			// TODO: PID autotune.
		}
		
		tunedPOTs = timer.get() >= LENGTH_TUNE_POT;
		tunedPIDs = timer.get() >= LENGTH_TUNE_POT + LENGTH_TUNE_PID;

		SmartDashboard.putBoolean("Swerve Tuned POTs", tunedPOTs);
		SmartDashboard.putBoolean("Swerve Tuned PIDs", tunedPIDs);
		SmartDashboard.putNumber("Swerve Tune Angle", angle);
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
	}
}
