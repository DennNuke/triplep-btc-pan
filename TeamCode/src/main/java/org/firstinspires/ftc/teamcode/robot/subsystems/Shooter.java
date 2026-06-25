package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.utils.DennyLUT;

@Config
public class Shooter extends SubsystemBase {
    public Shooter(){}
    private MotorEx l;
    private MotorEx r;

    public static double t = 0;
    public static double kS = 0.07, kP = 0.004;
    public static double kV1 = 0.000312, kV2 = 0.00032;

    public static double speed1 = 1100, speed2 = 1800;

//    public static double x1 = 41.559,
//            x2 = 85.82,
//            x3 = 133.98;
//    public static double s1 = 1000,
//            s2 = 1360,
//            s3 = 1480;

    public DennyLUT lut;

    public static boolean activated = true;
    public static boolean volComps = false;

    private double velOffset = 0;

    public void init(HardwareMap hw) {
        l = new MotorEx(hw,"sl");

        r = new MotorEx(hw,"sr");
        r.setInverted(true);

        lut = new DennyLUT();
//        lut.add(43.6, 980);
//        lut.add(75.8, 1320);
//        lut.add(102.6, 1360);
//        lut.add(135.6, 1740);
//        lut.add(150.6, 1840);

        lut.add(49.2, 950);
        lut.add(54.2,1000);
        lut.add(75.8, 1270);
        lut.add(90.4,  1330 );

        lut.add(102.6, 1390);
        lut.add(135.6, 2000);
        lut.add(150.6, 2100);
    }

    public double getTarget() {
        return t;
    }

    public double getVelocity() {
        return l.getVelocity();
    }

    public double getAcceleration() {
        return l.getAcceleration();
    }

    public void set(double p) {
        if(volComps){
            p = Robot.getInstance().voltage.getPower(p);
        }
        l.set(p);
        r.set(p);
    }

    public void turnOff() {
        activated = false;
        set(0);
    }

    public void turnOn() {
        activated = true;
    }

    public boolean isOn(){
        return activated;
    }

    public void shooterToggle() {
        activated = !activated;
        if (!activated)
            set(0);
    }

    public boolean atTarget() {
        return Math.abs((getTarget()- getVelocity())) < 60;
    }
    public void setDis(double x) {
//        double output = 0;
//        if(x < x2){
//            output = s1 +(x - x1)/(x2-x1) * (s2-s1);
//        }else{
//            output = s2 +(x - x2)/(x3-x2) * (s3-s2);
//        }

        setShooterTarget(lut.get(x) + velOffset);
    }

    public void setShooterTarget(double velocity) {
        t = velocity;
    }

    @Override
    public void periodic() {
        if (activated) {

            set((kV1 + (getTarget() - speed1)/(speed2 - speed1) * (kV2 - kV1)) * getTarget() + (kP * (getTarget() - getVelocity())) + kS);
        }
    }

    public void addVelOffset(double d) {
        velOffset += d;
    }

    public void resetVelOffset() {
        velOffset = 0;
    }

    public double getVelOffset() {
        return velOffset;
    }

    public void enableVoltageCompensation(boolean a){
        volComps = true;
    }

    public InstantCommand on(){return new InstantCommand(this::turnOn);}
    public InstantCommand off(){return new InstantCommand(this::turnOff);}

    public InstantCommand toggle() {
        return new InstantCommand(this::shooterToggle);
    }

    public InstantCommand setTarget(double v){return new InstantCommand(()->setShooterTarget(v)); }

    public InstantCommand setDistance(double x){
        return new InstantCommand(()->setDis(x));
    }
}

