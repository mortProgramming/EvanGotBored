package com.MORTlib.commands;

import static com.MORTlib.others.Ternary.*;

import java.util.function.BooleanSupplier;

import com.MORTlib.others.Ternary;

import edu.wpi.first.wpilibj2.command.Command;

public class FlipFlapOrFlop {

    public Ternary currentCommand;
    public boolean lastPress;

    public FlipFlapOrFlop() {
        currentCommand = Null;
        lastPress = false;
    }

    public Command FlipFlapFlop(Command defaultCommand, Command secondCommand, Command thirdCommand, BooleanSupplier input) {
        boolean pressed = !lastPress && input.getAsBoolean();
        if (pressed && currentCommand == Null) {
            currentCommand = False;
            lastPress = input.getAsBoolean();
            return secondCommand;
        }

        else if (pressed && currentCommand == False) {
            currentCommand = True;
            lastPress = input.getAsBoolean();
            return thirdCommand;
        }

        else if (pressed) {
            currentCommand = Null;
            lastPress = input.getAsBoolean();
            return defaultCommand;
        }

        else if (currentCommand == Null) {
            lastPress = input.getAsBoolean();
            return defaultCommand;
        }

        else if (currentCommand == False) {
            lastPress = input.getAsBoolean();
            return secondCommand;
        }

        lastPress = input.getAsBoolean();
        return thirdCommand;
    }
}
