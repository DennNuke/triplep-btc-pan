package org.firstinspires.ftc.teamcode.teleop.test;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.leds.Prism.Direction;
import org.firstinspires.ftc.teamcode.robot.leds.PrismLed;

@Config
@Disabled
@TeleOp(name = "Prism TeleOp", group = "test")
public class PrismTest extends OpMode {
    PrismLed prism;
    public static int sides = 0;
    public static int r = 0,g = 0,b = 0;
    public static int pulseRed = 0, pulseGreen = 0, pulseBlue = 0;
    public static int pulseBrightness = 0;
    public static float speed = 0;
    public static int pulsePeriod = 0;
    public static int brightness = 0;
    public static boolean update = false;

    @Override
    public void init() {
        prism = new PrismLed();
        prism.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(update){
            update = false;
            prism.update();
        }
//        switch(sides){
//            case 0:
//                prism.solidIndexes(PrismLed.LedSides.RIGHT);
//                break;
//            case 1:
//                prism.solidIndexes(PrismLed.LedSides.BACK_RIGHT);
//                break;
//            case 2:
//                prism.solidIndexes(PrismLed.LedSides.BACK_LEFT);
//                break;
//            case 3:
//                prism.solidIndexes(PrismLed.LedSides.LEFT);
//                break;
//        }
        prism.solidIndexes(0,35);

//        prism.getSolid().setPrimaryColor(r,g,b);
//        prism.getSolid().setBrightness(brightness);

        prism.getSineWave().setDirection(Direction.Forward);
        prism.getSineWave().setPeriod(pulsePeriod);
        prism.getSineWave().setPrimaryColor(pulseRed,pulseGreen,pulseBlue);
        prism.getSineWave().setBrightness(pulseBrightness);
        prism.getSineWave().setSpeed(speed);

//        prism.getPulse().setIndexes(0,35);
//        prism.getPulse().setPrimaryColor(pulseRed,pulseGreen,pulseBlue);
//        prism.getPulse().setBrightness(brightness);
//        prism.getPulse().setPeriod(pulsePeriod);
    }
}
