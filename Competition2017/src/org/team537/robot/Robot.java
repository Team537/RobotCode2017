package org.team537.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team537.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// Interfaces.
	public static OI oi;
	public static AHRS ahrs;
	public static Compressor compressor;
	public static UsbCamera camera;

	// Subsystems.
	public static GRIP grip;
	public static LEDs leds;
	public static Climber climber;
	public static Drive drive;
	public static Shooter shooter;

	// Autonomous.
	private SendableChooser<Command> autoChooser;
	private Command autoCommand;

	/**
	 * This function is for robot-wide initialization code.
	 */
	@Override
	public void robotInit() {
		// Interfaces.
		oi = new OI();

		try {
			ahrs = new AHRS(Port.kMXP);
		} catch (final RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);

		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(RobotMap.GRIP.IMAGE_WIDTH, RobotMap.GRIP.IMAGE_HEIGHT);

		// Subsystems.
		grip = new GRIP();
		leds = new LEDs();
		climber = new Climber();
		drive = new Drive();
		shooter = new Shooter();

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
