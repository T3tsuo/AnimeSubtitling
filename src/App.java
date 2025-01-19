import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));

        String[] encode
                = {"opt/homebrew/bin/ffmpeg", "-i", "\"Solo.Leveling.S02E14.mkv\"",
                "-codec", "copy", "-strict", "-2", "video.mp4"};
        ProcessBuilder processBuilder = new ProcessBuilder(encode);

        String[] burn
                = {"ffmpeg", "-i", "video.mp4", "-vf",
                "\"ass=Solo.Leveling.S02E14.ass\"", "-c:v", "libx264", "-crf", "13", "out.mp4"};

        Process process = processBuilder.start();
    }
}
