package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.OI;
import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.subsystems.Drive;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePercentCommand extends Command {
		@Override
		protected boolean isFinished() {
		return false;
		}
		
		public DrivePercentCommand() { 
			// Use requires() here to declare subsystem dependencies
			requires(Robot.drive);
		}

		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
			Robot.drive.RightDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
			Robot.drive.LeftDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			double leftSpeed = Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_LEFT_Y);
			double rightSpeed = Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_RIGHT_Y);
			Robot.drive.LeftDriveMaster.set(leftSpeed);
			Robot.drive.RightDriveMaster.set(rightSpeed);
			

			
		}

		// Called once after isFinished returns true
		@Override
		protected void end() {
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override
		protected void interrupted() {
		}
	}


