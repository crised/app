package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne //Owner Side (has the JOIN COLUMN)
    private Ad ad; //No tiene problemas al persistirlo debido a que no tiene @NotNull -> puede haber una imagen con un Ad no existente.

    private String description;

    private String path;

    private Boolean removed;

    public Picture() {
    }

    public String getPathThumbNail() {

        StringBuffer sb = new StringBuffer(path);
        sb.insert(sb.lastIndexOf(".jpg"), "_tn");
        return sb.toString();
    }

    public List<String> getThumbnailspaths(List<String> paths) {

        // Convert list http://192.168.1.10/14.jpg --> http://192.168.1.10/14_tn.jpg
        List<String> thumbpaths = new ArrayList<>();
        for (String path : paths) {
            if (path.endsWith(".jpg")) {
                StringBuilder sb = new StringBuilder(path);
                thumbpaths.add(sb.insert(sb.lastIndexOf(".jpg"), "_tn").toString());
            }
        }
        return thumbpaths;
    }

    public String getImagePathFromThumbnailPath(String thumbnailPath) {

        // Convert Single http://192.168.1.10/14_tn.jpg --> http://192.168.1.10/14.jpg -->
        if (thumbnailPath.endsWith(".jpg")) {
            StringBuilder sb = new StringBuilder(thumbnailPath);
            return sb.delete(sb.lastIndexOf("_tn"), sb.lastIndexOf("_tn") + 3).toString();
        } else return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }
}
