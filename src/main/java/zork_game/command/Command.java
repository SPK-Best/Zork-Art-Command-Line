package zork_game.command;

public abstract class Command {
    public String description;
    public abstract void apply();
}
