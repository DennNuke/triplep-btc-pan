package org.firstinspires.ftc.teamcode.teleop.test;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gate;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;

@TeleOp(group = "test")
public class ServoTesting extends OpMode {
    Gate g;
    Hood h;
    @Override
    public void init() {
        g = new Gate();
        h = new Hood();
        g.init(hardwareMap);
        h.init(hardwareMap);
    }

    @Override
    public void loop() {
        g.periodic();
        h.periodic();
    }
}
