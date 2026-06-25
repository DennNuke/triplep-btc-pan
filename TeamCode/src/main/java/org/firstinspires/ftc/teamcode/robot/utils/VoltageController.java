package org.firstinspires.ftc.teamcode.robot.utils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class VoltageController {
    private VoltageSensor voltageSensor;
    ElapsedTime timer;
    double lastVoltage = 12;
    public final double NOMINAL_VOLTAGE = 12;
    public void init(HardwareMap hw){
        timer = new ElapsedTime();
        voltageSensor = hw.voltageSensor.iterator().next();
        lastVoltage = voltageSensor.getVoltage();
    }
    public double getVoltage(){
        if(timer.milliseconds() > 250) {
            lastVoltage = voltageSensor.getVoltage();
            timer.reset();
        }
        return lastVoltage;
    }
    public double getPower(double power, double nominal_voltage){
        return power;
//        return power * getVoltage() / nominal_voltage;
    }
    public double getPower(double power){
        return getPower(power,NOMINAL_VOLTAGE);
    }
}
