package ua.site.prototype.entities;

import javax.persistence.*;

import java.util.*;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer photoCounter;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Folder> dates;

    public UserEntity() {
        photoCounter = 0;
        dates = new ArrayList<>();
    }

    public UserEntity(String name) {
        photoCounter = 0;
        this.name = name;
        dates = new ArrayList<>();
    }

    public void addPhoto(Photo photo) {
        Folder currentFolder = getCurrentFolder();
        currentFolder.addPhoto(photo);
    }

    private Folder getCurrentFolder() {
        if (dates.size() == 0) {
            dates.add(new Folder());
        }

        Folder currentFolder = dates.get(dates.size() - 1);

        if (!currentFolder.getDate().equals(Folder.getCurrentDate())) {
            currentFolder = new Folder();
            dates.add(currentFolder);
        }

        return currentFolder;
    }

    public Photo createPhoto(String imageBase64) {
        int firstImage = getPhotoCounter() + 1;

        Set<String> tags = new HashSet<>();
        tags.add("test_album");

        Photo photo = new Photo(Integer.toString(firstImage), tags);
        try {
            photo.savePhoto(imageBase64);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        photoCounter++;

        return photo;
    }

    public void sortFolders() {
        dates.sort(Folder.geComparator());
    }

    public String getCurrentDate() {
        return Folder.getCurrentDate();
    }

    public List<Folder> getDates() { return dates; }

    public Integer getPhotoCounter() {
        return photoCounter;
    }
}
