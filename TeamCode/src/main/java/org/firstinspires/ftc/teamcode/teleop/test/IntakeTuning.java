package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.DoubleIntake;

@TeleOp
@Config
@Disabled
public class IntakeTuning extends OpMode {
    DoubleIntake intake;
    public static double speed = 0;
    TelemetryPacket packet;
    FtcDashboard dash;
    @Override
    public void init() {
        intake = new DoubleIntake();
        intake.init(hardwareMap);
        packet = new TelemetryPacket();
        dash = FtcDashboard.getInstance();
    }

    @Override
    public void loop() {
        intake.setSpeed(speed);
        packet.put("speed",intake.getVelocity() );
        packet.put("target",speed );
        dash.sendTelemetryPacket(packet);
    }
}
