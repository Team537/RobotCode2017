package org.team537.robot.subsystems;

import org.team537.robot.RobotMap;
import org.team537.robot.commands.LightsColour;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lights extends Subsystem {
	private final Relay relay1 = new Relay(RobotMap.Analog.LIGHT_SPIKE_1); // +blue -common
	private final Relay relay2 = new Relay(RobotMap.Analog.LIGHT_SPIKE_2); // +red -green
	
	public Lights() {
		
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LightsColour(null, Colour.OFF));
	}
	
	public void set(Colour colour) {
		relay1.set(colour.value1);
		relay2.set(colour.value2);
	}

	public enum Colour {
		OFF(Relay.Value.kOn, Relay.Value.kOn), 
		WHITE(Relay.Value.kReverse, Relay.Value.kOff), 
		RED(Relay.Value.kOn, Relay.Value.kReverse),
		BLUE(Relay.Value.kReverse, Relay.Value.kOn), 
		GREEN(Relay.Value.kOn, Relay.Value.kForward), 
		YELLOW(Relay.Value.kOn, Relay.Value.kOff), 
		CYAN(Relay.Value.kReverse, Relay.Value.kForward), 
		MAGENTA(Relay.Value.kReverse, Relay.Value.kReverse);
		
		private final Relay.Value value1;
		private final Relay.Value value2;
		
		Colour(Relay.Value value1, Relay.Value value2) {
			this.value1 = value1;
			this.value2 = value2;
		}

		public Relay.Value getValue1() {
			return value1;
		}

		public Relay.Value getValue2() {
			return value2;
		}
		
		public static Colour getDefault() {
			switch (DriverStation.getInstance().getAlliance()) {
				case Red:
					return RED;
				case Blue:
					return BLUE;
				default:
					return WHITE;
			}
		}
	}
}
