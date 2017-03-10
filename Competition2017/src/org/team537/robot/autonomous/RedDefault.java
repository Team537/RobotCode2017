package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveAngle;
import org.team537.robot.commands.DriveDistance;
import org.team537.robot.commands.DriveRate;
import org.team537.robot.commands.ShooterAutomatic;
import org.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedDefault extends CommandGroup {
	public RedDefault() {
		addSequential(new ShooterAutomatic());
	//	addSequential(new DriveAngle(30.0, true));
	//	addSequential(new DriveDistance(2.2, 2.2));
	//	addSequential(new DriveAngle(90.0, true));
	//	addSequential(new DriveRate(200.0, 200.0, 1.2));
	}
}
