package org.team537.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private final CANTalon talon = new CANTalon(1);

	private Timer timer;
	private boolean currentDrawLimit;
	private boolean currentTimeout;
	
	@Override
	public void robotInit() {
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.setVoltageRampRate(10.0); // 0V to 10V in one second.
		talon.enable();
		
		timer = new Timer();
		currentDrawLimit = false;
		currentTimeout = false;
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		double currentTime = System.currentTimeMillis() / 1000.0;
		double setpoint = 0.32 * Math.abs(Math.cos(currentTime * 2.0));
		if (talon.getOutputCurrent() > 4.0 && !currentTimeout) {
			if (!currentDrawLimit) {
				currentDrawLimit = true;
				timer.start();
			}
			setpoint = timer.get() / 10.0;
			
			if (timer.get() > 4) {
				setpoint = 0.0;
				currentDrawLimit = true;
				currentTimeout = true;
			}
		} else {
			if (currentTimeout) {
				if (timer.get() > 7.0) {
					currentTimeout = false;
					currentDrawLimit = false;
					timer.reset();
					timer.stop();
				}
			} else if (currentDrawLimit) {
				currentDrawLimit = false;
				timer.reset();
				timer.stop();
			}
		}
		talon.set(setpoint);
		SmartDashboard.putNumber("Boiler Current", talon.getOutputCurrent());
		SmartDashboard.putNumber("Boiler Setpoint", talon.getSetpoint());
		SmartDashboard.putBoolean("Boiler currentDrawLimit", currentDrawLimit);
		SmartDashboard.putBoolean("Boiler currentTimeout", currentTimeout);
		SmartDashboard.putNumber("Boiler timer", timer.get());
	}

	@Override
	public void testPeriodic() {
	}
}

