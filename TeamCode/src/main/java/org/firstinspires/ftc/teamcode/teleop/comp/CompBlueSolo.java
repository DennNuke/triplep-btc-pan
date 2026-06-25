package org.firstinspires.ftc.teamcode.teleop.comp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.utils.Alliance;

@TeleOp(name = "BLUE",group = "111")
public class CompBlueSolo extends CompRedSolo {
    @Override
    public void setAlliance() {
        a = Alliance.BLUE;
    }
}
