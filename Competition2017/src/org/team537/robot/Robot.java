package org.team537.robot;

import java.util.Timer;
import java.util.TimerTask;

import org.team537.robot.autonomous.BlueBoiler;
import org.team537.robot.autonomous.NewBlueBoiler;
import org.team537.robot.autonomous.BlueRight;
import org.team537.robot.autonomous.RedBoiler;
import org.team537.robot.autonomous.NewRedBoiler;
import org.team537.robot.autonomous.RedLeft;
import org.team537.robot.subsystems.Agitator;
import org.team537.robot.subsystems.Collector;
import org.team537.robot.subsystems.Drive;
import org.team537.robot.subsystems.Feeder;
import org.team537.robot.subsystems.Lights;
import org.team537.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Winning code!

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. 
 * If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot {
	// Interfaces.
	//public static AHRS ahrs;
	public static UsbCamera camera;
	//public static MjpegServer mjpegServer;

	// Subsystems.
	public static Agitator agitator;
	public static Collector collector;
	public static Drive drive;
	public static Feeder feeder;
	// public static GRIP grip;
	// public static Lidar lidar;
	public static Lights lights;
	public static Shooter shooter;

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
			//ahrs = new AHRS(Port.kMXP);
		} catch (final RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}

		camera = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		camera.setResolution(RobotMap.GRIP.IMAGE_WIDTH, RobotMap.GRIP.IMAGE_HEIGHT);
		

		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				//if (ahrs != null) {
					//SmartDashboard.putNumber("NavX Angle", Maths.roundToPlace(ahrs.getAngle(), 3));
					//SmartDashboard.putNumber("NavX Angle Pitch", Maths.roundToPlace((double) ahrs.getPitch(), 3));
					//SmartDashboard.putNumber("NavX Angle Yaw", Maths.roundToPlace((double) ahrs.getYaw(), 3));
					//SmartDashboard.putNumber("NavX Angle Roll", Maths.roundToPlace((double) ahrs.getRoll(), 3));
					//SmartDashboard.putNumber("NavX Velocity X", Maths.roundToPlace((double) ahrs.getVelocityX(), 3));
					//SmartDashboard.putNumber("NavX Velocity Y", Maths.roundToPlace((double) ahrs.getVelocityY(), 3));
					//SmartDashboard.putNumber("NavX Velocity Z", Maths.roundToPlace((double) ahrs.getVelocityZ(), 3));
				//}

				if (camera != null) {
					SmartDashboard.putBoolean("Camera Conected", Robot.camera.isConnected());
				} else {
					SmartDashboard.putBoolean("Camera Conected", false);
				}
			}
		}, 0, 100);

		// Subsystems.
		agitator = new Agitator();
		collector = new Collector();
		drive = new Drive();
		feeder = new Feeder();
		// grip = new GRIP();
		// lidar = new Lidar();
		lights = new Lights();
		shooter = new Shooter();

		// OI.
		oi = new OI();

		// Autonomous chooser to display on the dashboard.
		autoChooser = new SendableChooser<>();
		
		Alliance alliance = null;
		
		if (DriverStation.getInstance() != null) {
			alliance = DriverStation.getInstance().getAlliance();
		}

		switch (alliance) {
			case Red:
				autoChooser.addObject("Nothing", null);
				autoChooser.addObject("Blue Boiler", new NewBlueBoiler());
				autoChooser.addObject("Blue Right", new BlueRight());
				autoChooser.addObject("Red Boiler", new NewRedBoiler());
				autoChooser.addDefault("Red Left", new RedLeft());
				autoChooser.addObject("Old Blue Boiler", new BlueBoiler());
				autoChooser.addObject("Old Red Boiler", new RedBoiler());
				break;
			case Blue:
				autoChooser.addObject("Nothing", null);
				autoChooser.addObject("Blue Boiler", new NewBlueBoiler());
				autoChooser.addDefault("Blue Right", new BlueRight());
				autoChooser.addObject("Red Boiler", new NewRedBoiler());
				autoChooser.addObject("Red Left", new RedLeft());
				autoChooser.addObject("Old Blue Boiler", new BlueBoiler());
				autoChooser.addObject("Old Red Boiler", new RedBoiler());
				break;
			default:
				autoChooser.addObject("Nothing", null); // addDefault
				autoChooser.addObject("Blue Boiler", new NewBlueBoiler());
				autoChooser.addObject("Blue Right", new BlueRight());
				autoChooser.addObject("Red Boiler", new NewRedBoiler());
				autoChooser.addDefault("Red Left", new RedLeft());
				autoChooser.addObject("Old Blue Boiler", new BlueBoiler());
				autoChooser.addObject("Old Red Boiler", new RedBoiler());
				break;
		}
		
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

		//ahrs.reset();
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

		//ahrs.reset();
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
		// This makes sure that the autonomous stops running when teleop starts
		// running.
		if (autoCommand != null) {
			autoCommand.cancel();
			autoCommand = null;
		}

		//ahrs.reset();
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

