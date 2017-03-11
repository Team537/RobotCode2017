package org.team537.robot.commands;

import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.subsystems.Lights;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterClimb extends Command {
	public ShooterClimb() {
		requires(Robot.shooter);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.shooter.reset();
		Robot.shooter.setBreakmode(true);
		Scheduler.getInstance().add(new LightsColour(this, Lights.Colour.MAGENTA));
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		double speed = Robot.oi.joystickSecondary.getRawAxis(RobotMap.JoystickAxes.SLIDER);
		
		if (speed < 0.0) {
			speed = 0.0;
		}
		
		SmartDashboard.putNumber("Climb Speed %", speed);
		Robot.shooter.shoot(4450.0 * speed);
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
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
