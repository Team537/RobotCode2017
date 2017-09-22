package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveRate;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class StraightGear extends CommandGroup {

	public StraightGear() {
		addSequential(new DriveRate(100, 100, 4.25));
		addSequential(new DriveRate(-100, -100, 1.0));
	}
}
