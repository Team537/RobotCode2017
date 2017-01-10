package org.team537.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.team537.robot.Robot;

public class ShooterShoot extends Command {
    private final Object imageLock;
    private VisionThread visionThread;

    private double centreX;
    private double centreY;

    public ShooterShoot() {
        requires(Robot.shooter);
        requires(Robot.drive);

        this.imageLock = new Object();
        this.visionThread = new VisionThread(Robot.camera, Robot.grip, pipeline -> {
            if (!pipeline.filterContoursOutput().isEmpty()) {
                Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));

                synchronized (imageLock) {
                    centreX = r.x + (r.width / 2);
                    centreY = r.y + (r.height / 2);
                }
            }
        });
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
    //    visionThread.start();
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
    //    visionThread.stop();
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    @Override
    protected void interrupted() {
    }
}
