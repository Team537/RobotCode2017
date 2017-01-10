package org.team537.robot.subsystems;

import edu.wpi.first.wpilibj.command.*;
import org.team537.robot.commands.*;

/**
 * A example subsystem.
 */
public class SubsystemExample extends Subsystem {
	/**
	 * Creates a new example subsystem.
	 */
	public SubsystemExample() {

	}

	/**
	 * Initializes the default command for this subsystem.
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandExample());
	}
}
