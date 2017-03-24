package org.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.AgitatorDefault;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Agitator extends Subsystem {
	private final CANTalon agitator = new CANTalon(RobotMap.CAN.AGITATOR);

	public Agitator() {
		agitator.changeControlMode(TalonControlMode.PercentVbus);
		agitator.enable();
		
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
		setDefaultCommand(new AgitatorDefault());
	}

	/**
	 * Drives the agitator from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void agitate(double rate) {
		SmartDashboard.putNumber("Agitator Rate", rate);
		agitator.set(-rate * RobotMap.Robot.AGITATOR_SPEED);
	}

	public void reset() {
		agitator.set(0.0);
		agitator.enable();
	}

	public void stop() {
		agitator.set(0.0);
	}

	private void dashboard() {
	}
}
