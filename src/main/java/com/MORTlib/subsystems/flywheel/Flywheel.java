package com.MORTlib.subsystems.flywheel;

import com.MORTlib.hardware.motor.Motor;
import com.MORTlib.hardware.motor.MotorIntf;
import com.MORTlib.hardware.motor.MotorTypeEnum;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class Flywheel {
    public MotorIntf motor;

    public SimpleMotorFeedforward feedForward;

    public Flywheel(MotorTypeEnum motorType, int motorID) {
        this(new Motor(motorType, motorID));
    }

    public Flywheel(MotorIntf motor) {
        this.motor = motor;

        feedForward = new SimpleMotorFeedforward(0, 0, 0);
    }

    public void setFeedforward(double kS, double kV, double kA) {
        feedForward = new SimpleMotorFeedforward(kS, kV, kA);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public void setFeededVoltage(double voltage, double velocityRotations) {
        motor.setVoltage(voltage + feedForward.calculate(velocityRotations));
    }

    public void setFeededVoltage(double voltage, double velocityRotations, double accelerationRotations) {
        motor.setVoltage(voltage + feedForward.calculate(velocityRotations, accelerationRotations));
    }
}
