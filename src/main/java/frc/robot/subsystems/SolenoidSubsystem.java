package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColoredSolenoid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolenoidSubsystem extends SubsystemBase {

    private static SolenoidSubsystem instance = new SolenoidSubsystem();

    private List<ColoredSolenoid> solenoids = new ArrayList<>();
    private List<ColoredSolenoid> middle = new ArrayList<>();
    private ColoredSolenoid cur;
    private ColoredSolenoid mid;
    private DigitalInput limit;
    private Random rn = new Random();

    private SolenoidSubsystem() {
        createSolenoids();
        this.limit = new DigitalInput(0);
        cur = solenoids.get(rn.nextInt(9));
        mid = middle.get(rn.nextInt(3));
        cur.set(true);
        mid.set(true);
    }

    public void changeSolenoid() {
        int index = solenoids.indexOf(cur);
        cur.set(false);
        if (index == 8) {
            ColoredSolenoid sol = solenoids.get(0);
            sol.set(true);
            cur = sol;
        }
        ColoredSolenoid sol = solenoids.get(index + 1);
        sol.set(true);
        cur = sol;
        mid.set(false);
        mid = middle.get(rn.nextInt(3));
        mid.set(true);
    }

    private Color chooseColor(int i) {
        if (i % 3 == 0)
            return Color.RED;

        if (i % 3 == 1)
            return Color.GREEN;

        return Color.BLUE;
    }

    private void createSolenoids() {
        for (int i = 0; i < 9; i++) {
            solenoids.add(new ColoredSolenoid(i / 8, i % 8, chooseColor(i)));
        }

        for (int i = 0; i < 3; i++) {
            middle.add(new ColoredSolenoid(1, i + 1, chooseColor(i)));
        }
    }

    public void blink(int num) {
        for (int i = 0; i < num; i++) {
            turnAllOn();
            turnAllOff();
        }
    }

    private void turnAllOn() {
        solenoids.forEach(solenoid -> solenoid.set(true));
    }

    private void turnAllOff() {
        solenoids.forEach(solenoid -> solenoid.set(false));
    }

    public boolean check() {
        return mid.getColor() == cur.getColor();
    }

    public boolean isPressed() {
        return limit.get();
    }

    public static SolenoidSubsystem getInstance() {
        return instance;
    }
}
