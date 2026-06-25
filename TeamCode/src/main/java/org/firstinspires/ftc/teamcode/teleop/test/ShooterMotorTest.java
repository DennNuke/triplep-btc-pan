package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

@TeleOp(name="Shooter Testing",group = "test")
//@Disabled
@Config
public class ShooterMotorTest extends OpMode {
    Motor i1,i2;
    public static boolean is1 = false, is2 = false;
    @Override
    public void init() {
        i1 = new MotorEx(hardwareMap,"sl");
        i2 = new MotorEx(hardwareMap,"sr");
    }

    @Override
    public void loop() {

        if(gamepad1.aWasPressed()){
            is1 = !is1;
        }
        if(gamepad1.bWasPressed()){
            is2 = !is2;
        }

        if(is1){
            i1.set(1);
        }else{
            i1.set(0);
        }
        if(is2){
            i2.set(1);
        }else{
            i2.set(0);
        }
    }
}
