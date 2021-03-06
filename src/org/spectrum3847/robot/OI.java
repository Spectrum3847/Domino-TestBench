package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.Gamepad;
import org.spectrum3847.robot.commands.CANRunAtSetpoint;
import org.spectrum3847.robot.commands.SolenoidCommand;
import org.spectrum3847.robot.commands.TurboToggle;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // four ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	// Toggle a command when the button is pushed each time, on, off, on, off, etc
	// button.toggleWhenPressed(new ExampleCommand());

    //Use this constructor to setup up button schedulers for commands
    public OI() {
    	//Driver
    	/*HW.Driver_Gamepad.getButton(Gamepad.A_BUTTON).toggleWhenPressed(
    								new SolenoidCommand("0 & 1 Extend",
    								Robot.sol_0_1,
    								true));
    	*/
    	/*HW.Operator_Gamepad.getButton(Gamepad.A_BUTTON).toggleWhenPressed(
    								new CANRunAtSetpoint("Shooter at Setpoint PID",
    								Robot.shooter));	
    	*/
    	
    	//Toggle 775s enabled/disabled
    	HW.Driver_Gamepad.getButton(Gamepad.B_BUTTON).toggleWhenPressed(
    								new TurboToggle());
    	
    	
    	
    }
}

