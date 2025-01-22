import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if (line.contains("frame=")) {
                System.out.println(matchBurn(line));
            }
        }
    }


    public double matchBurn(String inputString) {
        String regex = "\\d{2}:\\d{2}:\\d{2}.\\d{2}"; // Matches one or more digits
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        // Find the first match
        if (matcher.find()) {
            return convertToSeconds(matcher.group(0));
        } else {
            return 0;
        }
    }

    public double convertToSeconds(String durationString) {
        String[] timeArray = durationString.split(":");

        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        Double seconds = Double.parseDouble(timeArray[2]);

        return hours * 3600 + minutes * 60 + seconds;
    }
}
