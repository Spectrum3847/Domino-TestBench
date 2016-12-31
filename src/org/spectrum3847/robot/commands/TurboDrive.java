package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurboDrive extends Command{

	public TurboDrive(){
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.drive.turboDrive(-1*HW.Driver_Gamepad.getLeftY(), HW.Driver_Gamepad.getRightX(), SmartDashboard.getNumber("Drive Deadband"), SmartDashboard.getBoolean("Squared Drive Inputs"));
		System.out.println("HELLO, I AM THE TURBODRIVE COMMAND. I AM RECEIVING GAMEPAD INPUTS.");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
