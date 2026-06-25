package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.TransferChecker;

@TeleOp
@Disabled
public class ColorSensorTest extends OpMode {
    TransferChecker checker;
    MultipleTelemetry mTelemetry;
    @Override
    public void init() {
        checker = new TransferChecker();
        checker.init(hardwareMap);
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        mTelemetry.addData("IS 1 arti", checker.artiThere(1));
        mTelemetry.addData("IS 2 arti", checker.artiThere(2));
        mTelemetry.addData("IS arti", checker.artiAll());
        mTelemetry.addData("1 dis", checker.getDistance(1));
        mTelemetry.addData("2 dis", checker.getDistance(2));
    }
}
