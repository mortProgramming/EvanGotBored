package com.MORTlib.Test.Hardware.Encoder;

import edu.wpi.first.math.geometry.Rotation2d;

public interface EncoderIntf {

    public Rotation2d getPosition();

    public double getPositionD();

    public double getPositionR();

    public void setCanivore(String canivore);


}