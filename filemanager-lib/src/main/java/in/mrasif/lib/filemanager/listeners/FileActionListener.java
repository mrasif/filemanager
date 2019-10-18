package in.mrasif.lib.filemanager.listeners;

import java.io.File;

public interface FileActionListener {
    void onSelectFile(File file);
    void onSelectDir(File file);
}
