package org.firstinspires.ftc.teamcode.robot.leds;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.leds.Prism.Color;
import org.firstinspires.ftc.teamcode.robot.leds.Prism.Direction;
import org.firstinspires.ftc.teamcode.robot.leds.Prism.GoBildaPrismDriver;
import org.firstinspires.ftc.teamcode.robot.leds.Prism.PrismAnimations;
import org.firstinspires.ftc.teamcode.robot.utils.Alliance;

//@Config
public class PrismLed {
    public PrismLed(){}
    private GoBildaPrismDriver prism;
    private final PrismAnimations.Solid solid = new PrismAnimations.Solid();
    private final PrismAnimations.Pulse pulse = new PrismAnimations.Pulse();
    private final PrismAnimations.SineWave sineWave = new PrismAnimations.SineWave();
    private final PrismAnimations.Rainbow rainbow = new PrismAnimations.Rainbow();
    PrismAnimations.Snakes snakes = new PrismAnimations.Snakes();
    public boolean isAnim = false;
    public int artiCount = 0;
    public enum LedSides{
        RIGHT(0,11),
        BACK_RIGHT(12,17),
        BACK_LEFT(18,23),
        LEFT(24,35);

        final int start, end;
        LedSides(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public void init(HardwareMap hw){
        prism = hw.get(GoBildaPrismDriver.class,"prism");

        solid.setBrightness(100);
        pulse.setBrightness(100);
        sineWave.setBrightness(100);

        snakes.setColors(new Color( 240, 0, 100));
        snakes.setBrightness(100);
        snakes.setIndexes(0, 35);
        snakes.setSnakeLength(35);
        snakes.setSpeed(0.1f);

        rainbow.setBrightness(100);
//        rainbow.setHues(100,200);
        rainbow.setIndexes(0,35);
        rainbow.setSpeed(0.1f);

        clear();
    }

    public void clear(){
        prism.clearAllAnimations();
    }

    public void solidIndexes(int startIndex, int stopIndex){
        solid.setIndexes(startIndex, stopIndex);
    }

    public void update(){
        prism.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, snakes);
        if(isAnim) {
            prism.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_1, pulse);
            prism.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_2, sineWave);
        }
    }

    public void setPanheya(){
        pulse.setIndexes(0,35);
        solid.setPrimaryColor(190,0,100);

        sineWave.setDirection(Direction.Forward);
        sineWave.setPeriod(0);
        sineWave.setPrimaryColor(0,0,0);
        sineWave.setSpeed(0);

        pulse.setPrimaryColor(0,0,0);
        pulse.setPeriod(0);

        update();
    }

    public void setNoArti(){
        solid.setPrimaryColor(255,0,255);

        if(artiCount != 0)
            update();
        artiCount = 0;
    }

    public void set1Arti(){
        solid.setPrimaryColor(0,255,255);

        if(artiCount != 1)
            update();
        artiCount = 1;
    }

    public void set2Arti(){
        solid.setPrimaryColor(255,255,0);

        if(artiCount != 2)
            update();
        artiCount = 2;
    }

    public void set3Arti(){
        solid.setPrimaryColor(255,255,255);

        if(artiCount != 3)
            update();
        artiCount = 3;
    }

    public void setAllianceColor(Alliance a){
        if(a == Alliance.RED)
            setRed();
        else
            setBlue();
    }

    public void setBlue(){
        pulse.setIndexes(0,35);
        solid.setPrimaryColor(0,0,200);

        sineWave.setDirection(Direction.Forward);
        sineWave.setPeriod(0);
        sineWave.setPrimaryColor(0,0,0);
        sineWave.setSpeed(0);

        pulse.setPrimaryColor(0,0,0);
        pulse.setPeriod(0);

        update();
    }

    public void setRed(){
        pulse.setIndexes(0,35);
        solid.setPrimaryColor(200,0,0);

        sineWave.setDirection(Direction.Forward);
        sineWave.setPeriod(0);
        sineWave.setPrimaryColor(0,0,0);
        sineWave.setSpeed(0);

        pulse.setPrimaryColor(0,0,0);
        pulse.setPeriod(0);

        update();
    }

    public void solidIndexes(LedSides side){
        solidIndexes(side.start,side.end);
    }
    public PrismAnimations.Solid getSolid(){return solid;}
    public PrismAnimations.Pulse getPulse(){return pulse;}
    public PrismAnimations.SineWave getSineWave(){return sineWave;}
    public GoBildaPrismDriver getPrism(){return prism;}



    public void clearAnimation(){
        prism.clearAllAnimations();
    }

    public int getLedNumber(){
        return prism.getNumberOfLEDs();
    }
}
