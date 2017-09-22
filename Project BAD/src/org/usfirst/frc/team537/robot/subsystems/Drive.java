package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.commands.DrivePercentCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	private final CANTalon LeftDrive = new CANTalon(1);
	public CANTalon LeftDriveMaster = new CANTalon(2);
	private final CANTalon LeftDriveMini = new CANTalon(3);
	private final CANTalon RightDrive = new CANTalon(4);
	public CANTalon RightDriveMaster = new CANTalon(5);
	private final CANTalon RightDriveMini = new CANTalon(6);
	
	
	public Drive(){
		RightDrive.changeControlMode(CANTalon.TalonControlMode.Follower);
		RightDrive.set(RightDriveMaster.getDeviceID());
		RightDriveMini.changeControlMode(CANTalon.TalonControlMode.Follower);
		RightDriveMini.set(RightDriveMaster.getDeviceID());
		LeftDrive.changeControlMode(TalonControlMode.Follower);
		LeftDrive.set(LeftDriveMaster.getDeviceID());
		LeftDriveMini.changeControlMode(TalonControlMode.Follower);
		LeftDriveMini.set(LeftDriveMaster.getDeviceID());
		
		
	}
	
	

	
	protected void initDefaultCommand() {
		setDefaultCommand(new DrivePercentCommand());
	}

}
