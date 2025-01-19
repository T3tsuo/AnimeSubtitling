import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {

        //Command structure for encoding .mkv into .mp4
        String[] encode
                = {"ffmpeg", "-i", "Solo.Leveling.S02E14.mkv",
                "-codec", "copy", "-strict", "-2", "video.mp4"};
        //Command structure for burning .ass onto .mp4
        String[] burn
                = {"ffmpeg", "-i", "video.mp4", "-vf",
                "ass=Solo.Leveling.S02E14.ass", "-c:v", "libx264", "-crf", "13", "out.mp4"};

        //Initializes ProcessBuilder and adds first command and calls it
        ProcessBuilder processBuilder = new ProcessBuilder();
        ProcessStart process = new ProcessStart();
        processBuilder.command(encode);
        process.runCommand(processBuilder);

        //Encodes second command and calls it
        processBuilder.command(burn);
        process.runCommand(processBuilder);
    }
}
