package in.mrasif.lib.filemanager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.mrasif.lib.filemanager.R;
import in.mrasif.lib.filemanager.listeners.FileActionListener;
import in.mrasif.lib.filemanager.utils.MyGlide;

public class FileViewAdapter extends RecyclerView.Adapter<FileViewAdapter.ViewHolder> {

    private Context context;
    private List<File> files;
    private FileActionListener listener;
    private List<String> imageExt;

    public FileViewAdapter(Context context) {
        this.context = context;
        this.files=new ArrayList<>();
        this.listener=(FileActionListener) context;

        imageExt=new ArrayList<String>();
        imageExt.add(".jpg");
        imageExt.add(".png");
        imageExt.add(".jpeg");
        imageExt.add(".gif");

    }

    public void update(File[] files) {
        if (null!=files) {
            this.files.clear();
            Collections.addAll(this.files, files);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_file,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File file=files.get(position);
        boolean isFile=file.isFile();
        String extension=getFileExtension(file);

        if (isFile) {
            if (imageExt.contains(extension)) {
                Glide.with(context).load(file.getAbsoluteFile()).into(holder.ivImage);
            }
            else if (".pdf".equalsIgnoreCase(extension)){
                holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf));
            }
            else {
                holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_file));
            }
        }
        else {
            holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_folder));
        }

        holder.tvTitle.setText(file.getName());
        holder.llItem.setOnClickListener(v -> {
            if (isFile){
                listener.onSelectFile(file, extension);
            }
            else {
                listener.onSelectDir(file);
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItem;
        ImageView ivImage;
        TextView tvTitle;

        public ViewHolder(@NonNull View item) {
            super(item);
            llItem=item.findViewById(R.id.llItem);
            ivImage=item.findViewById(R.id.ivImage);
            tvTitle=item.findViewById(R.id.tvTitle);
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

}
