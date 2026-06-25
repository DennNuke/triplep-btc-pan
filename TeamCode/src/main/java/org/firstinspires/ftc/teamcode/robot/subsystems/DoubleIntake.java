package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
@Config
public class DoubleIntake extends SubsystemBase {
    public DoubleIntake(){}

    MotorEx mr,ml;
    public static double in = 1;
    public static double out = -1;
    public static double off = 0;
    public static double shoot = 0.6;

    public static double inSpeed = 2100;
    public static double shootSpeed = 1100;
    public static double shootSpeedClose = 2100;

    public static double kP = 0.0005, kF = 0.000527;
    public void init(HardwareMap hw){
        mr = new MotorEx(hw,"i1");
        ml = new MotorEx(hw,"i2");
        ml.setInverted(true);
    }
    public void setPower(double power){
        mr.set(power);
        ml.set(power);
    }
    public double getVelocity(){
        return ml.getVelocity();
    }
    public void setSpeed(double speed){
        double error =  speed - getVelocity();
        setPower(speed * kF + error * kP);
    }
    public void speedIn(){setSpeed(inSpeed);}
    public void speedShoot(){setSpeed(shootSpeed);}
    public void speedShootClose(){setSpeed(shootSpeedClose);}
    public void spinIn(){
        setPower(in);
    }
    public void spinOut(){
        setPower(out);
    }
    public void spinOff(){
        setPower(off);
    }
    public void spinShoot(){setPower(shoot);}
    public InstantCommand shoot(){return new InstantCommand(this::spinShoot);}
    public InstantCommand in(){
        return new InstantCommand(this::spinIn);
    }

    public InstantCommand shootSpeedCommand(){
        return new InstantCommand(this::speedShootClose);
    }

    public InstantCommand shootSpeedFarCommand(){
        return new InstantCommand(this::speedShoot);
    }
    public InstantCommand out(){
        return new InstantCommand(this::spinOut);
    }
    public InstantCommand off(){
        return new InstantCommand(this::spinOff);
    }
}
