package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SolenoidSubsystem;

public class SolenoidCommand extends CommandBase {

    private SolenoidSubsystem subsystem;
    private double time;
    public static double DELAY_START = 0.8;
    private double delay;

    private SolenoidCommand() {
        subsystem = SolenoidSubsystem.getInstance();
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
}
