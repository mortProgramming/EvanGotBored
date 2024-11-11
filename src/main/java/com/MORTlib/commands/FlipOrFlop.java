package com.MORTlib.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;

public class FlipOrFlop {

    public boolean currentCommand;
    public boolean lastPress;

    public FlipOrFlop() {
        currentCommand = false;
        lastPress = false;
    }

    public Command FlipFlop(Command defaultCommand, Command otherCommand, BooleanSupplier input) {
        boolean pressed = !lastPress && input.getAsBoolean();
        if (pressed && currentCommand) {
            currentCommand = false;
            lastPress = input.getAsBoolean();
            return defaultCommand;
        }

        else if (pressed) {
            currentCommand = true;
            lastPress = input.getAsBoolean();
            return otherCommand;
        }

        else if (currentCommand) {
            lastPress = input.getAsBoolean();
            return otherCommand;
        }

        lastPress = input.getAsBoolean();
        return defaultCommand;
    }
}
