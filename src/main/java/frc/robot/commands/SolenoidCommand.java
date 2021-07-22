package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ColoredSolenoid;
import frc.robot.subsystems.SolenoidSubsystem;

import java.util.List;

public class SolenoidCommand extends CommandBase {

    private SolenoidSubsystem subsystem;
    private List<ColoredSolenoid> solenoids;
    private double time;
    public static double DELAY_START = 0.8;
    private double delay;

    private SolenoidCommand() {
        subsystem = SolenoidSubsystem.getInstance();
        solenoids = subsystem.getSolenoids();
        time = Timer.getFPGATimestamp();
        delay = DELAY_START;
    }

    @Override
    public void execute() {
        if (subsystem.isPressed()) {
            if (subsystem.check())
                delay -= delay / 7;
            else
                subsystem.blink(1);
        }
        double now = Timer.getFPGATimestamp();
        if (now - time >= delay) {
            subsystem.changeSolenoid();
            time = now;
        }
    }

    @Override
    public void end(boolean interrupted) {
        //@todo
    }

//    private void changeDelay() {
//        if (delay > 0.5) {
//            delay -= 0.07;
//        } else {
//            if (delay > 0.3) {
//                delay -= 0.05;
//            } else {
//                if (delay > 0.15) {
//                    delay -= 0.03;
//                } else {
//                    if (delay > 0.08) {
//                        delay -= 0.02;
//                    }
//                }
//            }
//        }
//    }
}
