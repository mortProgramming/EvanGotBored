package com.MORTlib.Test.Swerve;

import edu.wpi.first.math.controller.PIDController;

import com.MORTlib.Test.Hardware.ctre.CTREUtility.Krakenx60;
import com.MORTlib.Test.Hardware.ctre.CTREUtility.Falcon500;
import com.MORTlib.Test.Hardware.rev.RevUtility.NEO550;
import com.MORTlib.Test.Hardware.rev.RevUtility.NEO;
import com.MORTlib.Test.Hardware.Motor;
import com.MORTlib.Test.Hardware.MotorIntf;
import com.MORTlib.Test.Hardware.MotorTypeEnum;
import com.MORTlib.Test.Hardware.Encoder;
import com.MORTlib.Test.Hardware.EncoderIntf;
import com.MORTlib.Test.Hardware.EncoderTypeEnum;

public class PIDMotor implements MotorIntf {

    public int motorID;
    public MotorTypeEnum motorType;

    public MotorIntf motor;

    public PIDController controller;

    public PIDMotor(MotorTypeEnum motorType, int motorID) {
        this.motorID = motorID;
        this.motorType = motorType;

        this.motor = new Motor(motorType, motorID);

        this.controller = new PIDController(0, 0, 0);
        this.controller.enableContinuousInput(-180, 180);
        this.controller.setTolerance(2, 10);
    }

    public void setPositionD(double position, double setpoint) {
        this.motor.setVoltage(controller.calculate(position, setpoint));
    }

    public void setPercent(double percent) {
        this.motor.setPercent(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public void setPIDValues(double kP, double kI, double kD) {
        controller = new PIDController(kP, kI, kD);
    }

    public void setPIDTolerance(double position, double velocity) {
        this.controller.setTolerance(position, velocity);
    }



    public double getPositionD() {
        return this.motor.getPositionD();
    }

    public double getPosition1() {
        return this.motor.getPosition1();
    }

    public double getVelocityD() {
        return this.motor.getVelocityD();
    }

    public double getVelocity1() {
        return this.motor.getVelocity1();
    }

    public MotorIntf getMotor() {
        return this.motor;
    }

    public MotorTypeEnum getMotorType() {
        return this.motorType;
    }

}