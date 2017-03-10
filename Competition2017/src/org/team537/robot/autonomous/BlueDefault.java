package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveAngle;
import org.team537.robot.commands.DriveDistance;
import org.team537.robot.commands.DriveRate;
import org.team537.robot.commands.ShooterAutomatic;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueDefault extends CommandGroup {
	public BlueDefault() {
		// What worked at mini regional:
		//addSequential(new DriveRate(200.0, 200.0, 3.5));
		//addSequential(new DriveRate(100.0, -100.0, 1.0));
		//addSequential(new DriveRate(70.0, 70.0, 2.6));

		addSequential(new ShooterAutomatic());
	}
}
