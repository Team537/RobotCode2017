package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.commands.ShooterDefault;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author matthew
 *
 */
public class Shooter extends Subsystem {
	private final CANTalon shooter1 = new CANTalon(RobotMap.CAN.SHOOTER_1);
	private final CANTalon shooter2 = new CANTalon(RobotMap.CAN.SHOOTER_2);

	private double rateScalar;
	private boolean enabledWheel1;
	private boolean enabledWheel2;
	private int wheelSpeed1;

	public Shooter() {
		shooter1.changeControlMode(TalonControlMode.PercentVbus);
		shooter1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter1.reverseSensor(true);
		shooter1.configEncoderCodesPerRev(80);
		
		shooter1.configNominalOutputVoltage(+0.0f, -0.0f);
		shooter1.configPeakOutputVoltage(+12.0f, -0.0f);
		
		shooter1.setProfile(0);
		shooter1.setF(0.0);
		shooter1.setP(0.0);
		shooter1.setI(0.0);
		shooter1.setD(0.0);

		shooter2.changeControlMode(TalonControlMode.Follower);
		shooter2.set(shooter1.getDeviceID());

		// shooter2.disable();
		// shooter2.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		// shooter2.changeControlMode(TalonControlMode.Speed);
		// shooter2.configEncoderCodesPerRev(1024);
		// shooter2.setPulseWidthPosition(4);
		// shooter2.setPID(1.0, 0.0, 0.0);
		// shooter2.setF(1.0f);
		// shooter2.enable();

		rateScalar = 0.615;
		enabledWheel1 = true;
		enabledWheel2 = false;
		wheelSpeed1 = 4;

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
		// setDefaultCommand(new ShooterDefault());
	}

	public void shoot(double rate) {
		// if
		// (Robot.oi.joystickPrimary.getRawButton(RobotMap.JoystickKeys.START))
		// {
		// rateScalar = 0.8;
		// } else {
		// rateScalar = 0.0;
		// }
		rateScalar = Maths.deadband(0.05, Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.RIGHT_Y)); // (-
																												// wwww
																												// +
																												// 1.0)
																												// /
																												// 2.0

		rate = Maths.roundToPlace(rateScalar, 3);
		SmartDashboard.putNumber("Shooter Setpoint", rate);

		/*
		 * if (enabledWheel1) { shooter1.set(rate * (wheelSpeed1 / 7.0)); } else
		 * { shooter1.set(0.0); }
		 * 
		 * if (enabledWheel2) { // shooter2.set((-rate * 5700.0f) / 7.2f); // *
		 * 1024 shooter2.set(64); SmartDashboard.putNumber("Shooter Testing",
		 * -rate * (5700.0f / 7.2f)); } else { shooter2.set(0.0); }
		 */

		this.shooter1.enable();
		this.shooter2.enable();

		this.shooter1.set(0.75);
	}

	public double getRateScalar() {
		return rateScalar;
	}

	public void setRateScalar(double rateScalar) {
		this.rateScalar = rateScalar;
	}

	public boolean isEnabledWheel1() {
		return enabledWheel1;
	}

	public void setEnabledWheel1(boolean enabledWheel1) {
		this.enabledWheel1 = enabledWheel1;
	}

	public boolean isEnabledWheel2() {
		return enabledWheel2;
	}

	public void setEnabledWheel2(boolean enabledWheel2) {
		this.enabledWheel2 = enabledWheel2;
	}

	public int getWheelSpeed1() {
		return wheelSpeed1;
	}

	public void setWheelSpeed1(int wheelSpeed1) {
		this.wheelSpeed1 = wheelSpeed1;
	}

	public void reset() {
		shooter1.set(0.0);
	}

	public void stop() {
		shooter1.set(0.0);
	}

	public void dashboard() {
		SmartDashboard.putBoolean("Shooter Wheel 1 Enabled", enabledWheel1);
		SmartDashboard.putBoolean("Shooter Wheel 2 Enabled", enabledWheel2);
		SmartDashboard.putNumber("Shooter Wheel Speed (7 Speed)", wheelSpeed1);
		SmartDashboard.putNumber("Shooter Rate Scalar", Maths.roundToPlace(rateScalar, 3));
		SmartDashboard.putNumber("Shooter speed error", shooter1.getError());
		SmartDashboard.putNumber("Shooter Encoder Velocity", shooter1.getSpeed()); // (72.0f
																							// /
																							// 10.0f)
		SmartDashboard.putNumber("Shooter Encoder Position", shooter1.getPosition());
		SmartDashboard.putNumber("Shooter Voltage 1", shooter1.getBusVoltage());
		SmartDashboard.putNumber("Shooter Voltage 2", shooter2.getBusVoltage());
	}
}
