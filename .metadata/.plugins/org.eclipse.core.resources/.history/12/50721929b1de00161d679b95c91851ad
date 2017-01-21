package org.team537.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.DriveDefault;

public class LEDs extends Subsystem {
	private double r, g, b, a;

	public LEDs() {
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 100);

		// Creates initial colours.
		reset();
	}

	@Override
	protected void initDefaultCommand() {
	//	setDefaultCommand(new DriveDefault());
	}

	public void setColour(double r, double g, double b, double a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;

		// TODO: Update colours from RoboRIO.
	}

	public void reset() {
		// Initial charger colours.
		setColour(RobotMap.LEDs.CHARGER_R, RobotMap.LEDs.CHARGER_G, RobotMap.LEDs.CHARGER_B, 1.0);
	}

	public void dashboard() {
	//	SmartDashboard.putNumber("LEDs r", r);
	//	SmartDashboard.putNumber("LEDs g", g);
	//	SmartDashboard.putNumber("LEDs b", b);
	//	SmartDashboard.putNumber("LEDs a", a);
	}
}
