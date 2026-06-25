package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

@TeleOp
@Config
@Disabled
public class IntakoTest extends OpMode {
    Motor i1,i2;
    @Override
    public void init() {
        i1 = new MotorEx(hardwareMap,"i1");
        i2 = new MotorEx(hardwareMap,"i2");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            i1.set(1);
        }else{
            i1.set(0);
        }

        if(gamepad1.b){
            i2.set(1);
        }else{
            i2.set(0);
        }
    }
}
