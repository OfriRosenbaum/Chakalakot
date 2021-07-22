package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;

import java.awt.*;

public class ColoredSolenoid extends Solenoid {

    private Color color;

    public ColoredSolenoid(int moduleChannel, int channel, Color color) {
        super(moduleChannel, channel);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
