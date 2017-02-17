package org.team537.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team537.robot.commands.CommandExample;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A example subsystem.
 */
public class SubsystemExample extends Subsystem {
	/**
	 * Creates a new example subsystem.
	 */
	public SubsystemExample() {
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 100);
	}

	/**
	 * Initializes the default command for this subsystem.
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandExample());
	}

	public void dashboard() {
	}
}
