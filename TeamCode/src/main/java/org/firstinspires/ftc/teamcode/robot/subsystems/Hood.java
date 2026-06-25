package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.utils.DennyLUT;


@Config
public class Hood extends SubsystemBase {
    public Hood(){}
    public ServoEx h;

    public static double t = 0.5;
    public static double min = 0.16;
    public static double max = 0.88;
    public static double compM = 0.9;
    public static double compShoo = 800;
    public static boolean isCompMode = false;
//    public static double x1 = 41.559,
//            x2 = 85.82,
//            x3 = 133.98;
//    public static double s1 = 0.67,
//            s2 = 0,
//            s3 = 0.15;

    DennyLUT lut;

    public static boolean activated = false;

    public void init(HardwareMap hw) {
        h = new ServoEx(hw,"h");

        lut = new DennyLUT();
//        lut.add(43.6,  0.75);
//        lut.add(75.8,  0.16);
//        lut.add(102.6, 0.16);
//        lut.add(135.6, 0.22);
//        lut.add(150.6, 0.21);

        lut.add(49.2,  0.89);
        lut.add(54.2,0.89);
        lut.add(75.8,  0.32);
        lut.add(90.4,  0.30);

        lut.add(102.6, 0.27);
        lut.add(135.6, 0.29);
        lut.add(150.6, 0.21);
    }

    public double getTarget() {
        return t;
    }
    public void activate(boolean a){
        activated = a;
    }

    public double getHood() {
        return h.getRawPosition();
    }

    @Override
    public void periodic() {
        if(activated)
            h.set(t);
    }

    public void setPos(double pos){
        t = pos;
    }

    public void setTarget(double pos){
        t = pos;
        if(t > max) t = max;
        else if(t<min) t = min;
    }

    public void setTarget(double pos, double x){
        t = pos;

        if(isCompMode){
            Shooter s = Robot.getInstance().shooter;
            double predictedVelocity = s.getVelocity() + s.getAcceleration() * 1.6 ;
            double error = Math.abs(predictedVelocity - s.lut.get(x));
            if(error < 30){
                error = 0;
            }
            double m = (error/compShoo) * compM;
            if(m > compM) m = compM;
            t += m;
        }

        if(t > max) t = max;
        else if(t<min) t = min;
    }

    public InstantCommand set(double v){return new InstantCommand(()->setTarget(v)); }

    public void setCompMode(boolean a){
        isCompMode = a;
    }

    public void setDis(double x){
        setTarget(lut.get(x),x);
    }
}
