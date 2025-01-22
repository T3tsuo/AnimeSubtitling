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
     * @throws IOException
     */
    public void runCommand(ProcessBuilder pBuilder, boolean outputTerminal) throws IOException, InterruptedException {
        Process process = pBuilder.start();
        if (outputTerminal) OutputTerminal(process);
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

       /* // Get the error stream of the process
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        // Read the error output of the command
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }*/
        int exitCode = process.waitFor();
        System.out.println("Process exited with code: " + exitCode);
    }
}
