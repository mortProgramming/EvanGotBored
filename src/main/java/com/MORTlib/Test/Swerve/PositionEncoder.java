package com.MORTlib.Test.Swerve;

import edu.wpi.first.math.controller.PIDController;

import com.MORTlib.Test.Hardware.ctre.CTREUtility;
import com.MORTlib.Test.Hardware.rev.RevUtility;

import com.MORTlib.Test.Hardware.Motor;
import com.MORTlib.Test.Hardware.MotorIntf;
import com.MORTlib.Test.Hardware.MotorTypeEnum;
import com.MORTlib.Test.Hardware.Encoder;
import com.MORTlib.Test.Hardware.EncoderIntf;
import com.MORTlib.Test.Hardware.EncoderTypeEnum;

public class PositionEncoder implements EncoderIntf {

    public int encoderID;
    public EncoderTypeEnum encoderType;

    public EncoderIntf encoder;

    public PositionEncoder(EncoderTypeEnum encoderType, int encoderID) {
        this.encoderID = encoderID;
        this.encoderType = encoderType;

        this.encoder = new Encoder(encoderID, encoderType);
    }

    public void setPositionD(double position) {
        this.motor.setVoltage(controller.calculate(encoder.getPositionD(), position));
    }

    public double getPositionD() {
        return this.encoder.getPositionD();
    }

}