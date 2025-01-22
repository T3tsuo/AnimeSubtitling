import java.io.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Initializes ProcessBuilder and adds first command and calls it
        ProcessBuilder processBuilder = new ProcessBuilder();
        ProcessStart process = new ProcessStart();

        processBuilder.redirectErrorStream(true);

        //Encodes .mkv into .mp4
        processBuilder.command(TerminalCommands.ENCODE);
        process.runCommand(processBuilder, true);

        //Grabs video duration
        processBuilder.command(TerminalCommands.GRAB_DURATION);
        process.runCommand(processBuilder, true);

        //Burns .ass onto .mp4
        processBuilder.command(TerminalCommands.BURN);
        process.runCommand(processBuilder, true);
    }
}
