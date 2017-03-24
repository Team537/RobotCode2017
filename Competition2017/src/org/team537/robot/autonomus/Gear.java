package org.team537.robot.autonomus;

import org.team537.robot.commands.DriveRate;
import org.team537.robot.commands.ShooterAutomatic;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class Gear extends CommandGroup {

	public Gear() {
		addSequential(new DriveRate(100, 100, 5));
	}
}
