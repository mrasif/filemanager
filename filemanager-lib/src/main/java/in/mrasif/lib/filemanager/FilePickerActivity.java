package in.mrasif.lib.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.io.File;
import java.util.Arrays;
import java.util.Stack;

import in.mrasif.lib.filemanager.adapters.FileViewAdapter;
import in.mrasif.lib.filemanager.listeners.FileActionListener;

public class FilePickerActivity extends AppCompatActivity implements FileActionListener {

    private static final String TAG = "FilePickerActivity";
    public static final String FILE_URL="data";

    private RecyclerView rvView;
    private FileViewAdapter adapter;
    private Stack<File> fileStack=new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setUI();
        init();
    }

    private void setUI() {
        rvView = findViewById(R.id.rvViews);
    }

    private void init() {
        fileStack.push(Environment.getExternalStorageDirectory());
        System.out.println("Stack Size: "+fileStack.size());
        adapter=new FileViewAdapter(this);
        FlexboxLayoutManager flm = new FlexboxLayoutManager(this);
        flm.setFlexDirection(FlexDirection.ROW);
        flm.setJustifyContent(JustifyContent.FLEX_START);
        rvView.setLayoutManager(flm);
        rvView.setAdapter(adapter);
        loadDirs();
    }

    private void loadDirs() {
        File dir=fileStack.peek();
        System.out.println("==================PEEK==================");
        System.out.println(dir.getAbsolutePath());
        System.out.println("========================================");
        File[] files = dir.listFiles();
        adapter.update(files);
    }

    @Override
    public void onSelectFile(File file) {
        Intent intent=getIntent();
        intent.putExtra(FILE_URL,file.getAbsolutePath());
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onSelectDir(File file) {
        fileStack.push(file);
        loadDirs();
    }

    @Override
    public void onBackPressed() {
        if (fileStack.size()==1) {
            super.onBackPressed();
        }
        else {
            fileStack.pop();
            loadDirs();
        }
    }
}
