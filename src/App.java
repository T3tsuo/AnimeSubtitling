import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));

        String[] encode
                = {"ffmpeg", "-i", "Solo.Leveling.S02E14.mkv",
                "-codec", "copy", "-strict", "-2", "video.mp4"};
        ProcessBuilder processBuilder = new ProcessBuilder(encode);

        String[] burn
                = {"ffmpeg", "-i", "video.mp4", "-vf",
                "\"ass=Solo.Leveling.S02E14.ass\"", "-c:v", "libx264", "-crf", "13", "out.mp4"};

        Process process = processBuilder.start();

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
