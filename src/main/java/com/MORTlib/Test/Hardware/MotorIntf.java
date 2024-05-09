package com.MORTlib.Test.Hardware;

public interface MotorIntf {

    public void setDirection(boolean direction);

    public void setPIDValues(double kP, double kI, double kD);

    public void setPercent(double percent);

    public void setVoltage(double voltage);

    public void setPositionD(double position, double setpoint);

    // degrees
    public double getPositionD();

    // 1 is a full turn
    public double getPosition1();

    public double getVelocityD();

    public double getVelocity1();


}