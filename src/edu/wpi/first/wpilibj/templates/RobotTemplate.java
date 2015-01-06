/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {

    Joystick Joystick1 = new Joystick(1);
    Joystick JoyStick2 = new Joystick(2);
    RobotDrive robotDrive = new RobotDrive(4, 3, 2, 1);
    DoubleSolenoid climberPiston = new DoubleSolenoid(3, 4);
    Relay compressorSpike = new Relay(1);
    DigitalInput digitalCompresser = new DigitalInput(1);
    DoubleSolenoid magazine = new DoubleSolenoid(1, 2);

    public void autonomous() {

    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);

        while (isOperatorControl() && isEnabled()) {
            robotDrive.tankDrive(-Joystick1.getY(), -JoyStick2.getY());
            if (JoyStick2.getRawButton(6)) {
                climberPiston.set(DoubleSolenoid.Value.kForward);
            }
            if (JoyStick2.getRawButton(7)) {
                climberPiston.set(DoubleSolenoid.Value.kReverse);
            }
            if (JoyStick2.getRawButton(8)) {
                climberPiston.set(DoubleSolenoid.Value.kOff);
            }
            if (!digitalCompresser.get()) {
                compressorSpike.set(Relay.Value.kForward);
            } else {
                compressorSpike.set(Relay.Value.kOff);
                
            }
            if (JoyStick2.getRawButton(11))
            {
                magazine.set(DoubleSolenoid.Value.kForward);
            }
            if (JoyStick2.getRawButton(10))
            {
                magazine.set(DoubleSolenoid.Value.kReverse);
            }
            Timer.delay(0.01);

        }

    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {

    }
}