package org.firstinspires.ftc.teamcode.auto.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class IntakeNorm extends SequentialCommandGroup {
    boolean done;
    public IntakeNorm(){
        done = false;
        Robot r = Robot.getInstance();
        addCommands(
                r.intake.in(),
                new WaitCommand(200),
                r.intake.off(),
                new WaitCommand(200),
                r.intake.in(),
                new InstantCommand(()->done = true)
        );
    }

    @Override
    public boolean isFinished() {
        return done;
    }
}
