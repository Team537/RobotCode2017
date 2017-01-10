package org.team537.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team537.robot.commands.ShooterShoot;

public class ShootCross extends CommandGroup {
    public ShootCross() {
        addSequential(new ShooterShoot(), 12);
    }
}
