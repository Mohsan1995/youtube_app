package niya.mohsan.youtube.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import niya.mohsan.youtube.R;
import niya.mohsan.youtube.model.Youtube;

/**
 * Created by mohsan on 22/06/16.
 */
public class YoutubeAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    List<Youtube> youtubeList;


    public YoutubeAdapter(List<Youtube> list){
        this.youtubeList = list;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_adapter, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        Youtube youtube = youtubeList.get(position);
        holder.bind(youtube);

    }

    @Override
    public int getItemCount() {
        return youtubeList.size();
    }
}
