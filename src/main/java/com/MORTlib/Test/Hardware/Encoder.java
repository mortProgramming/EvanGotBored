package com.MORTlib.Test.Hardware;

import com.MORTlib.Test.Hardware.ctre.Krakenx60Motor;
import com.MORTlib.Test.Hardware.ctre.Falcon500Motor;
import com.MORTlib.Test.Hardware.rev.NEOMotor;
import com.MORTlib.Test.Hardware.rev.NEO550Motor;
import com.ctre.phoenix6.hardware.TalonFX;
import com.MORTlib.Test.Hardware.EncoderTypeEnum;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Encoder implements EncoderIntf {

    public EncoderTypeEnum encoderType;
    public int ID;

    public MotorIntf encoder;
    
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