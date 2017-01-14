package org.team537.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.team537.robot.Robot;
import org.team537.robot.RobotMap;
import org.team537.robot.subsystems.GRIP;
import org.team537.robot.toolbox.Maths;

import java.util.ArrayList;
import java.util.List;

public class ShooterShoot extends Command {
	private final Object imageLock;
	private VisionThread visionThread;

	private double centreX;
	private double centreY;
	private double distance;

	public ShooterShoot() {
		requires(Robot.shooter);
		requires(Robot.drive);

		this.imageLock = new Object();
		this.visionThread = new VisionThread(Robot.camera, Robot.grip, this::vision);
		this.visionThread.start();

		this.centreX = 0.0;
		this.centreY = 0.0;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.shooter.reset();
		Robot.drive.reset();
		Robot.leds.setColour(0.0, 1.0, 0.0, 1.0);
		// visionThread.start();
	}

	/**
	 * A function run from the vision thread every update.
	 *
	 * @param pipeline The GRIP pipeline to run from.
	 */
	private void vision(GRIP pipeline) {
		// Only update if the readings are used.
		if (!isRunning()) {
			return;
		}

		// Only update if there are more than two contours. (Two high boiler lines + extra possible random noise).
		if (pipeline.filterContoursOutput().size() >= 2) {
			// Creates rectangles from the contours and sorts from large area => small area.
			List<Rect> rectangles = new ArrayList<>();

			for (MatOfPoint p : pipeline.filterContoursOutput()) {
				rectangles.add(Imgproc.boundingRect(p));
			}

			rectangles = sortRectangles(rectangles);

			// The rectangles to look at.
			Rect r1 = rectangles.get(0);
			Rect r2 = rectangles.get(1);

			// The calculations to find the distance between both rectangles.
			double rd = Math.sqrt(Math.pow((r1.x - r2.x), 2) + Math.pow((r1.y - r2.y), 2));
			distance = (RobotMap.GRIP.PERCEIVED_FOCAL_LENGTH * RobotMap.GRIP.BOILER_HIGH_VISION) / rd;

			// The combined rectangle for both rectangles.
			double minX = Maths.minValue(r1.x, r2.x);
			double maxX = Maths.maxValue(r1.x, r2.x);
			double minY = Maths.minValue(r1.y, r2.y);
			double maxY = Maths.maxValue(r1.y, r2.y);
			Rect r = new Rect(new Point(minX, minY), new Point(maxX, maxY));

			// Finds the centre for the robot to point to.
			synchronized (imageLock) {
				centreX = r.x + (r.width / 2);
				centreY = r.y + (r.height / 2);
			}
		}
	}

	/**
	 * Sorts a list of rectangles using insertion sort, sorted by rectangle size.
	 *
	 * @param list The list of rectangles to sort by area.
	 * @return The sorted list.
	 */
	public List<Rect> sortRectangles(List<Rect> list) {
		for (int i = 1; i < list.size(); i++) {
			Rect toInsert = list.get(i);
			int j = i - 1;

			while (j >= 0 && (((Double) list.get(j).area()).compareTo(toInsert.area()) > 0)) {
				list.set(j + 1, list.get(j));
				j--;
			}

			list.set(j + 1, toInsert);
		}

		// TODO: Ensure the returned list has sorted areas big to small!
		return list;
	}

	/**
	 * The execute method is called repeatedly until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		double rate = 1.0; // TODO: Calculate needed velocity (M/S).
		double drive = 0.0; // TODO: Rotate to angle.
		Robot.shooter.shoot(rate);
		Robot.drive.drive(drive, -drive);
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.drive.stop();
		Robot.leds.reset();
		// visionThread.stop();
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
	}
}