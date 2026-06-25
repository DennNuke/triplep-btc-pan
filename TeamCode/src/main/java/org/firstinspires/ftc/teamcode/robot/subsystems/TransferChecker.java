package org.firstinspires.ftc.teamcode.robot.subsystems;

import android.graphics.Color;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class TransferChecker extends SubsystemBase {
    public TransferChecker(){}
    public NormalizedColorSensor colorSensor1,colorSensor2;
    public static double dis1 = 5, dis2 = 5;

    public void init(HardwareMap hw){
        colorSensor1 = hw.get(NormalizedColorSensor.class, "color1");
        colorSensor2 = hw.get(NormalizedColorSensor.class, "color2");

        colorSensor1.setGain(50);
        colorSensor2.setGain(50);

        if (colorSensor1 instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor1).enableLight(true);
        }
        if (colorSensor2 instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor2).enableLight(true);
        }
    }

    public boolean artiThere(int num){
        if(num == 1){
//            return (getColors(num).alpha < 0.260 &&  (gethsvValue(num)[0] >= 139.6));\
            return (getColors(num).blue / gethsvValue(num)[2] < 0.745) || ((getColors(num).alpha < 0.18) && (getColors(num).alpha > 0.1));
        }else{
            return (getColors(num).green < 0.09 && (gethsvValue(num)[0] > 170)) || getDistance(num) < 6.5;
        }
    }

    public boolean artiAll(){
        return artiThere(1) && artiThere(2);
    }

    public double getDistance(int num){
        NormalizedColorSensor color = (num == 2?colorSensor2:colorSensor1);
        if (color instanceof DistanceSensor) {
            return ((DistanceSensor) color).getDistance(DistanceUnit.CM);
        }
        return 0;
    }

    public NormalizedRGBA getColors(int num){
        NormalizedColorSensor color = (num == 2?colorSensor2:colorSensor1);
        return color.getNormalizedColors();
    }

    public float[] gethsvValue(int num){
        NormalizedColorSensor color = (num == 2?colorSensor2:colorSensor1);
        NormalizedRGBA colors = getColors(num);
        float[] hsvValues = new float[3];
        Color.colorToHSV(colors.toColor(), hsvValues);
        return hsvValues;
    }
}
