package in.mrasif.lib.filemanager.models;

import java.io.File;

public class MyFile {
    private int id;
    private String name;
    private File file;
    private boolean isDir;

    public MyFile() {
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file=" + file +
                ", isDir=" + isDir +
                '}';
    }
}
