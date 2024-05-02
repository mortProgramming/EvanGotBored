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

public class SteerMotor implements MotorIntf {

    public int motorID;
    public MotorTypeEnum motorType;

    public MotorIntf motor;

    public PIDController controllerD;

    public double KP;
    public double KI;
    public double KD;

    public SteerMotor(MotorTypeEnum motorType, int motorID) {
        this.motorID = motorID;
        this.motorType = motorType;

        this.motor = new Motor(motorType, motorID, false);

        switch (motorType) {
            case NEO:
                this.KP = NEO.SWERVE_STEER_KP;
                this.KI = NEO.SWERVE_STEER_KI;
                this.KD = NEO.SWERVE_STEER_KD;
                break;
            case NEO550:
                this.KP = NEO550.SWERVE_STEER_KP;
                this.KI = NEO550.SWERVE_STEER_KI;
                this.KD = NEO550.SWERVE_STEER_KD;
                break;
            case FALCON:
                this.KP = Falcon500.SWERVE_STEER_KP;
                this.KI = Falcon500.SWERVE_STEER_KI;
                this.KD = Falcon500.SWERVE_STEER_KD;
                break;
            case KRAKEN:
                this.KP = Krakenx60.SWERVE_STEER_KP;
                this.KI = Krakenx60.SWERVE_STEER_KI;
                this.KD = Krakenx60.SWERVE_STEER_KD;
                break;
        }

        this.controllerD = new PIDController(KP, KI, KD);
        this.controllerD.enableContinuousInput(0, 360);
        this.controllerD.setTolerance(2, 10);
    }

    public void setPositionD(double position, double setpoint) {
        this.motor.setVoltage(controllerD.calculate(position, setpoint));
    }

    public void setPercent(double percent) {
        this.motor.setPercent(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
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