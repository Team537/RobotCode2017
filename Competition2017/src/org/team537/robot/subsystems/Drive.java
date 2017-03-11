package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.DriveDefault;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A robot subsystem that controls a "tank" drive system, providing control modes for 6 CAN Talons.
 */
public class Drive extends Subsystem implements PIDOutput {
	private final CANTalon driveLeft3 = new CANTalon(RobotMap.CAN.DRIVE_LEFT_MINI);
	private final CANTalon driveLeft2 = new CANTalon(RobotMap.CAN.DRIVE_LEFT_NORMAL);
	private final CANTalon driveLeft1 = new CANTalon(RobotMap.CAN.DRIVE_LEFT_MASTER);

	private final CANTalon driveRight3 = new CANTalon(RobotMap.CAN.DRIVE_RIGHT_MINI);
	private final CANTalon driveRight2 = new CANTalon(RobotMap.CAN.DRIVE_RIGHT_NORMAL);
	private final CANTalon driveRight1 = new CANTalon(RobotMap.CAN.DRIVE_RIGHT_MASTER);
	
//	private final PIDController anglePID = new PIDController(0.002, 0.0, 0.0, 0.0, Robot.ahrs, this);

	public Drive() {
		driveLeft3.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeft3.set(RobotMap.CAN.DRIVE_LEFT_MASTER);

		driveLeft2.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeft2.set(RobotMap.CAN.DRIVE_LEFT_MASTER);

		driveLeft1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		driveLeft1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		driveLeft1.configEncoderCodesPerRev(255);
		driveLeft1.setVoltageRampRate(9.0); // 0V to 9V in one second.
		driveLeft1.reverseOutput(false);
		driveLeft1.reverseSensor(false);
		driveLeft1.setPID(0.0, 0.0, 0.0);
		driveLeft1.setF(0.0);
		
		driveRight3.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRight3.set(RobotMap.CAN.DRIVE_RIGHT_MASTER);

		driveRight2.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRight2.set(RobotMap.CAN.DRIVE_RIGHT_MASTER);
		
		driveRight1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		driveRight1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		driveRight1.configEncoderCodesPerRev(255);
		driveRight1.setVoltageRampRate(9.0); // 0V to 9V in one second.
		driveRight1.reverseOutput(false);
		driveRight1.reverseSensor(false);
		driveRight1.setPID(0.0, 0.0, 0.0);
		driveRight1.setF(0.0);
		
		/*anglePID.setInputRange(-180.0, 180.0);
		anglePID.setOutputRange(-1.0, 1.0);
		anglePID.setAbsoluteTolerance(2.0);
		anglePID.setContinuous(true);
	    LiveWindow.addActuator("Drive", "Angle PID", anglePID);*/
	    
	    reset(); ////// TODO: REMOVE //////
	    
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
	//	if (RobotMap.Driver.ARCADE_DRIVE) {
	//		setDefaultCommand(new DriveArcade());
	//	} else {
			setDefaultCommand(new DriveDefault());
	//	}
		
		//setDefaultCommand(new DriveSpecial());
	}
	
	/**
	 * Sets the current Talon mode to a new mode.
	 * 
	 * @param mode The new mode to set to.
	 */
	public void setToMode(CANTalon.TalonControlMode mode) {
		// Makes sure the Talons are in the right mode.
		driveLeft1.changeControlMode(mode);
		driveRight1.changeControlMode(mode);
	}
	
	/**
	 * Sets the PID and F for the drive train.
	 * 
	 * @param pl Left P.
	 * @param il Left I.
	 * @param dl Left D.
	 * @param fl Left F.
	 * @param pr Right P.
	 * @param ir Right I.
	 * @param dr Right D.
	 * @param fr Right f.
	 */
	public void setPIDF(double pl, double il, double dl, double fl, double pr, double ir, double dr, double fr) {
		driveLeft1.setPID(pl, il, dl);
		driveLeft1.setF(fl);
		driveRight1.setPID(pr, ir, dr);
		driveRight1.setF(fr);
	}

	/**
	 * Drives the drive train from left and right speeds.
	 * 
	 * @param left The input left speed.
	 * @param right The input right speed.
	 */
	public void speed(double speedLeft, double speedRight) {
		// Sets the Talons to the drive at % speeds.
		driveLeft1.set(Maths.clamp(speedLeft * RobotMap.Robot.DRIVE_SPEED, -1.0, 1.0));
		driveRight1.set(Maths.clamp(-speedRight * RobotMap.Robot.DRIVE_SPEED, -1.0, 1.0));
	}

