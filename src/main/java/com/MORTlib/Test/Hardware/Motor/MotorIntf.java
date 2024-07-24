package com.MORTlib.Test.Hardware.Motor;

public interface MotorIntf {

    public void setCurrentLimit(double limit);
    
    public void setDirection(boolean direction);

    public void setPIDValues(double kP, double kI, double kD);

    public void setPercent(double percent);

    public void setVoltage(double voltage);

    public void setPositionD(double position, double setpoint);

    public void setCanivore(String canivore);

    // degrees
    public double getPositionD();

    // 1 is a full turn
    public double getPosition();

    public double getVelocityD();

    public double getVelocity1();

    public Object getMotor();
}
