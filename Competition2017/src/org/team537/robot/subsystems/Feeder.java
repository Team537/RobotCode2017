package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.FeederDefault;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Feeder extends Subsystem {
	private final CANTalon feeder = new CANTalon(RobotMap.CAN.FEEDER);

	public Feeder() {
		feeder.disable();
		feeder.changeControlMode(TalonControlMode.PercentVbus);
		feeder.enable();

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
		setDefaultCommand(new FeederDefault());
	}

	/**
	 * Drives the climber from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void feed(double rate) {
		SmartDashboard.putNumber("Feeder Rate", rate);
		feeder.set(rate * RobotMap.Robot.FEEDER_SPEED);
	}

	public void reset() {
		feeder.set(0.0);
		feeder.enable();
	}

	public void stop() {
		feeder.set(0.0);
	}

	private void dashboard() {
		SmartDashboard.putNumber("Feeder Speed", feeder.getSpeed());
		SmartDashboard.putNumber("Feeder Encoder Speed", feeder.getEncVelocity()); // Native units.
		SmartDashboard.putNumber("Feeder Encoder Error", feeder.getError() * 4.0f);
		SmartDashboard.putNumber("Feeder Encoder Position", feeder.getEncPosition());

		SmartDashboard.putNumber("Feeder Voltage", feeder.getBusVoltage());
		SmartDashboard.putNumber("Feeder Setpoint", feeder.getSetpoint());
	}
}
