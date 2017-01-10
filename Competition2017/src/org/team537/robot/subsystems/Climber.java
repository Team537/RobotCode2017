package org.team537.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team537.robot.RobotMap;
import org.team537.robot.commands.ClimberDefault;

import java.util.Timer;
import java.util.TimerTask;

public class Climber extends Subsystem {
	private final CANTalon climber = new CANTalon(RobotMap.CAN.CLIMBER_SIM);

	public Climber() {
		climber.changeControlMode(TalonControlMode.PercentVbus);
		climber.enable();

		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 1000);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberDefault());
	}

	public void climb(double speed) {
		climber.set(RobotMap.Robot.CLIMB_SPEED * speed * 12.0);
	}

	public void reset() {
		climber.enable();
		climber.set(0.0);
	}

	public void stop() {
		climber.set(0.0);
	}

	public void dashboard() {
		SmartDashboard.putNumber("Climber Speed", climber.get());
	}
}
