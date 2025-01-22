/**
 * Saves String commands for terminal calls
 */
public class TerminalCommands {

    public static final String[] ENCODE = {"ffmpeg", "-i", "Solo.Leveling.S02E14.mkv",
            "-codec", "copy", "-strict", "-2", "video.mp4"};
    public static final String[] BURN = {"ffmpeg", "-i", "video.mp4", "-vf",
            "ass=Solo.Leveling.S02E14.ass", "-c:v", "libx264", "-crf", "13", "out.mp4"};
    public static final String[] GRAB_DURATION = {"ffprobe", "-i", "video.mp4", "-show_entries", "format=duration",
            "-v", "quiet", "-of", "csv=p=0"};

}
