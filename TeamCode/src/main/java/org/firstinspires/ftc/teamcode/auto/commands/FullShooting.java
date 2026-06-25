package org.firstinspires.ftc.teamcode.auto.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class FullShooting extends SequentialCommandGroup {
    boolean done;
    public FullShooting(){
        done = false;
        Robot r = Robot.getInstance();
        addCommands(
                new PreShooting(),
                new WaitCommand(500),
                new ShootCommand()
        );
    }

    @Override
    public boolean isFinished() {
        return done;
    }
}
