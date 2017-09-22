package org.team537.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team537.robot.commands.AgitatorAgitate;

public class AgitationAuto extends CommandGroup {
	public AgitationAuto(){
		addSequential(new AgitatorAgitate(false),1);
		addSequential(new AgitatorAgitate(true),0.2);
		
	}

}
