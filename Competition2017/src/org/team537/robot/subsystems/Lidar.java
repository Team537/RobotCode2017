package org.team537.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A robot subsystem that reads ranges from a I2C lidar module.
 */
public class Lidar extends Subsystem {
	private I2C lidar;
	private double range;
	
	private Thread thread;

	public Lidar() {
		this.lidar = new I2C(I2C.Port.kOnboard, 0x30);
		this.range = 0.0;
		
		this.thread = new Thread(this::threadRun);
		this.thread.start();
	}

	@Override
	protected void initDefaultCommand() {
	}

	private void threadRun() {
		byte[] buffer = new byte[3];
		System.out.println("RoboRio-537 lidar init");
		boolean found = false;

		while (!found) {
			lidar.write(0x60, 0x01);
			lidar.read(0x61, 3, buffer);
			found = ((byte) 0xa1 == buffer[0]);
			System.out.println(String.format("Rangefinder found %s val 0x%2x", (found ? "true" : "false"), buffer[0]));

			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		while (found) {
			lidar.read(0x61, 3, buffer);

			int rmm = (buffer[0] << 8) + buffer[1];
			double metres = rmm * 1000.0;
			
			synchronized (this) {
				range = metres;
				SmartDashboard.putNumber("Lidar Range (Metres)", range);
			}
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets the currently read range in metres.
	 * 
	 * @return The current range in metres.
	 */
	public double getRange() {
		synchronized (this) {
			return range;
		}
	}
}
