package org.spectrum3847.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.spectrum3847.robot.HW;

/*
 * @author matthew, JAG
 */
public class Dashboard {
	
	public static final boolean ENABLE_DASHBOARD = true;
	
	
	static final double SHORT_DELAY = .015;
    static final double LONG_DELAY = 1;
    
    static double shortOldTime = 0.0;
    static double longOldTime = 0.0;


    public static void intializeDashboard() {
    	if(ENABLE_DASHBOARD){
    		//SmartDashboard.putBoolean("Relay", false);
    		//SmartDashboard.putNumber("Motor 1", 0);
        	//Robot.shooter.putCompleteOnDashboard();
    		SmartDashboard.putNumber("Drive Deadband", .1);
    		SmartDashboard.putBoolean("Squared Drive Inputs", false);
    		
    		//Battery/Current monitoring
    		SmartDashboard.putNumber("Battery Voltage", HW.PDP.getVoltage());
    		SmartDashboard.putNumber("Total Current Draw", HW.PDP.getTotalCurrent());
    		
    		//Current to each motor
    		SmartDashboard.putNumber("Left First CIM Current", HW.PDP.getCurrent(HW.LEFT_DRIVE_MOTOR_1_PDP));
    		SmartDashboard.putNumber("Left Second CIM Current", HW.PDP.getCurrent(HW.LEFT_DRIVE_MOTOR_2_PDP));
    		SmartDashboard.putNumber("Left 775 Current", HW.PDP.getCurrent(HW.LEFT_DRIVE_MOTOR_3_PDP));
    		SmartDashboard.putNumber("Left Fourth CIM Current", HW.PDP.getCurrent(HW.LEFT_DRIVE_MOTOR_4_PDP));
    		
    		SmartDashboard.putNumber("Right First CIM Current", HW.PDP.getCurrent(HW.RIGHT_DRIVE_MOTOR_1_PDP));
    		SmartDashboard.putNumber("Right Second CIM Current", HW.PDP.getCurrent(HW.RIGHT_DRIVE_MOTOR_2_PDP));
    		SmartDashboard.putNumber("Right 775 Current", HW.PDP.getCurrent(HW.RIGHT_DRIVE_MOTOR_3_PDP));
    		SmartDashboard.putNumber("Right Fourth CIM Current", HW.PDP.getCurrent(HW.RIGHT_DRIVE_MOTOR_4_PDP));
    	}
    }

    private static void updatePutShort() {
    	//SmartDashboard.putNumber("Motor 1", Motor_1.get());
    	//Robot.shooter.updateValuesToDashboard();
    }
    
    private static void updatePutLong(){
    	//SmartDashboard.putBoolean("Compressor", Compressor.enabled());
    }

    
    public static void updateDashboard() {
    	if (ENABLE_DASHBOARD) {
            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
                updatePutShort();
            }
            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                longOldTime = Timer.getFPGATimestamp();
                updatePutLong();
            }
        }
    }
}
