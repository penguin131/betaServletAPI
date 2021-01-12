package edu.school21.cinema.services;

import edu.school21.cinema.config.CinemaConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class ImageFileService {

    @Autowired
    private CinemaConfig cinemaConfig;

    public void saveImage(long imageId, Part part) throws IOException {
        File save = new File(cinemaConfig.getFileStoragePath(), String.valueOf(imageId));
        if (!save.createNewFile()) {
            throw new IOException("File already exists!");
        }
        final String absolutePath = save.getAbsolutePath();
        part.write(absolutePath);
    }

    public File getImageFile(String imageId) {
        try {
            File result = new File(cinemaConfig.getFileStoragePath() + "/" + imageId);
            if (result.exists()) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
