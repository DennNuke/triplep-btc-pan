package org.firstinspires.ftc.teamcode.auto.comp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robot.utils.Alliance;


@Autonomous(name = "Solo Autie Red", group = "SOLO")
public class SoloRedAuto extends SoloBlueAuto{
    @Override
    public void setAlliance() {
        a = Alliance.RED;
    }
}
