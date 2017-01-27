package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.commands.ClimberDefault;
import org.team537.robot.commands.ShooterDefault;
import org.team537.robot.toolbox.Maths;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	private final CANTalon climber1 = new CANTalon(RobotMap.CAN.CLIMBER_1);

	public Climber() {
		climber1.disable();
		climber1.changeControlMode(TalonControlMode.PercentVbus);
		climber1.enable();

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
		setDefaultCommand(new ClimberDefault());
	}

	public void climb(double rate) {
		SmartDashboard.putNumber("Climber Rate", rate);
		climber1.set(rate);
	}

	public void reset() {
		climber1.setPosition(0.0);
		climber1.set(0.0);
		climber1.enable();
	}

	public void stop() {
		climber1.set(0.0);
	}

	public void dashboard() {
		SmartDashboard.putNumber("Climber Encoder Velocity", climber1.getEncVelocity());
		SmartDashboard.putNumber("Climber Encoder Position", climber1.getEncPosition());
		SmartDashboard.putNumber("Climber Voltage 1", climber1.getBusVoltage());
	}
}