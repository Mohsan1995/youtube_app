package niya.mohsan.youtube.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.video.R;
import niya.mohsan.youtube.adapters.YoutubeAdapter;
import niya.mohsan.youtube.model.Video;
import niya.mohsan.youtube.network.YoutubeProvider;
import niya.mohsan.youtube.network.YoutubeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohsan on 22/06/16.
 */
public class FragmentListVideo extends Fragment {

    @BindView(R.id.recylerView) RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this,v);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        YoutubeService youtubeService = YoutubeProvider.get();

        Call<List<Video>> call = youtubeService.listYoutube();

        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                Log.e("MainActivity", response.body().get(0).getName());
                recyclerView.setAdapter(new YoutubeAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });

        return v;
    }
}
