package org.firstinspires.ftc.teamcode.robot.utils;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.skeletonarmy.marrow.zones.Point;
import com.skeletonarmy.marrow.zones.PolygonZone;

import org.firstinspires.ftc.teamcode.robot.Robot;


@Config
public class PoseController {
    public static double rVM = 0.7;
    private final static PolygonZone closeLaunchZone = new PolygonZone(new Point(144, 144), new Point(72, 72), new Point(0, 144));
    private final static PolygonZone farLaunchZone = new PolygonZone(new Point(48, 0), new Point(72, 24), new Point(96, 0));
    private final static PolygonZone robotZone = new PolygonZone(15, 18);
    private final static PolygonZone bigRobotZone = new PolygonZone(50, 50);
    public static boolean isInZone(Pose pose){
        robotZone.setPosition(pose.getX(), pose.getY());
        robotZone.setRotation(pose.getHeading());
        return robotZone.isInside(closeLaunchZone)
                || robotZone.isInside(farLaunchZone);
    }

    public static boolean isNearZone(Pose pose){
        bigRobotZone.setPosition(pose.getX(), pose.getY());
        bigRobotZone.setRotation(pose.getHeading());
        return bigRobotZone.isInside(closeLaunchZone)
                || bigRobotZone.isInside(farLaunchZone);
    }

    public static boolean isInFarZone(Pose pose){
        robotZone.setPosition(pose.getX(), pose.getY());
        robotZone.setRotation(pose.getHeading());
        return robotZone.isInside(farLaunchZone);
    }

    public static Pose nearBigZonePose(Pose pose){
        if(isInZone(pose)) return pose;
        double x = 72 + (pose.getX() - pose.getY())/2;
        return new Pose(x,144-x,pose.getHeading());
    }

    public static Pose nearSmallZonePose(Pose pose){
        if(isInZone(pose)) return pose;
        double x = 24 + (pose.getX() + pose.getY())/2;
        return new Pose(x,x-48, pose.getHeading());
    }

    public static Pose getNearestPose(Pose pose){
        if(pose.distanceFrom(nearBigZonePose(pose)) >
                pose.distanceFrom(nearSmallZonePose(pose)))
            return nearSmallZonePose(pose);
        else return nearBigZonePose(pose);
    }

    public static double getGoalDis(Robot r){
        return r.drive.getPose().distanceFrom(r.getAlliance().pose) ;
    }

    public static double getGoalDis(Pose r,Alliance a){
        double dis =  r.distanceFrom(a.pose);
//        if(a.equals(Alliance.BLUE))
//            dis += 3;
        return dis;
    }

    public static Pose getFuturePose(Follower f){
        return getFuturePose(f,rVM);
    }

    public static Pose getFuturePose(Follower f,double kK){
        return new Pose(f.getPose().getX() + f.getVelocity().getXComponent() * kK,
                f.getPose().getY() + f.getVelocity().getYComponent() * kK,
                f.getHeading()
        );
    }

}


