package niya.mohsan.youtube.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.youtube.R;
import niya.mohsan.youtube.model.Youtube;

/**
 * Created by mohsan on 22/06/16.
 */
public class VideoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvtitle) TextView tvtitle;
    @BindView(R.id.image) ImageView imageView;


    public VideoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


    public void bind(Youtube video){
        tvtitle.setText(video.getName());
        Glide.with(imageView.getContext()).load(video.getImageUrl()).centerCrop().fitCenter().into(imageView);
    }
}
