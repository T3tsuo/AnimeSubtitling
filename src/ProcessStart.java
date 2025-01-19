import java.io.*;

public class ProcessStart {

    public ProcessStart (){}

    public void runCommand(ProcessBuilder pBuilder) throws IOException{
        Process process = pBuilder.start();

        // Get the input stream of the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Read the output of the command
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Get the error stream of the process
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        // Read the error output of the command
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }
    }

}
