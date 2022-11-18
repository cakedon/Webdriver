import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.time.*;
import java.time.temporal.*;

import javax.imageio.ImageIO;
public class CodexVideo {
    public static int videoid = 0;
    public static String time;
    public static LocalDateTime foldername = LocalDateTime.now();
    public static int folderhour = foldername.getHour();
    public static int folderminute = foldername.getMinute();
    public static String foldertime = "batch_" + folderhour + "-" + folderminute;
    public static void main (String[]args) throws IOException {
        File file = new File(foldertime);
        file.mkdir();
        for(videoid = 0; videoid < 15; videoid++){
        System.out.println("[" + videoid + "] Initiated creation of video");
            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            int minute = now.getMinute();
            int second = now.getSecond();
            time = hour + "-" + minute + "-" + second;
        BufferedImage[] images = new BufferedImage[6];
        for (int i = 0; i < images.length; i++) {
            images[i] = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);
            Graphics g = images[i].getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1024, 768);
            Random r = new Random();
            for (int j = 0; j < 3; j++) {
                g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                g.fillRect(r.nextInt(1024), r.nextInt(768), r.nextInt(1024), r.nextInt(768));
            }
            System.out.println("[" + videoid + "] Created an image successfully");
        }
        for (int i = 0; i < images.length; i++) {
            ImageIO.write(images[i], "png", new File("image" + i + ".png"));
        }
        System.out.println("[" + videoid + "] Executing ffmpeg");
        String[] cmd = {"ffmpeg", "-framerate", "1/2", "-i", "image%d.png", "-c:v", "libx264", "-r", "30", "-pix_fmt", "yuv420p", foldertime + "\\video_" + videoid + "_" + time + ".mp4"};
        ProcessBuilder pb = new ProcessBuilder(cmd);
        Process p = pb.start();
    }
        System.out.println("Created " + videoid + " videos.");
    }

}