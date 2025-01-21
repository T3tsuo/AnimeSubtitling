import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {

        //Initializes ProcessBuilder and adds first command and calls it
        ProcessBuilder processBuilder = new ProcessBuilder();
        ProcessStart process = new ProcessStart();

        //Encodes .mkv into .mp4
        processBuilder.command(TerminalCommands.ENCODE);
        process.runCommand(processBuilder, false);

        //TODO Wait for .mp4 to be  created before proceeding
        /*
        .mp4 isn't being created fast enough before trying to grab file duration or burning .ass
        need to make program wait here until first command is finished
         */

        //Grabs video duration
        processBuilder.command(TerminalCommands.GRAB_DURATION);
        process.runCommand(processBuilder, true);

        //Burns .ass onto .mp4
        processBuilder.command(TerminalCommands.BURN);
        process.runCommand(processBuilder, true);
    }
}
