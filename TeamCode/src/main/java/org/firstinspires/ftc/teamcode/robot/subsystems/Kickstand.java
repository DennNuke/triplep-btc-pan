package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Kickstand extends SubsystemBase {
    public Kickstand(){}
    public ServoEx servo;
    public static double retrack_p = 1, extend_p = 0.76;
    public boolean extend = false;
    public static double t = 0.73;

    public static boolean activated = false;
    public void init(HardwareMap hw) {
        servo = new ServoEx(hw,"g");
        t = retrack_p;
    }

    public void activate(boolean a){
        activated = a;
    }

    @Override
    public void periodic() {
        if(activated)
            setPosition(t);
    }

    private void setPosition(double pos){
        servo.set(pos);
    }

    public void extend(){
        extend = true;
        t = extend_p;
    }

    public void retrack(){
        extend = false;
        t = retrack_p;
    }
}
