package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.ClimberDefault;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	private final CANTalon climber = new CANTalon(RobotMap.CAN.CLIMBER);

	public Climber() {
		climber.disable();
		climber.changeControlMode(TalonControlMode.PercentVbus);
		climber.enable();

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

	/**
	 * Drives the climber from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void climb(double rate) {
		SmartDashboard.putNumber("Climber Rate", rate);
		climber.set(rate);
	}

	public void reset() {
		climber.set(0.0);
		climber.enable();
	}

	public void stop() {
		climber.set(0.0);
	}

	private void dashboard() {
		SmartDashboard.putNumber("Climber Voltage", climber.getBusVoltage());
	}
}
