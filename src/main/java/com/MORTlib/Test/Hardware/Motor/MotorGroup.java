package com.MORTlib.Test.Hardware.Motor;

public class MotorGroup implements MotorIntf {

    public MotorIntf[] motors;

    public int motorCount;

    public MotorGroup (MotorIntf... motors) {
        this.motors = motors; 
        motorCount = motors.length;
    }

    // public void giveAll (Runnable method) {
    //     for (int i = 0; i < motorCount; i++) {
    //         method(motors[i]);
    //     }
    // }

    public void setCurrentLimit(double limit) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setCurrentLimit(limit);
        }
    }

    public void setCurrentLimit(int motorNumber, double limit) {
        motors[motorNumber].setCurrentLimit(limit);
    }

    public void setDirectionFlip(boolean direction) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setDirectionFlip(direction);
        }
    }

    public void setDirectionFlip(int motorNumber, boolean direction) {
        motors[motorNumber].setDirectionFlip(direction);
    }

    public void setPIDValues(double kP, double kI, double kD) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setPIDValues(kP, kI, kD);
        }
    }

    public void setPIDValues(int motorNumber, double kP, double kI, double kD) {
        motors[motorNumber].setPIDValues(kP, kI, kD);
    }

    public void setPercent(double percent) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setPercent(percent);
        }
    }

    public void setPercent(int motorNumber, double percent) {
        motors[motorNumber].setPercent(percent);
    }

    public void setVoltage(double voltage) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setVoltage(voltage);
        }
    }

    public void setVoltage(int motorNumber, double voltage) {
        motors[motorNumber].setVoltage(voltage);
    }

    public void setPositionRotations(double setpoint) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setPositionRotations(setpoint);
        }
    }
    
    public void setPositionRotations(int motorNumber, double setpoint) {
        motors[motorNumber].setPositionRotations(setpoint);
    }

    // public void setPositionRotations(double position, double setpoint) {
    //     for (int i = 0; i < motorCount; i++) {
    //         motors[i].setPositionRotations(position, setpoint);
    //     }
    // }
    
    // public void setPositionRotations(int motorNumber, double position, double setpoint) {
    //     motors[motorNumber].setPositionRotations(position, setpoint);
    // }

    public void setCanivore(String canivore) {
        for (int i = 0; i < motorCount; i++) {
            motors[i].setCanivore(canivore);
        }
    }

    public void setCanivore(int motorNumber, String canivore) {
        motors[motorNumber].setCanivore(canivore);
    }


    
    public double getPositionRotations() {
        return motors[0].getPositionRotations();
    }

     public double getPositionRotations(int motorNumber) {
        return motors[motorNumber].getPositionRotations();
    }

    public double getVelocityRPM() {
        return motors[0].getVelocityRPM();
    }

    public double getVelocityRPM(int motorNumber) {
        return motors[motorNumber].getVelocityRPM();
    }

    public MotorIntf getMotor (int motorNumber) {
        return motors[motorNumber];
    }

    public MotorIntf[] getMotor () {
        return motors;
    }
}
