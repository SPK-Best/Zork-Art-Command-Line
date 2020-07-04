package zork_game.command;

public abstract class Command {

    protected String description;
    private String usage;
    private int numberOfParameters;
    private String parameter;

    /**
     * Command includes :
     * description -> What this command does
     * usage -> How to use this command
     * numberOfParameters -> Parameters for this command
     */
    public Command(String description, String usage, int numberOfParameters) {
        this.description = description;
        this.usage = usage;
        this.numberOfParameters = numberOfParameters;
    }

    public abstract boolean canUse(boolean status);

    // Error Command
    public boolean isErrorUsage(String fullCommand) {
        if (!correctUsage(fullCommand)) {
            System.out.format("USAGE: "+ usage + "\n");
            return true;
        }
        return false;
    }

    protected abstract void apply();

    public void apply(String fullCommand) {
        if(!isErrorUsage(fullCommand)) {
            if(numberOfParameters == 1) {
                parameter = fullCommand.split(" ")[1];
            }
            else {
                parameter = "";
            }
            apply();
        }
    }

    protected String getParameter() {
        return parameter;
    }


    public boolean correctUsage(String fullCommand) {
        return fullCommand.split(" ").length == numberOfParameters + 1;
    }

}
