import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessStart {
    private boolean grabDuration;
    public double duration;
    public double currentPct = 0;
    public int counterCap = 7;
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
    public void runCommand(ProcessBuilder pBuilder, boolean outputTerminal, String cmdTag, boolean grabDuration) throws IOException, InterruptedException {
        this.grabDuration = grabDuration;
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
        int counter = 0;
        while ((line = reader.readLine()) != null) {
            if(grabDuration){
                duration = Double.parseDouble(line);
            } else if (line.contains("frame=") && counter >= counterCap) {
                counter = 0;
                double temp = pctCalculator(matchBurn(line));
                if (currentPct < temp) {
                    currentPct = temp;
                    System.out.printf("%.2f%%\n", currentPct);
                }
            }
            counter++;
        }
    }

    /**
     * Uses regex to filter for duration in processing
     * @param inputString command line string we want to parse
     * @return parsed string in format xx:xx:xx.xx
     */
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

    /**
     * Converts parsed time value from xx:xx:xx.xx into decimal seconds
     * @param durationString String to be parsed
     * @return Resulting time conversion
     */
    public double convertToSeconds(String durationString) {
        String[] timeArray = durationString.split(":");

        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        double seconds = Double.parseDouble(timeArray[2]);

        return hours * 3600 + minutes * 60 + seconds;
    }

    /**
     * Caluclates loading percentage
     * @param currentTime current time progressed in seconds
     * @return Percentage
     */
    public double pctCalculator(double currentTime){
        return (currentTime/duration) * 100;
    }
    public void setCounterCap(int counterCap){
        this.counterCap = counterCap;
    }
}
