package niya.mohsan.youtube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @BindView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        textView.setText("JZIJAIJDIAZ");

        YoutubeService youtubeService = YoutubeProvider.get();

        Call<List<Youtube>> call = youtubeService.listYoutube();

        call.enqueue(new Callback<List<Youtube>>() {
            @Override
            public void onResponse(Call<List<Youtube>> call, Response<List<Youtube>> response) {
                Log.e("MainActivity", response.body().get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Youtube>> call, Throwable t) {

            }
        });
    }
}
