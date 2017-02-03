package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.ShooterDefault;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	private final CANTalon shooter1 = new CANTalon(RobotMap.CAN.SHOOTER_1);
	private final CANTalon shooter2 = new CANTalon(RobotMap.CAN.SHOOTER_2);

	public Shooter() {
		shooter1.disable();
		shooter1.changeControlMode(TalonControlMode.PercentVbus);
		shooter1.enable();

		shooter2.disable();
		shooter2.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter2.changeControlMode(TalonControlMode.Speed);
		shooter2.configEncoderCodesPerRev(20);
		// shooter2.setPulseWidthPosition(4);
		shooter2.setPID(
				(0.12f * 1023.0f) / (70.0f * (1.0f / 60.0f) * (1.0f / 10.0f) * 80.0f), 
				0.0, 
				(1.20f * 1023.0f) / (70.0f * (1.0f / 60.0f) * (1.0f / 10.0f) * 80.0f)
		);
		shooter2.setF(1023.0f / (5670.0f * (1.0f / 60.0f) * (1.0f / 10.0f) * 80.0f));
		shooter2.configPeakOutputVoltage(+12.0, 0.0);
		shooter2.enable();

		SmartDashboard.putNumber("Shooter F", Maths.roundToPlace(shooter2.getF(), 4));
		SmartDashboard.putNumber("Shooter P", Maths.roundToPlace(shooter2.getP(), 4));
		SmartDashboard.putNumber("Shooter D", Maths.roundToPlace(shooter2.getD(), 4));

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

	public void shoot(double rate) {
		SmartDashboard.putNumber("Shooter Setpoint", rate);
		
		shooter1.set(rate * (4.0 / 7.0));
		shooter2.set(rate);
	}

	public void reset() {
		shooter1.set(0.0);
		shooter1.enable();

		shooter2.set(0.0);
		shooter2.enable();
	}

	public void stop() {
		shooter1.set(0.0);
		
		shooter2.set(0.0);
	}

	public void dashboard() {
		SmartDashboard.putNumber("Shooter Encoder Speed", shooter2.getSpeed());
		SmartDashboard.putNumber("Shooter Encoder Error", shooter2.getError() * 4.0f);
		SmartDashboard.putNumber("Shooter Encoder Position", shooter2.getEncPosition());
		SmartDashboard.putNumber("Shooter Voltage 1", shooter1.getBusVoltage());
		SmartDashboard.putNumber("Shooter Voltage 2", shooter2.getBusVoltage());
	}
}
