package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.OI;
import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.subsystems.Agitator;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class AgitatorPercentCommand extends Command {
	public AgitatorPercentCommand(){
		requires(Robot.agitator);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	protected void initialize(){
			Robot.agitator.AgitatorTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	protected void execute(){
			Robot.agitator.AgitatorTalon.set(0.5);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.agitator.AgitatorTalon.set(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.agitator.AgitatorTalon.set(0.0);
	}
}
