package org.team537.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team537.robot.RobotMap;
import org.team537.robot.commands.DriveDefault;
import org.team537.robot.toolbox.Maths;

import java.util.Timer;
import java.util.TimerTask;

public class Drive extends Subsystem {
	private final CANTalon driveLeft1 = new CANTalon(0);
	private final CANTalon driveLeft2 = new CANTalon(0);

	private final CANTalon driveRight1 = new CANTalon(0);
	private final CANTalon driveRight2 = new CANTalon(0);

	public Drive() {
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 1000);

		driveLeft1.changeControlMode(CANTalon.TalonControlMode.Speed);
		driveLeft1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		driveLeft2.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeft2.set(0);

		driveRight1.changeControlMode(CANTalon.TalonControlMode.Speed);
		driveRight1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		driveRight2.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRight2.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveDefault());
	}

	public void drive(double left, double right) {
		// Logs drive inputs.
		SmartDashboard.putNumber("Drive Input Left", left);
		SmartDashboard.putNumber("Drive Input Right", right);

		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Speed)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.Speed);
		}

		if (!driveRight2.getControlMode().equals(CANTalon.TalonControlMode.Speed)) {
			driveRight2.changeControlMode(CANTalon.TalonControlMode.Speed);
		}

		// Sets the Talons to the drive speeds.
		driveLeft1.set(Maths.normalizeAngle(Maths.deadband(RobotMap.Robot.DRIVE_SPEED, left)));
		driveRight1.set(Maths.normalizeAngle(Maths.deadband(RobotMap.Robot.DRIVE_SPEED, right)));
	}

	public void distance(double left, double right) {
		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Position)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.Position);
		}

		if (!driveRight2.getControlMode().equals(CANTalon.TalonControlMode.Position)) {
			driveRight2.changeControlMode(CANTalon.TalonControlMode.Position);
		}

		// Sets the Talons to the drive distances.
		driveLeft1.set(left);
		driveRight1.set(right);
	}

	public void angle(double angle) {
		// Makes sure the Talons are in the right mode.
		if (!driveLeft1.getControlMode().equals(CANTalon.TalonControlMode.Speed)) {
			driveLeft1.changeControlMode(CANTalon.TalonControlMode.Speed);
		}

		if (!driveRight2.getControlMode().equals(CANTalon.TalonControlMode.Speed)) {
			driveRight2.changeControlMode(CANTalon.TalonControlMode.Speed);
		}

		// TODO: Rotate!
	}

	public void dashboard() {
	}

	public void reset() {
		driveLeft1.reset();
		driveLeft2.reset();
	}

	public void stop() {
		driveLeft1.set(0);
		driveLeft2.set(0);
	}
}
