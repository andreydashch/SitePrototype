package ua.site.prototype.entities;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Photo {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    String src;

    @ElementCollection
    Set<String> tags;

    public Photo() {
        tags = new HashSet<>();
    }

    public Photo(String src, Set<String> tags) {
        this.src = src;
        this.tags = tags;
    }

    public void savePhoto(String imageBase64) throws IOException {
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageBase64);

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

        File outputFile = new File("src/main/resources/static/SavedImages/" + src + ".jpg");

        try (OutputStream os = new FileOutputStream(outputFile)) {
            ImageIO.write(img, "jpg", os);
        } catch (Exception exp) {
            throw new IOException(exp);
        }
    }

    public String getSrc() {
        return src;
    }
}
