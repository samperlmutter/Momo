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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Momo extends SimpleRobot
{

    Joystick joystick1 = new Joystick(1);
    Joystick joystick2 = new Joystick(2);
    RobotDrive robotDrive = new RobotDrive(4, 3, 2, 1);
    DoubleSolenoid climberPiston = new DoubleSolenoid(3, 4);
    Relay compressorSpike = new Relay(1);
    DigitalInput digitalCompresser = new DigitalInput(1);
    DoubleSolenoid magazine = new DoubleSolenoid(1, 2);
    Jaguar frontMotor = new Jaguar(5);
    Jaguar backMotor = new Jaguar(6);
    Relay hammer = new Relay(6);

    public
            void autonomous()
    {

    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl()
    {
        robotDrive.setSafetyEnabled(true);

        while (isOperatorControl() && isEnabled())
        {
            robotDrive.tankDrive(-joystick1.getY(), -joystick2.getY());

            if (joystick2.getRawButton(6))
            {
                climberPiston.set(DoubleSolenoid.Value.kForward);
            }
            if (joystick2.getRawButton(7))
            {
                climberPiston.set(DoubleSolenoid.Value.kReverse);
            }
            if (joystick2.getRawButton(8))
            {
                climberPiston.set(DoubleSolenoid.Value.kOff);
            }
            if (!digitalCompresser.get())
            {
                compressorSpike.set(Relay.Value.kForward);
            }
            else
            {
                compressorSpike.set(Relay.Value.kOff);

            }
            if (joystick2.getRawButton(11))
            {
                magazine.set(DoubleSolenoid.Value.kForward);
            }
            if (joystick2.getRawButton(10))
            {
                magazine.set(DoubleSolenoid.Value.kReverse);
            }

            double transformedThrottleRightJoystick = transformThrottle(joystick2.getZ());
            double transformedThrottleLeftJoystick = transformThrottle(joystick1.getZ());

            System.out.print(joystick2.getZ() + " " + transformedThrottleRightJoystick);
            System.out.println("    ||    " + joystick2.getZ() + " " + transformedThrottleLeftJoystick);

            if (transformedThrottleRightJoystick > .1)
            {
                frontMotor.set(transformedThrottleRightJoystick);
            }
            else
            {
                frontMotor.set(0);
            }
            if (transformedThrottleLeftJoystick > .1)
            {
                backMotor.set(transformedThrottleLeftJoystick);
            }
            else
            {
                backMotor.set(0);
            }
            if (joystick2.getRawButton(1))
            {
                hammer.set(Relay.Value.kReverse);
            }
            else if (joystick2.getRawButton(2))
            {
                hammer.set(Relay.Value.kForward);
            }
            else
            {
                hammer.set(Relay.Value.kOff);
            }
            Timer.delay(0.01);

        }

    }

    public
            double transformThrottle(double throttle)
    {
        double transformedThrottle = (((-(throttle)) + 1) / 2);
        return transformedThrottle;
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public
            void test()
    {

    }
}
