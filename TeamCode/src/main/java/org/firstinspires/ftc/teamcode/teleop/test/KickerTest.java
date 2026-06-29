package org.firstinspires.ftc.teamcode.teleop.test;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.Kickstand;

@TeleOp(group = "test")
//@Config
public class KickerTest extends OpMode {
    Kickstand kick;
    @Override
    public void init() {
        kick = new Kickstand();
        kick.init(hardwareMap);
        kick.activate(true);
    }

    @Override
    public void loop() {
        kick.periodic();
    }
}
