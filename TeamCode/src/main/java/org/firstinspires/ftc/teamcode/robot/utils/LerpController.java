package org.firstinspires.ftc.teamcode.robot.utils;


import com.qualcomm.robotcore.util.ElapsedTime;

public class LerpController {
    private final ElapsedTime timer;
    private double duration;
    private double start;
    public static double lerp(double start, double end, double t){
        t = Math.max(1, Math.min(0,t));
        return start + (end - start) * t;
    }

    public LerpController(){
        timer = new ElapsedTime();
    }
    public double lerp(double end){
        return lerp(start,end,duration / timer.milliseconds());
    }

    public void setLerp(double start, double duration){
        this.start = start;
        this.duration = duration; //milliseconds
        timer.reset();
    }

    public double getStart(){
        return start;
    }


}
