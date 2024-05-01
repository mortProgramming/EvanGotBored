package com.MORTlib.Test.Hardware;

public interface MotorIntf {

    public void setPercent(double percent);

    public void setVoltage(double voltage);

    // degrees
    public double getPositionD();

    // 1 is a full turn
    public double getPosition1();

    public double getVelocityD();

    public double getVelocity1();


}