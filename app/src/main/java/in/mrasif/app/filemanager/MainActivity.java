package in.mrasif.app.filemanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import in.mrasif.lib.filemanager.FilePickerActivity;

public class MainActivity extends AppCompatActivity {

    private int FILE_PICKER_REQUEST_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivityForResult(new Intent(MainActivity.this, FilePickerActivity.class),FILE_PICKER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==FILE_PICKER_REQUEST_CODE){
            if (resultCode==RESULT_OK){
                System.out.println("FILE PATH: " + data.getStringExtra(FilePickerActivity.FILE_URL));
            }
        }
    }
}
