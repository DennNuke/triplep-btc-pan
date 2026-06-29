package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.DoubleIntake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;


@TeleOp(group = "test")
@Config
//@Disabled
public class IntakeTuning extends OpMode {
    DoubleIntake intake;
    Shooter shooter;
    public static double pos = 0;
    public static boolean transfer = false;
    public static double speed = 0;
    TelemetryPacket packet;
    FtcDashboard dash;
    @Override
    public void init() {
        intake = new DoubleIntake();
        intake.init(hardwareMap);

        shooter = new Shooter();
        shooter.init(hardwareMap);

        packet = new TelemetryPacket();
        dash = FtcDashboard.getInstance();
    }

    @Override
    public void loop() {
//        intake.setSpeed(speed);

        if(gamepad1.right_trigger>0.1){
            intake.spinIn();
        }else if(gamepad1.left_trigger>0.1){
            intake.spinShoot();
        }else{
            intake.spinOff();
        }

//        if(transfer){
//            intake.transferOn();
//        }else{
//            intake.transferOff();
//        }

        shooter.periodic();
        packet.put("speed",intake.getVelocity() );
        packet.put("target",speed );
        dash.sendTelemetryPacket(packet);
    }
}