	/**
	 * Drives the drive train from left and right encoder rates.
	 * 
	 * @param left The input left encoder rate.
	 * @param right The input right encoder rate.
	 */
	public void rate(double left, double right) {
		// Sets the Talons to the drive at rates.
		driveLeft1.set(left);
		driveRight1.set(-right);
	}

	/**
	 * Drives the drive train to a left and right encoder distance.
	 * 
	 * @param left The input left distance (inches).
	 * @param right The input right distance (inches).
	 */
	public void distance(double left, double right) {
		// Sets the Talons to the drive distances.
		driveLeft1.set(left * RobotMap.Digital.DRIVE_IN_TO_ENCODER);
		driveRight1.set(-right * RobotMap.Digital.DRIVE_IN_TO_ENCODER);
	}

	/**
	 * Drives the drive train to a angle.
	 * 
	 * @param angle The angle to go to (degrees), this is a absolute angle (this + robotAngle = setpoint).
	 */
	/*public void angle(double angle) {
		if (!anglePID.isEnabled()) {
			anglePID.enable();
		}
		
		anglePID.setSetpoint(-angle);
		
		driveLeft1.set(anglePID.get());
		driveRight1.set(anglePID.get());
	}*/

	/**
	 * A method from PID Source used to output from the angle PID to the drive systems.
	 */
	@Override
	public void pidWrite(double output) {
		SmartDashboard.putNumber("Drive PID Output", output);

		double speedLeft = output * RobotMap.Robot.DRIVE_SPEED;
		double speedRight = output * RobotMap.Robot.DRIVE_SPEED;
		
		// (NEGATIVE -> LEFT), (POSITIVE -> RIGHT).
		driveLeft1.set(Math.max(speedLeft, 0.4));
		driveRight1.set(Math.max(speedRight, 0.4));
	}
	
	/**
	 * Gets if the target has been met.
	 * 
	 * @return If the target has been met.
	 */
	public boolean atTarget() {
		if (driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Position)) { // Distance.
			if (Maths.nearTarget(driveLeft1.getEncPosition(), driveLeft1.getSetpoint(), 0.07 * RobotMap.Digital.DRIVE_IN_TO_ENCODER)) {
				if (Maths.nearTarget(driveRight1.getEncPosition(), driveRight1.getSetpoint(), 0.07 * RobotMap.Digital.DRIVE_IN_TO_ENCODER)) {
					return true;
				}
			}
		}/* else if (anglePID.isEnabled()) { // Angle
			return anglePID.onTarget();
		}*/

		return false;
	}
	
	/**
	 * Resets the drive train, and sets the encoder positions to 0.
	 */
	public void reset() {
		driveLeft1.reset();
		driveLeft1.setPID(0.0, 0.0, 0.0);
		driveLeft1.setF(0.0);
		driveLeft1.enable();
		driveLeft1.setPosition(0.0);
		driveLeft1.set(0.0);
		
		driveRight1.reset();
		driveRight1.setPID(0.0, 0.0, 0.0);
		driveRight1.setF(0.0);
		driveRight1.enable();
		driveRight1.setPosition(0.0);
		driveRight1.set(0.0);

	//	anglePID.reset();
	}

	/**
	 * Stops the entire drive train, disables speeds and PIDs.
	 */
	public void stop() {
		driveLeft1.set(0.0);
		
		driveRight1.set(0.0);
		
	//	anglePID.disable();
	}

	private void dashboard() {
		SmartDashboard.putNumber("Drive Setpoint Left", driveLeft1.getSetpoint());
		SmartDashboard.putNumber("Drive Setpoint Right", driveRight1.getSetpoint());

		SmartDashboard.putNumber("Drive Error Left", driveLeft1.getError() * 4.0);
		SmartDashboard.putNumber("Drive Error Right", driveRight1.getError() * 4.0);

		SmartDashboard.putNumber("Drive Encoder Speed Left", driveLeft1.getEncVelocity());
		SmartDashboard.putNumber("Drive Encoder Speed Right", driveRight1.getEncVelocity());

		SmartDashboard.putNumber("Drive Encoder Pos Left", driveLeft1.getEncPosition());
		SmartDashboard.putNumber("Drive Encoder Pos Right", driveRight1.getEncPosition());

	//	SmartDashboard.putNumber("Drive PID Setpoint", anglePID.getSetpoint());
	//	SmartDashboard.putString("Drive PID", anglePID.getP() + ", " + anglePID.getI() + ", " + anglePID.getD());
	//	SmartDashboard.putNumber("Drive PID Error", anglePID.getError());
	//	SmartDashboard.putBoolean("Drive PID On Target", anglePID.onTarget());
	}
}

