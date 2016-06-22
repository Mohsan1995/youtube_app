package niya.mohsan.youtube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.youtube.model.Youtube;
import niya.mohsan.youtube.network.YoutubeProvider;
import niya.mohsan.youtube.network.YoutubeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.contentMain)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentList fragmentList = new FragmentList();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragmentList).commit();

        ButterKnife.bind(this);



    }
}
