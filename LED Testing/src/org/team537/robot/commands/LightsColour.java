package org.team537.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.Robot;
import org.team537.robot.subsystems.Lights.Colour;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LightsColour extends Command {
	protected static final String Colours = null;
	private Command listener;
	private Colour colour;

	public LightsColour(Command listener, Colour colour) {
		requires(Robot.lights);
		setInterruptible(true);
		this.listener = listener;
		this.colour = colour;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.lights.set(colour);
	}
	
	private int i = 0;
	private boolean down = false;

	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		if (Robot.joy.getRawButton(1)) {
			if (!down) {
			i--;
			if (i < 0) {
				i = 0;
			}
			}
			Robot.lights.set(Colour.values()[i]);
			down = true;
		} else if (Robot.joy.getRawButton(2)) {
			if (!down) {
			i++;
			if (i > Colour.values().length - 1) {
				i = Colour.values().length - 1;
			}
			Robot.lights.set(Colour.values()[i]);
			}
			down = true;
		} else {
			down = false;
		}
		
		SmartDashboard.putString("color", Colour.values()[i].name());
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return (listener != null) ? !listener.isRunning() : false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.lights.set(Colour.getDefault());
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
	}
}
