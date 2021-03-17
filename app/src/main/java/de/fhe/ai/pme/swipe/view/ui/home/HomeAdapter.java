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
import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Item;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;
import de.fhe.ai.pme.swipe.view.ui.core.RecyclerViewClickListener;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    // View Holder definition for Folder
    public static class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView folderName;
        private final ImageView folderImage;

        private HomeViewHolder(View itemView) {
            super(itemView);
            this.folderName = itemView.findViewById(R.id.item_folder_card_name);
            this.folderImage = itemView.findViewById(R.id.item_folder_card_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.itemClick(v, this.getAdapterPosition());
        }
    }

    private final LayoutInflater inflater;
    private List<Folder> folderList;
    private List<Card> cardList;
    private static RecyclerViewClickListener itemListener;

    public HomeAdapter(Context context, RecyclerViewClickListener itemListener) {
        this.inflater = LayoutInflater.from(context);
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_folder_card, parent, false);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        if (this.folderList != null && !this.folderList.isEmpty()) {
            Item current = this.folderList.get(position);

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
    }
    
    public void setCards(List<Card> cards){
        this.cardList = cards;
        notifyDataSetChanged();
    }

    public void swapCards(int fromPosition, int toPosition) {
        Card fromCard = this.cardList.get(fromPosition);
        Card toCard = this.cardList.get(toPosition);

        this.cardList.set(fromPosition, toCard);
        this.cardList.set(toPosition, fromCard);
    }
    

}
