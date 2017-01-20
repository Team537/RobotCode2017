package org.team537.robot.autonomous;

import org.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueDefault extends CommandGroup {
	public BlueDefault() {
		addSequential(new ShooterShoot(), 12);
	}
}
