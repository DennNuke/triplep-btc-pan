package org.firstinspires.ftc.teamcode.robot.subsystems;
import static org.firstinspires.ftc.teamcode.robot.subsystems.AxonTurret.normalizeAngle;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierPoint;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.auto.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot.utils.Alliance;
import org.firstinspires.ftc.teamcode.robot.utils.InputScaler;

@Config
public class Drive extends SubsystemBase {
    public Drive(){}
    private Follower f;
    private Alliance a = Alliance.BLUE;
    private boolean hold = false, field = true, holdAngle = false;
    public static double absoluteSlowValue = 1;
    public static double firstSlowValue = 0.5;
    public static double secondSlowValue = 0.1;
    public static double kP = 1, kI = 0, kD = 0.1;
    public static double targetAngle = 0;
    public double heading = 0;
    PIDController control;

    public void init(HardwareMap hw, Alliance a,Pose startPose) {
        init(hw);
        f.setStartingPose(startPose);
        this.a = a;
        absoluteSlowValue = 1;
        control = new PIDController(kP,kI,kD);
    }

    public void init(HardwareMap hw) {
        f = Constants.createFollower(hw);
    }

    public void setAlliance(Alliance a){
        this.a = a;
    }

    public void startDrive() {
        f.startTeleopDrive();
    }
    public void goalReset(){
        Pose reset =  new Pose(18.651, 80.121, Math.toRadians(180));

        if (a.equals(Alliance.BLUE))
            f.setPose(reset);
        else
            f.setPose(new Pose(126.226, 77.205, Math.toRadians(0)));

    }
    @Override
    public void periodic() {
        f.update();
    }
    public void setAngle(double angle){
        targetAngle = angle;
    }
    public void face(Pose targetPose, Pose robotPose) {
        double angleToTargetFromCenter = Math.atan2(targetPose.getY() - robotPose.getY(), targetPose.getX() - robotPose.getX());
        double normalizeAngle = normalizeAngle(angleToTargetFromCenter);
        setAngle(normalizeAngle);
    }
    public void holdAngle(){
        holdAngle = true;
    }
    public void unholdAngle(){
        holdAngle = false;
    }
    public void setSlower(double a){
        absoluteSlowValue = a;
    }
    public void drive(GamepadEx g) {drive(g,false,false);}
    public void drive(GamepadEx g, boolean isSlow, boolean isSlow2) {
        if (!hold) {
            double heading_power = -InputScaler.scaleInputHigh(g.getRightX()) * absoluteSlowValue;
            double forward_power = InputScaler.scaleInputHigh(g.getLeftY())* absoluteSlowValue;
            double strafe_power = -InputScaler.scaleInputHigh(g.getLeftX())* absoluteSlowValue;

            if(isSlow){
                heading_power *= secondSlowValue;
                forward_power *= secondSlowValue;
                strafe_power *= secondSlowValue;
            }else if(isSlow2){
                heading_power *= firstSlowValue;
                forward_power *= firstSlowValue;
                strafe_power *= firstSlowValue;
            }

            if(holdAngle) {
                control.setPID(kP,kI,kD);
                double error = f.getHeading() - targetAngle;
                heading =  f.getHeading();
                if(heading < 0){
                    heading = 3* Math.PI - f.getHeading();
                }
                heading_power = control.calculate(heading,targetAngle);
            }

            if (field) {
                f.setTeleOpDrive(forward_power, strafe_power,
                        heading_power,false, a == Alliance.BLUE ? 3.14 : 0);

            } else
                f.setTeleOpDrive(forward_power, strafe_power,
                        heading_power, true);
        }
    }
    public void teleToggleCentric() {
        field = !field;
    }
    public void setRoboCentric(){
        field = false;
    }
    public void cornerReset() {
        if (a.equals(Alliance.BLUE))
            f.setPose(new Pose(5.14, 8.6, Math.toRadians(0)).mirror());
        else
            f.setPose(new Pose(5.14, 8.6, Math.toRadians(0)));
    }
    public Pose getPose() {
        return f.getPose();
    }
    public double getT() {
        return f.getCurrentTValue();
    }
    public Follower getFollower() { return f;}
}
