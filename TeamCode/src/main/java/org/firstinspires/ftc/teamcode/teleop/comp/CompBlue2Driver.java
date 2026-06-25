package org.firstinspires.ftc.teamcode.teleop.comp;

import org.firstinspires.ftc.teamcode.robot.utils.Alliance;

//@TeleOp(name = "BLUEEEEE",group = "111")
public class CompBlue2Driver extends CompRed2Driver {
    @Override
    public void setAlliance() {
        a = Alliance.BLUE;
    }
}
