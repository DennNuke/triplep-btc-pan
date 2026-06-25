package org.firstinspires.ftc.teamcode.auto.comp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robot.utils.Alliance;

@Autonomous(name = "Autie Red", group = "CLOSE")
public class PancakeRedAuto extends PancakeBlueAuto{
    @Override
    public void setAlliance(){
        a = Alliance.RED;
    }
}
