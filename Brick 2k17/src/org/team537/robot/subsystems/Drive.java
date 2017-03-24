package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.DriveArcade;
import org.team537.robot.commands.DriveDefault;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem implements PIDOutput {
	private final CANTalon driveLeft1 = new CANTalon(RobotMap.CAN.DRIVE_LEFT_1);
	private final CANTalon driveLeft2 = new CANTalon(RobotMap.CAN.DRIVE_LEFT_2);
	private final CANTalon driveLeft3 = new CANTalon(RobotMap.CAN.DRIVE_LEFT_3);

	private final CANTalon driveRight1 = new CANTalon(RobotMap.CAN.DRIVE_RIGHT_1);
	private final CANTalon driveRight2 = new CANTalon(RobotMap.CAN.DRIVE_RIGHT_2);
	private final CANTalon driveRight3 = new CANTalon(RobotMap.CAN.DRIVE_RIGHT_3);
	
	private final Solenoid shifter = new Solenoid(RobotMap.Solenoid.DRIVE_SHIFTER);
	
	public Drive() {
		driveLeft1.changeControlMode(CANTalon.TalonControlMode.Speed);
	//	driveLeft1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		driveLeft2.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeft2.set(RobotMap.CAN.DRIVE_LEFT_1);
		
		driveLeft3.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeft3.set(RobotMap.CAN.DRIVE_LEFT_1);

		driveRight1.changeControlMode(CANTalon.TalonControlMode.Speed);
	//	driveRight1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		driveRight2.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRight2.set(RobotMap.CAN.DRIVE_RIGHT_1);
		
		driveRight3.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRight3.set(RobotMap.CAN.DRIVE_RIGHT_1);
		
		shifter.set(false);
		
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 100);
	}

	@Override
	protected void initDefaultCommand() {
		if (RobotMap.Driver.ARCADE_DRIVE) {
			setDefaultCommand(new DriveArcade());
		} else {
			setDefaultCommand(new DriveDefault());
		}
	}

	/**
	 * Drives the drive train from left and right speeds.
	 * 
	 * @param left The input left speed.
	 * @param right The input right speed.
	 */
	public void speed(double speedLeft, double speedRight) {
		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.PercentVbus)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		}

		if (!driveRight1.getControlMode().equals(CANTalon.TalonControlMode.PercentVbus)) {
			driveRight1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		}

		SmartDashboard.putNumber("Drive Left", speedLeft);
		SmartDashboard.putNumber("Drive Right", speedRight);

		// Sets the Talons to the drive at % speeds.
		driveLeft1.set(Maths.clamp(speedLeft * RobotMap.Robot.DRIVE_SPEED, -1.0, 1.0));
		driveRight1.set(-Maths.clamp(speedRight * RobotMap.Robot.DRIVE_SPEED, -1.0, 1.0));
	//	anglePID.disable();
	}

	/**
	 * Drives the drive train from left and right encoder rates.
	 * 
	 * @param left The input left encoder rate.
	 * @param right The input right encoder rate.
	 */
	public void driveRate(final double left, final double right) {
		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Speed)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.Speed);
		}

		if (!driveRight1.getControlMode().equals(CANTalon.TalonControlMode.Speed)) {
			driveRight1.changeControlMode(CANTalon.TalonControlMode.Speed);
		}

		// Sets the Talons to the drive at rates.
		driveLeft1.set(left);
		driveRight2.set(-right);
	}

	/**
	 * Drives the drive train to a left and right encoder distance.
	 * 
	 * @param left The input left distance (inches).
	 * @param right The input right distance (inches).
	 */
	public void distance(double left, double right) {
		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Position)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.Position);
		}

		if (!driveRight1.getControlMode().equals(CANTalon.TalonControlMode.Position)) {
			driveRight1.changeControlMode(CANTalon.TalonControlMode.Position);
		}

		// Sets the Talons to the drive distances.
		driveLeft1.set(left * RobotMap.Digital.DRIVE_IN_TO_ENCODER);
		driveRight1.set(-right * RobotMap.Digital.DRIVE_IN_TO_ENCODER);
	}

	/**
	 * Drives the drive train to a angle.
	 * 
	 * @param angle The angle to go to (degrees), this is a absolute angle (this + robotAngle = setpoint).
	 */
	public void angle(double angle) {
		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.PercentVbus)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		}

		if (!driveRight1.getControlMode().equals(CANTalon.TalonControlMode.PercentVbus)) {
			driveRight1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		}
	}


	@Override
	public void pidWrite(double output) {
		// (NEGATIVE -> LEFT), (POSITIVE -> RIGHT).
		driveLeft1.set(output);
		driveRight1.set(-output);
	}
	
	/**
	 * Gets if the target has been met.
	 * 
	 * @return If the target has been met.
	 */
	public boolean atTarget() {
		if (driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Position)) { // Distance.
			if (Maths.nearTarget(driveLeft1.getEncPosition(), driveLeft1.getSetpoint(), 3.0 * RobotMap.Digital.DRIVE_IN_TO_ENCODER)) {
				if (Maths.nearTarget(driveRight1.getEncPosition(), driveRight1.getSetpoint(), 3.0 * RobotMap.Digital.DRIVE_IN_TO_ENCODER)) {
					return true;
				}
			}
		}

		return false;
	}
	
	public void reset() {
		driveLeft1.reset();
		driveLeft1.setPosition(0);
		
		driveRight1.reset();
		driveRight1.setPosition(0);
	}

	public void stop() {
		driveLeft1.set(0);
		
		driveRight1.set(0);
	}

	public void dashboard() {
		SmartDashboard.putNumber("Drive Setpoint Left", driveLeft1.getSetpoint());
		SmartDashboard.putNumber("Drive Setpoint Right", driveRight1.getSetpoint());

		SmartDashboard.putNumber("Drive Error Left", driveLeft1.getClosedLoopError());
		SmartDashboard.putNumber("Drive Error Right", driveRight1.getClosedLoopError());

		SmartDashboard.putNumber("Drive Speed Left", driveLeft1.getSpeed());
		SmartDashboard.putNumber("Drive Speed Right", driveRight1.getSpeed());

		SmartDashboard.putNumber("Drive Encoder Pos Left", driveLeft1.getEncPosition());
		SmartDashboard.putNumber("Drive Encoder Pos Right", driveRight1.getEncPosition());
	}
}

