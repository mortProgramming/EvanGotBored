package com.MORTlib.Test.Hardware;

import edu.wpi.first.math.controller.PIDController;

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

    public void setCurrentLimit(double limit) {
        this.motor.setCurrentLimit(limit);
    }

    public void setDirection(boolean direction) {
        this.motor.setDirection(direction);
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

    public double getPosition() {
        return this.motor.getPosition();
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