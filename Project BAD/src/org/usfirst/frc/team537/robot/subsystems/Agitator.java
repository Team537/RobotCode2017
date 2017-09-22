package org.usfirst.frc.team537.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Agitator extends Subsystem {
	public final CANTalon AgitatorTalon = new CANTalon(11);
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public Agitator() {
		AgitatorTalon.set(AgitatorTalon.getDeviceID());
	}

}
