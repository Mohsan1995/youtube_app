package niya.mohsan.youtube;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import niya.mohsan.video.R;
import niya.mohsan.youtube.model.Video;
import niya.mohsan.youtube.utils.AppPreferenceTools;
import niya.mohsan.youtube.utils.Message;

/**
 * Created by mohsan on 24/06/16.
 */
public class VideoDetailActivity extends AppCompatActivity {


    static final String TAG = "VideoDetailActivity";

    @BindView(R.id.main_content) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitleVideo) TextView tvTitle;
    @BindView(R.id.youtubeImage) ImageView imageView;
    @BindView(R.id.fbFavorite) FloatingActionButton floatingActionButton;

    Video video;
    AppPreferenceTools appPreferenceTools;
    Snackbar snackbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_video);
        ButterKnife.bind(this);


        this.video = getIntent().getExtras().getParcelable("video");
        this.appPreferenceTools = new AppPreferenceTools(getApplicationContext());
        assert video != null;
        this.tvDescription.setText(video.getDescription());
        this.tvTitle.setText(video.getName());
        Glide.with(this).load(video.getImageUrl()).centerCrop().fitCenter().into(imageView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VideoDetailActivity.this, MainActivity.class ));
            }
        });


        if(appPreferenceTools.getVideoFavorite(video.getId())){
            Log.e(TAG, "FAVORI : "+ video.getId());
            this.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(this,android.R.drawable.btn_star_big_on));
        }else{
            this.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(this,android.R.drawable.btn_star_big_off));
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               actionFavorite();
            }
        });

    }

    private void actionFavorite() {
        Log.e(TAG, "FAVORI : "+ video.getId());
        if(this.appPreferenceTools.getVideoFavorite(video.getId())){
            this.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(this,android.R.drawable.btn_star_big_off));
            this.appPreferenceTools.removeFavorite(video.getId());
            Message.message(snackbar,coordinatorLayout,R.string.remove_favorite);
        }else{
            this.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(this,android.R.drawable.btn_star_big_on));
            this.appPreferenceTools.saveFavorite(video.getId());
            Message.message(snackbar,coordinatorLayout,R.string.add_favourite, Color.GREEN);
        }
    }

    @OnClick(R.id.lecture)
    public void lecture(){
        Toast.makeText(VideoDetailActivity.this, video.getVideoUrl(), Toast.LENGTH_SHORT).show();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getVideoUrl()));
        startActivity(browserIntent);

    }
}
