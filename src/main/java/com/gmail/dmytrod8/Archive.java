package com.gmail.dmytrod8;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Archives")
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "filesinside", nullable = false)
    //check name mysql
    private String filesInside;
    @Column(name = "path", nullable = false)
    private String path;

    public Archive() {
    }

    public Archive(String name, String filesInside, String path) {
        this.name = name;
        this.filesInside = filesInside;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilesInside() {
        return filesInside;
    }

    public void setFilesInside(String filesInside) {
        this.filesInside = filesInside;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filesInside='" + filesInside + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
