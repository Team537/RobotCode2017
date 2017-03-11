package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveRate;
import org.team537.robot.commands.ShooterAutomatic;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueDefault extends CommandGroup {
	public BlueDefault() {
		addSequential(new ShooterAutomatic());
		addSequential(new DriveRate(-120.0, -120.0, 1.0));
		addSequential(new DriveRate(120.0, -120.0, 1.2));
		addSequential(new DriveRate(200.0, 200.0, 4.15));
		addSequential(new DriveRate(-120.0, 120.0, 1.1));
		addSequential(new DriveRate(315.0, 315.0, 1.7));
	}
}
