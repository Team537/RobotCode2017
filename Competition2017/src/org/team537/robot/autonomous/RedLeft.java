package org.team537.robot.autonomous;

import org.team537.robot.commands.CollectorIntake;
import org.team537.robot.commands.DriveRate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedLeft extends CommandGroup {
	public RedLeft() {
		// Forward 15feet, Turn left 90, Forward 5ft+ (6), Collector on
		addSequential(new DriveRate(200.0, 200.0, 4.2));
		addSequential(new DriveRate(-120.0, 120.0, 1.1));
		addSequential(new DriveRate(330.0, 330.0, 1.2));
		addSequential(new CollectorIntake(true), 5.0);
	}
}
