package niya.mohsan.youtube;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.video.R;
import niya.mohsan.youtube.model.Video;

/**
 * Created by mohsan on 24/06/16.
 */
public class VideoDetailActivity extends AppCompatActivity {


    @BindView(R.id.main_content) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitleVideo) TextView tvTitle;
    @BindView(R.id.youtubeImage) ImageView imageView;
    @BindView(R.id.fbFavorite) FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_video);
        ButterKnife.bind(this);


        Video video = getIntent().getExtras().getParcelable("video");

        assert video != null;
        tvDescription.setText(video.getDescription());
        tvTitle.setText(video.getName());
        Glide.with(this).load(video.getImageUrl()).centerCrop().fitCenter().into(imageView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout,"Go Bask", Snackbar.LENGTH_SHORT).show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout,"Video add to favourite", Snackbar.LENGTH_SHORT).show();
            }
        });




    }
}
