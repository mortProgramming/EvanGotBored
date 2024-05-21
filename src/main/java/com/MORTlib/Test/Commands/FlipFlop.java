package com.MORTlib.Test.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.

public class FlipFlop {

    public Command command1, command2;

    public boolean flipOrFlop;

    public FlipFlop(Command command1, Command command2, boolean flipOrFlop) {
        this.command1 = command1;
        this.command2 = command2;

        this.flipOrFlop = flipOrFlop;
    }




}