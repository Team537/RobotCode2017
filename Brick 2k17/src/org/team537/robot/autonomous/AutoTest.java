package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveRate;
import org.team537.robot.commands.DriveAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTest extends CommandGroup {
	public AutoTest() {
		// super.addSequential(new DriveDistance(0.3f, 0.3f));
		addSequential(new DriveAngle(90.0f));
	}
}
