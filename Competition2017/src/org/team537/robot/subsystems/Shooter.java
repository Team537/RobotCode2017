package org.team537.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team537.robot.RobotMap;
import org.team537.robot.commands.ShooterDefault;

import java.util.Timer;
import java.util.TimerTask;

public class Shooter extends Subsystem {
	private final CANTalon shooter = new CANTalon(RobotMap.CAN.CLIMBER_SIM);

	public Shooter() {
		shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.enable();

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
		setDefaultCommand(new ShooterDefault());
	}

	public void shoot(double rate) {
		shooter.set(rate); // TODO: Convert M/S => Encoder Ticks / Second.
	}

	public void reset() {
		shooter.enable();
		shooter.setPosition(0.0);
		shooter.set(0.0);
	}

	public void stop() {
		shooter.set(0.0);
	}

	public void dashboard() {
		SmartDashboard.putNumber("Climber Speed", shooter.get());
	}
}
