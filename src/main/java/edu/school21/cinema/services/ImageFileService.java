package edu.school21.cinema.services;

import edu.school21.cinema.config.CinemaConfig;
import edu.school21.cinema.models.Image;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ImageFileService {

    @Autowired
    private CinemaConfig cinemaConfig;

    public void saveImage(long imageId, Part part) throws IOException {
        File save = new File(cinemaConfig.getFileStoragePath(), String.valueOf(imageId));
        try {
            save.createNewFile();
        } catch (IOException e) {
            throw new IOException("Images folder not exists!!!");
        }
        final String absolutePath = save.getAbsolutePath();
        part.write(absolutePath);
    }

    public byte[] getImage(Image image) {
        try {
            File imageFile = new File(cinemaConfig.getFileStoragePath() + "/" + image.getName());
            BufferedImage bufferedImage = ImageIO.read(imageFile);

            return ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
        } catch (Exception e) {
            return null;
        }
    }
}
