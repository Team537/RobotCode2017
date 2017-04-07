package org.team537.robot.autonomous;

import org.team537.robot.commands.DriveRate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTest extends CommandGroup {
	public AutoTest() {
		// super.addSequential(new DriveDistance(0.3f, 0.3f));
		addSequential(new DriveRate(200.0f, 200.0f, 4.0f));
	}
}
