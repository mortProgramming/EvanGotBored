package com.MORTlib.Test.Hardware;

import com.MORTlib.Test.Hardware.ctre.CANCoderEncoder;

public class Encoder implements EncoderIntf {

    public EncoderTypeEnum encoderType;
    public int ID;

    public EncoderIntf encoder;
    
    public Encoder(EncoderTypeEnum encoderType, int ID) {
        this.encoderType = encoderType;
        this.ID = ID;

        switch (encoderType) {
            case CANCODER:
                this.encoder = new CANCoderEncoder(ID);
                break;
        }
    }

    public double getPositionD() {
        return this.encoder.getPositionD();
    }

    public double getPositionR() {
        return this.encoder.getPositionR();
    }

    public EncoderIntf getEncoder() {
        return this.encoder;
    }

    public EncoderTypeEnum getMotorType() {
        return this.encoderType;
    }

}