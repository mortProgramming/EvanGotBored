package com.MORTlib.hardware.brands.ctre;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;

import com.MORTlib.hardware.imu.IMUIntf;

import com.ctre.phoenix6.hardware.Pigeon2;

public class Pigeon2IMU implements IMUIntf {

    public Pigeon2 imu;

    public int ID;

    public Pigeon2IMU(int ID) {
        this.ID = ID;

        imu = new Pigeon2(ID);
    }

    public void setCanivore(String canivore) {
        imu = new Pigeon2(ID, canivore);
    }

    public double getAngle() {
        return imu.getAngle();
    }

    public double getRate() {
        return imu.getRate();
    }

    public void reset () {
        imu.reset();
    }
    
    public Rotation2d getRotation2d() {
        return imu.getRotation2d();
    }

    public Rotation3d getRotation3d() {
        return imu.getRotation3d();
    }

    public Pigeon2 getIMU() {
        return imu;
    }

}
