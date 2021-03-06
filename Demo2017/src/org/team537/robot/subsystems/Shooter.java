package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.ShooterDefault;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	private final CANTalon shooterMaster = new CANTalon(RobotMap.CAN.SHOOTER_MASTER);
	private final CANTalon shooterSlave = new CANTalon(RobotMap.CAN.SHOOTER_SLAVE);

	public Shooter() {
		shooterSlave.changeControlMode(TalonControlMode.Follower);
		shooterSlave.set(RobotMap.CAN.SHOOTER_MASTER);
		shooterSlave.enableBrakeMode(false);
		shooterSlave.enable();

		shooterMaster.changeControlMode(TalonControlMode.Speed); 	
		shooterMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);	
		shooterMaster.enableBrakeMode(false);
		shooterMaster.configEncoderCodesPerRev(14);
		// shooter2.setPulseWidthPosition(4);
		shooterMaster.setPID(
				42.0, 
				0.0, 
				0.0
		);
		shooterMaster.setF(1023.0 / 590.0);
		shooterMaster.configPeakOutputVoltage(+12.0, 0.0);
		shooterMaster.enable();
		
		reset();
		
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
		setDefaultCommand(new ShooterDefault());
	}

	/**
	 * Drives the shooter from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void shoot(double rate) {
		// IF RATE IS NEGATIVE: THE ROBOT WILL KILL ITSELF. 
		shooterMaster.set(Math.abs(rate)); 
	}
	
	public void setBreakmode(boolean enabled) {
		shooterMaster.enableBrakeMode(enabled);
	}
	
	public double getSetpoint() {
		return shooterMaster.getSetpoint();
	}
	
	public boolean nearSpeed() {
		return getSetpoint() > 0 && Math.abs(shooterMaster.getError()) < RobotMap.Robot.SHOOTER_MAX_ERROR;
	}

	public void reset() {
		shooterMaster.set(0.0);
		shooterMaster.setPosition(0.0);
		shooterMaster.enable();

		shooterSlave.enable();
	}

	public void stop() {
		shooterMaster.set(0.0);
	}

	private void dashboard() {
	//	System.out.println("[Shooter] Error: " + shooterMaster.getError() + " Current: " + shooterMaster.getOutputCurrent());
		
		SmartDashboard.putNumber("Shooter Amps", shooterMaster.getOutputCurrent());
		SmartDashboard.putNumber("Shooter Speed", shooterMaster.getSpeed());
		SmartDashboard.putBoolean("Shooter Near Speed", nearSpeed());
		SmartDashboard.putNumber("Shooter Encoder Speed", shooterMaster.getEncVelocity()); // Native units.
		SmartDashboard.putNumber("Shooter Encoder Error", shooterMaster.getError());
		SmartDashboard.putNumber("Shooter Encoder Position", shooterMaster.getEncPosition());
	}
	
	
}
