import java.io.*;

public class ProcessStart {

    /**
     * Basic Constructor
     */
    public ProcessStart (){}

    /**
     * Runs the passed ProcessBuilder command, and prints output to terminal if needed
     * @param pBuilder ProcessBuilder with encoded command
     * @param outputTerminal flag for if we need the terminal output or no
     * @param cmdTag Tag for what command is being run, output with exit code
     * @throws IOException
     */
    public void runCommand(ProcessBuilder pBuilder, boolean outputTerminal, String cmdTag) throws IOException, InterruptedException {
        Process process = pBuilder.start();
        if (outputTerminal) OutputTerminal(process);

        int exitCode = process.waitFor();
        System.out.println(cmdTag + ": Process exited with code - " + exitCode);
    }

    /**
     * Outputs passed Process to the terminal
     * @param process Process to output
     * @throws IOException
     */
    public void OutputTerminal(Process process) throws IOException, InterruptedException {

        // Get the input stream of the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Read the output of the command
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
