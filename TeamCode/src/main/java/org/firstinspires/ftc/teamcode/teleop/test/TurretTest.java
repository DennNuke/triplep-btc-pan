package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.AxonTurret;

@TeleOp(group = "test")
@Config
public class TurretTest extends OpMode {
    public static double yaw = 0;
    public static boolean yawMode = true;
    AxonTurret t;
    @Override
    public void init() {
        t = new AxonTurret();
        t.init(hardwareMap);
        t.on();
    }

    @Override
    public void loop() {

        if(yawMode){
            t.setYaw(Math.toRadians( yaw ));
        }

        telemetry.addData("turret pos",t.getTurretTarget());

        t.periodic();
    }
}
