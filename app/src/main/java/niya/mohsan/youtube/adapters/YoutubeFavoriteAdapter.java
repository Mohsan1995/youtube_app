package niya.mohsan.youtube.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import niya.mohsan.video.R;
import niya.mohsan.youtube.model.Video;
import niya.mohsan.youtube.utils.AppPreferenceTools;

/**
 * Created by mohsan on 22/06/16.
 */
public class YoutubeFavoriteAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    List<Video> videoList;
    AppPreferenceTools appPreferenceTools;


    public YoutubeFavoriteAdapter(List<Video> list){
        this.videoList = list;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_adapter, parent, false);
        appPreferenceTools = new AppPreferenceTools(view.getContext());
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        Video video = videoList.get(position);
        if(appPreferenceTools.getVideoFavorite(video.getId())){
            holder.bind(video);
        }

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
