package in.mrasif.lib.filemanager.listeners;

import java.io.File;

public interface FileActionListener {
    void onSelectFile(File file, String extension);
    void onSelectDir(File file);
}
