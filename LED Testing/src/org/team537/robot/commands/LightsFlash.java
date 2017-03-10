package org.team537.robot.commands;

import org.team537.robot.subsystems.Lights.Colour;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LightsFlash extends CommandGroup {
	public LightsFlash(Command listener) {
		addSequential(new LightsColour(listener, Colour.GREEN), 1.0);
		addSequential(new LightsColour(listener, Colour.YELLOW), 1.0);
		addSequential(new LightsColour(listener, Colour.GREEN), 1.0);
		addSequential(new LightsColour(listener, Colour.YELLOW), 1.0);
	}
}
