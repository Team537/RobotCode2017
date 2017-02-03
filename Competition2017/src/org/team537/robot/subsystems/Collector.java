package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.CollectorDefault;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends Subsystem {
	private final CANTalon collector1 = new CANTalon(RobotMap.CAN.COLLECTOR_1);

	public Collector() {
		collector1.disable();
		collector1.changeControlMode(TalonControlMode.PercentVbus);
		collector1.enable();

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

	public void collector(double rate) {
		SmartDashboard.putNumber("Collector Rate", rate);
		collector1.set(rate);
	}

	public void reset() {
		collector1.set(0.0);
		collector1.enable();
	}

	public void stop() {
		collector1.set(0.0);
	}

	public void dashboard() {
		SmartDashboard.putNumber("Collector Voltage", collector1.getBusVoltage());
	}
}
