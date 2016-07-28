package niya.mohsan.youtube.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import niya.mohsan.video.R;
import niya.mohsan.youtube.controller.RealmController;
import niya.mohsan.youtube.model.Video;
import niya.mohsan.youtube.utils.AppPreferenceTools;
import niya.mohsan.youtube.utils.Message;

/**
 * Created by mohsan on 24/06/16.
 */
public class VideoDetailActivity extends AppCompatActivity {


    static final String TAG = "VideoDetailActivity";

    @BindView(R.id.main_content) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitleVideo) TextView tvTitle;
    @BindView(R.id.youtubeImage) ImageView imageView;
    @BindView(R.id.fbFavorite) FloatingActionButton floatingActionButton;

    Video video;
    AppPreferenceTools appPreferenceTools;
    Snackbar snackbar;
    Realm realm;
    ActionBar actionBar;

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
        this.realm = RealmController.getInstance(this).getRealm();

        Glide.with(this).load(video.getImageUrl()).centerCrop().fitCenter().into(imageView);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle(video.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VideoDetailActivity.this, MainActivity.class ));
            }
        });


        if(appPreferenceTools.getVideoFavorite(video.getId())){
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

        this.realm.beginTransaction();
        this.realm.copyToRealm(video);
        this.realm.commitTransaction();

        Toast.makeText(VideoDetailActivity.this, video.getVideoUrl(), Toast.LENGTH_SHORT).show();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getVideoUrl()));
        /*Intent intent =new Intent(this, VideoPlayerActivity.class);
        intent.putExtra("url",getYouTubeVideoId(video.getVideoUrl()));*/
        startActivity(browserIntent);

    }


    public static String getYouTubeVideoId(String video_url) {

        if (video_url != null && video_url.length() > 0) {

            Uri video_uri = Uri.parse(video_url);
            String video_id = video_uri.getQueryParameter("v");

            if (video_id == null)
                video_id = parseYoutubeVideoId(video_url);

            return video_id;
        }
        return null;
    }


    public static String parseYoutubeVideoId(String youtubeUrl)
    {
        String video_id = null;
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 &&
                youtubeUrl.startsWith("http"))
        {
            // ^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/
            String expression = "^.*((youtu.be" + "\\/)"
                    + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*";
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches())
            {
                // Regular expression some how doesn't work with id with "v" at
                // prefix
                String groupIndex1 = matcher.group(7);
                if (groupIndex1 != null && groupIndex1.length() == 11)
                    video_id = groupIndex1;
                else if (groupIndex1 != null && groupIndex1.length() == 10)
                    video_id = "v" + groupIndex1;
            }
        }
        return video_id;
    }
}
