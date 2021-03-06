
package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.Gamepad;
import org.spectrum3847.lib.drivers.SpectrumEncoder;
import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Logger;
import org.spectrum3847.robot.commands.CANManualControl;
import org.spectrum3847.robot.subsystems.Drive;
import org.spectrum3847.robot.subsystems.MotorWithLimits;
import org.spectrum3847.robot.subsystems.SolenoidSubsystem;
import org.spectrum3847.robot.subsystems.SpeedCANSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//logger
	public static Logger logger;
	
    //Add Debug flags
	//You can have a flag for each subsystem, etc
	public static final String output = "OUT";
	public static final String input = "IN";
	public static final String controls = "CONTROL";
	public static final String general = "GENERAL";
	public static final String auton = "AUTON";
	public static final String commands = "COMMAND";
	
	// Create a single static instance of all of your subsystems
    // This MUST be here. If the OI creates Commands (which it very likely
    // will), constructing it during the construction of CommandBase (from
    // which commands extend), subsystems are not guaranteed to be
    // yet. Thus, their requires() statements may grab null pointers. Bad
    // news. Don't move it.
	
	public static Drive drive;
	public static SpectrumSpeedController leftDriveCIMs;
	public static SpectrumSpeedController rightDriveCIMs;
	public static SpectrumSpeedController leftDrive775;
	public static SpectrumSpeedController rightDrive775;
	
	public static Compressor compressor;
	
    public static void setupSubsystems(){
    	//compressor = new Compressor(0);

    	
    	//MAKE A DRIVETRAIN
    	Talon leftFront_CIMs = new Talon(HW.LEFT_FRONT_DRIVE_MOTOR_PAIR);
    	Talon left_775 = new Talon(HW.LEFT_DRIVE_MOTOR_3);
    	Talon leftBack_CIM = new Talon(HW.LEFT_DRIVE_MOTOR_4);
    	
    	Talon rightFront_CIMs = new Talon(HW.RIGHT_FRONT_DRIVE_MOTOR_PAIR);
    	Talon right_775 = new Talon(HW.RIGHT_DRIVE_MOTOR_3);
    	Talon rightBack_CIM = new Talon(HW.RIGHT_DRIVE_MOTOR_4);
    	
    	//Note that in the XXDriveCIMs objects, b/c there are two motors on the 1st PWM, there are three PDP slots but only two speed controllers 
    	
    	
    	leftDriveCIMs = new SpectrumSpeedController(
    						new SpeedController[] {leftFront_CIMs, leftBack_CIM},
    						new int[] {HW.LEFT_DRIVE_MOTOR_1_PDP, HW.LEFT_DRIVE_MOTOR_2_PDP, 
    								HW.LEFT_DRIVE_MOTOR_4_PDP});
    	leftDrive775 = new SpectrumSpeedController(
    						new SpeedController[] {left_775},
    						new int[] {HW.LEFT_DRIVE_MOTOR_3_PDP});
    	
    	rightDriveCIMs = new SpectrumSpeedController(
    						new SpeedController[] {rightFront_CIMs, rightBack_CIM},
    						new int[] {HW.RIGHT_DRIVE_MOTOR_1_PDP, HW.RIGHT_DRIVE_MOTOR_2_PDP, HW.RIGHT_DRIVE_MOTOR_3_PDP});
    	rightDrive775 = new SpectrumSpeedController(
    						new SpeedController[] {right_775},
    						new int[] {HW.RIGHT_DRIVE_MOTOR_3_PDP});
    	
    	drive = new Drive("775 turbocharged", leftDriveCIMs, leftDrive775, rightDriveCIMs, rightDrive775);
    	
    	
    	//Setup a Solenoid Subsystem and give it an initial state
    	
    }
    
    //Used to keep track of the robot current state easily
    public enum RobotState {
        DISABLED, AUTONOMOUS, TELEOP
    }

    public static RobotState s_robot_state = RobotState.DISABLED;

    public static RobotState getState() {
        return s_robot_state;
    }

    public static void setState(RobotState state) {
        s_robot_state = state;
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	initDebugger();
    	printGeneralInfo("Start robotInit()");
    	setupSubsystems(); //This has to be before the OI is created on the next line
		HW.oi = new OI();
        Dashboard.intializeDashboard();
        logger = Logger.getInstance();
    }
    
    private static void initDebugger(){
    	Debugger.setLevel(Debugger.info3); //Set the initial Debugger Level
    	Debugger.flagOn(general); //Set all the flags on, comment out ones you want off
    	Debugger.flagOn(controls);
    	Debugger.flagOn(input);
    	Debugger.flagOn(output);
    	Debugger.flagOn(auton);
    	Debugger.flagOn(commands);
    }
    /**
     * Initialization code for test mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters test mode.
     */
    public void testInit() {
    	compressor.startLiveWindowMode();
    	compressor.setClosedLoopControl(false);
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        setState(RobotState.DISABLED);
        printGeneralInfo("Start disabledInit()");
        Disabled.init();
        printGeneralInfo("End disableInit()");
    }
    /**
     * This function is called while in disabled mode.
     */    
    public void disabledPeriodic(){
    	Disabled.periodic();
    }


    public void autonomousInit() {
    	setState(RobotState.AUTONOMOUS);
    	printGeneralInfo("Start autonomousInit()");
        Autonomous.init();
        printGeneralInfo("End autonomousInit()");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Autonomous.periodic();
    }

    public void teleopInit() {
    	setState(RobotState.TELEOP);
    	printGeneralInfo("Start teleopInit()");
        Teleop.init();
        printGeneralInfo("End teleopInit()");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Teleop.periodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public static void printGeneralInfo(String msg){
    	Debugger.println(msg, general, Debugger.info3);
    }
}
