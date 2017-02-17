package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.CollectorDefault;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends Subsystem {
	private final CANTalon collector = new CANTalon(RobotMap.CAN.COLLECTOR);

	public Collector() {
		collector.changeControlMode(TalonControlMode.PercentVbus);
		collector.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		collector.enableBrakeMode(false);
		collector.configEncoderCodesPerRev(98);
		// shooter2.setPulseWidthPosition(4);
		collector.setPID(
				0.0, 
				0.0, 
				0.0
		);
		collector.setF(1.0); // 1023.0 / 610.0
		collector.configPeakOutputVoltage(+12.0, 0.0);
		collector.enable();

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
		setDefaultCommand(new CollectorDefault());
	}

	/**
	 * Drives the collector from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void collect(double rate) {
		collector.set(-rate * RobotMap.Robot.COLLECT_SPEED);
	}

	public void reset() {
		collector.set(0.0);
		collector.enable();
	}

	public void stop() {
		collector.set(0.0);
	}

	private void dashboard() {
		SmartDashboard.putNumber("Collector Speed", collector.getSpeed());
		SmartDashboard.putNumber("Collector Encoder Speed", collector.getEncVelocity()); // Native units.
		SmartDashboard.putNumber("Collector Encoder Error", collector.getError() * 4.0f);
		SmartDashboard.putNumber("Collector Encoder Position", collector.getEncPosition());

		SmartDashboard.putNumber("Collector Voltage", collector.getBusVoltage());
		SmartDashboard.putNumber("Collector Setpoint", collector.getSetpoint());
	}
}
