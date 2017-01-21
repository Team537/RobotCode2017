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
		shooter1.disable();
		shooter1.changeControlMode(TalonControlMode.PercentVbus);
		shooter1.enable();

		shooter2.disable();
		shooter2.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter2.changeControlMode(TalonControlMode.Speed);
		shooter2.configEncoderCodesPerRev(1024);
		shooter2.setPID(1.0, 0.0, 0.0);
		shooter2.enable();

		rateScalar = 0.615;
		enabledWheel1 = false;
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
		setDefaultCommand(new ShooterDefault());
	}

	public void shoot(double rate) {
		rateScalar = (-Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxesX3D.SLIDER) + 1.0) / 2.0;

		rate = Maths.roundToPlace(rateScalar, 3);
		SmartDashboard.putNumber("Shooter Setpoint", rate);
		
		if (enabledWheel1) {
			shooter1.set(rate * (wheelSpeed1 / 7.0));
		} else {
			shooter1.set(0.0);
		}
		
		if (enabledWheel2) {
			shooter2.set(-rate * 1024);
		} else {
			shooter2.set(0.0);
		}
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
		shooter1.setPosition(0.0);
		shooter1.set(0.0);
		shooter1.enable();

		shooter2.enable();
		shooter2.setPosition(0.0);
		shooter2.set(0.0);
	}

	public void stop() {
		shooter1.set(0.0);
		enabledWheel1 = false;
		
		shooter2.set(0.0);
		enabledWheel2 = false;
	}

	public void dashboard() {
		SmartDashboard.putBoolean("Shooter Wheel 1 Enabled", enabledWheel1);
		SmartDashboard.putBoolean("Shooter Wheel 2 Enabled", enabledWheel2);
		SmartDashboard.putNumber("Shooter Wheel Speed (7 Speed)", wheelSpeed1);
		SmartDashboard.putNumber("Shooter Rate Scalar", Maths.roundToPlace(rateScalar, 3));
		SmartDashboard.putNumber("Shooter Encoder Velocity", shooter2.getEncVelocity());
		SmartDashboard.putNumber("Shooter Encoder Position", shooter2.getEncPosition());
		SmartDashboard.putNumber("Shooter Voltage 1", shooter1.getBusVoltage());
		SmartDashboard.putNumber("Shooter Voltage 2", shooter2.getBusVoltage());
	}
}
