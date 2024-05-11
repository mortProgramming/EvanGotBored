package com.MORTlib.Test.Hardware.ctre;

import com.MORTlib.Test.Hardware.EncoderIntf;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.geometry.Rotation2d;

public class CANCoderEncoder implements EncoderIntf {

    public int ID;

    public CANcoder encoder;

    public CANCoderEncoder(int ID) {
        this.ID = ID;

        encoder = new CANcoder(ID);
    }

    public Rotation2d getPosition() {
        return Rotation2d.fromRotations(encoder.getPosition().getValueAsDouble());
    }

    public double getPositionD() {
        return encoder.getPosition().getValueAsDouble() * 360;
    }

    public double getPositionR() {
        return encoder.getPosition().getValueAsDouble();
    }

    public CANcoder getEncoder() {
        return encoder;
    }
 
}