package org.team537.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.subsystems.SubsystemExample;
import org.team537.robot.toolbox.Maths;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. 
 * If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot {
	// Interfaces.
	public static AHRS ahrs;
	private Compressor compressor;

	// Subsystems.
	public static SubsystemExample example;

	// OI.
	public static OI oi;
	
	// Autonomous.
	private SendableChooser<Command> autoChooser;
	private Command autoCommand;

	/**
	 * This function is for robot-wide initialization code.
	 */
	@Override
	public void robotInit() {
		// Interfaces.
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (final RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}
		
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
	            SmartDashboard.putNumber("NavX Angle", Maths.roundToPlace(ahrs.getAngle(), 3));
	            SmartDashboard.putNumber("NavX Angle Pitch", Maths.roundToPlace(ahrs.getPitch(), 3));
	            SmartDashboard.putNumber("NavX Angle Yaw", Maths.roundToPlace(ahrs.getYaw(), 3));
	            SmartDashboard.putNumber("NavX Angle Roll", Maths.roundToPlace(ahrs.getRoll(), 3));
	            SmartDashboard.putNumber("NavX Velocity X", Maths.roundToPlace(ahrs.getVelocityX(), 3));
	            SmartDashboard.putNumber("NavX Velocity Y", Maths.roundToPlace(ahrs.getVelocityY(), 3));
	            SmartDashboard.putNumber("NavX Velocity Z", Maths.roundToPlace(ahrs.getVelocityZ(), 3));
			}
		}, 0, 100);

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);

		// Subsystems.
		example = new SubsystemExample();

		// OI.
		oi = new OI();

		// Autonomous chooser to display on the dashboard.
		autoChooser = new SendableChooser<>();
		autoChooser.addObject("Nothing", null);
		SmartDashboard.putData("Autonomous", autoChooser);
	}

	/**
	 * This function is called when the disabled button is hit.
	 */
	@Override
	public void disabledInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	/**
	 * Periodic code for disabled mode should go here.
	 */
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called at the start of autonomous period.
	 */
	@Override
	public void autonomousInit() {
		// Schedules the autonomous command.
		autoCommand = autoChooser.getSelected();

		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called at the start of operator control period.
	 */
	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when teleop starts running.
		if (autoCommand != null) {
			autoCommand.cancel();
			autoCommand = null;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
