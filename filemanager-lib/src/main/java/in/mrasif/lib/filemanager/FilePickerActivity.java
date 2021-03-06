package in.mrasif.lib.filemanager;



import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.io.File;
import java.util.Stack;

import in.mrasif.lib.filemanager.adapters.FileViewAdapter;
import in.mrasif.lib.filemanager.listeners.FileActionListener;

public class FilePickerActivity extends AppCompatActivity implements FileActionListener {

    private static final String TAG = "FilePickerActivity";
    public static final String FILE_URL="path";
    public static final String FILE_EXTENSION="extension";

    private RecyclerView rvView;
    private FileViewAdapter adapter;
    private TextView tvNoContent;
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
        tvNoContent = findViewById(R.id.tvNoContent);
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
        if (adapter.getItemCount()<1){
            tvNoContent.setVisibility(View.VISIBLE);
        }
        else {
            tvNoContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSelectFile(File file, String extension) {
        Intent intent=getIntent();
        intent.putExtra(FILE_URL,file.getAbsolutePath());
        intent.putExtra(FILE_EXTENSION,extension);
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
