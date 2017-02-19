package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveRate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueDefault extends CommandGroup {
	public BlueDefault() {
		addSequential(new DriveRate(200.0, 200.0, 3.5));
		addSequential(new DriveRate(100.0, -100.0, 1.0));
		addSequential(new DriveRate(70.0, 70.0, 2.6));
	}
}
