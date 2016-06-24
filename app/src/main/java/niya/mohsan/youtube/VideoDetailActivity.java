package niya.mohsan.youtube;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.youtube.model.Youtube;

/**
 * Created by mohsan on 24/06/16.
 */
public class VideoDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitleVideo) TextView tvTitle;
    @BindView(R.id.youtubeImage) ImageView imageView;
    @BindView(R.id.fbFavorite) FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        ButterKnife.bind(this);


        Youtube youtube = getIntent().getExtras().getParcelable("video");

        Log.e("VideoDetailActivity", youtube.toString());


    }
}
