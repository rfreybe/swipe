package de.fhe.ai.pme.swipe.view.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {

    // View Holder definition
    static class FolderViewHolder extends RecyclerView.ViewHolder {
        private final TextView folderName;
        private final ImageView folderImage;

        private FolderViewHolder(View itemView) {
            super(itemView);
            this.folderName = itemView.findViewById(R.id.item_folder_name);
            this.folderImage = itemView.findViewById(R.id.item_folder_image);
        }
    }

    private final LayoutInflater inflater;
    private List<Folder> folderList;
    private SwipeRepository repository;

    public FolderAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.repository = new SwipeRepository(context);
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_folder, parent, false);

        return new FolderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        if (this.folderList != null && !this.folderList.isEmpty()) {
            Folder current = this.folderList.get(position);

            // Set Name and Image Resource
            holder.folderName.setText(current.getName());
            holder.folderImage.setImageResource(R.drawable.ic_folder);
        }
        else {
            // Covers the case of data not being ready yet.
            holder.folderName.setText(R.string.text_empty_list);
        }
    }

    @Override
    public int getItemCount() {
        if( this.folderList != null && !this.folderList.isEmpty() )
            return this.folderList.size();
        else
            return 0;
    }

    public void setFolders(List<Folder> folders){
        this.folderList = folders;
        notifyDataSetChanged();
    }

    public void swapFolders(int fromPosition, int toPosition) {
        Folder fromFolder = this.folderList.get(fromPosition);
        Folder toFolder = this.folderList.get(toPosition);

        this.folderList.set(fromPosition, toFolder);
        this.folderList.set(toPosition, fromFolder);
        notifyDataSetChanged();
    }

}
