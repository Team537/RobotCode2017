package org.team539.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

public class RangeFinder extends Thread {
	private I2C lidar;
	private double range;

	public RangeFinder() {
		this.lidar = new I2C(I2C.Port.kOnboard, 0x30);
		this.range = 0;
		this.start();
	}

	public double currentRange() {
		synchronized (this) {
			return range;
		}
	}

	@Override
	public void run() {
		byte[] buffer = new byte[3];
		System.out.println("RoboRio-537 lidar init");
		int timer = 0;
		boolean found = false;

		while (!found) {
			lidar.write(0x60, 0x01);
			lidar.read(0x61, 3, buffer);
			found = ((byte) 0xa1 == buffer[0]);
			System.out.println(String.format("Rangefinder found %s val 0x%2x", (found ? "true" : "false"), buffer[0]));
			timer++;
			
			if (found || 100 < timer)
				break;
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		while (found) {
			lidar.read(0x61, 3, buffer);

			int rmm = (buffer[0] << 8) + buffer[1];
			double rin = rmm * 0.0393701;
			
			synchronized (this) {
				range = rin;
			}
			
			if (0 == timer % 20) {
				timer = 0;
				System.out.println(String.format("-- range -- %10.5f", rin));
			}
			
			timer++;
			
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

