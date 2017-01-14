package org.team537.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team537.robot.RobotMap;
import org.team537.robot.commands.DriveDefault;

import java.util.Timer;
import java.util.TimerTask;

public class LEDs extends Subsystem {
	private double r, g, b, a;

	public LEDs() {
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 1000);

		// Creates initial colours.
		reset();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveDefault());
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
		SmartDashboard.getNumber("LEDs r", r);
		SmartDashboard.getNumber("LEDs g", g);
		SmartDashboard.getNumber("LEDs b", b);
		SmartDashboard.getNumber("LEDs a", a);
	}
}
