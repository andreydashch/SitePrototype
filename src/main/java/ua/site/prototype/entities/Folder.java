package ua.site.prototype.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
public class Folder {
    @Id
    @GeneratedValue
    private Integer Id;

    @Column
    private String date;

    @Column
    private Date rawDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos;

    public Folder() {
        this.rawDate = new Date();
        this.date = getCurrentDate();
        photos = new ArrayList<>();
    }

    static public String getCurrentDate() {
        final String SEPARATOR = " ";
        StringBuilder formatDate = new StringBuilder(12);

        String[] splitDate = new Date().toString().split(" ");

        formatDate.append(splitDate[2]);
        formatDate.append(SEPARATOR);
        formatDate.append(Month.valueOf(splitDate[1]).getFullName());
        formatDate.append(SEPARATOR);
        formatDate.append(splitDate[5]);

        return formatDate.toString();
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public String getDate() {
        return date;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public static Comparator<Folder> geComparator() {
        return Comparator.comparing(folder -> folder.rawDate);
    }


}
