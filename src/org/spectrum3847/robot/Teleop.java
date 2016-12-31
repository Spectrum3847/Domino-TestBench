package org.spectrum3847.robot;

import org.spectrum3847.robot.commands.TurboDrive;
import org.spectrum3847.robot.Robot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Scheduler;


/**
 * The Driver Control period of the competition
 */
public class Teleop {
	
    public static void init() {
        Scheduler.getInstance().removeAll();
        
        TurboDrive turboDrive = new TurboDrive();
        turboDrive.start();
        
        //Robot.compressor.start();
        //Robot.compressor.setClosedLoopControl(true);
        
        Robot.logger.openFile();
    }

    public static void periodic() {
    	Dashboard.updateDashboard();
        Scheduler.getInstance().run();
        //Robot.compressor.stop();
        
        //Tank Drive
        //Robot.drive.setOpenLoop(new DriveSignal(HW.Driver_Gamepad.getLeftY(), HW.Driver_Gamepad.getRightY()));
        
        Robot.logger.logAll();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
