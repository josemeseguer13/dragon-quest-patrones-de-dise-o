package com.taller.patrones.application.commands;

public interface Command {
    void execute();
    void undo();
}
