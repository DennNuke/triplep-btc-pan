package org.firstinspires.ftc.teamcode.auto.comp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robot.utils.Alliance;


@Autonomous(name = "Far Autie Red", group = "FAR")
public class FarPancakeRedAuto extends FarPancakeBlueAuto{
    @Override
    public void setAlliance(){
        a = Alliance.RED;
    }
}
