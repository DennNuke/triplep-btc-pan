package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.utils.Alliance;

import java.util.List;

public class DennyOpMode extends OpMode {
    protected GamepadEx base, helper;
    protected List<LynxModule> allHubs;
    protected ElapsedTime deltaTimer;
    protected double deltaTime;
    protected Robot r;
    protected Alliance a;
    public void onInit(){}
    public void setAlliance(){a = Alliance.BLUE;}
    public void onStart(){}
    public void onUpdate(){}
    @Override
    public void init() {
        base = new GamepadEx(gamepad1);
        helper = new GamepadEx(gamepad2);
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        r = Robot.getInstance();
        deltaTimer = new ElapsedTime();

        onInit();
        setAlliance();
    }

    @Override
    public void start() {
        r.init(hardwareMap,a);
        deltaTimer.reset();
        onStart();
    }

    @Override
    public void loop() {
        deltaTime = deltaTimer.milliseconds()/1000;
        deltaTimer.reset();

        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
        base.readButtons();
        helper.readButtons();

        onUpdate();

        r.periodic();
    }
}
