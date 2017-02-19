package org.team537.robot.commands;

import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class DriveArcade extends Command {
	public DriveArcade() {
		requires(Robot.drive);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.setToMode(CANTalon.TalonControlMode.PercentVbus);
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		double axisY = -Robot.oi.joystickSecondary.getRawAxis(RobotMap.JoystickAxes.STICK_Y);
		double axisX = Robot.oi.joystickSecondary.getRawAxis(RobotMap.JoystickAxes.STICK_X);
		double left = axisY + axisX;
		double right = axisY - axisX;
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
