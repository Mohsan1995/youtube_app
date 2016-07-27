package niya.mohsan.youtube.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.video.R;
import niya.mohsan.youtube.adapters.YoutubeAdapter;
import niya.mohsan.youtube.model.Video;
import niya.mohsan.youtube.network.YoutubeProvider;
import niya.mohsan.youtube.network.YoutubeService;
import niya.mohsan.youtube.utils.AppPreferenceTools;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohsan on 22/06/16.
 */
public class FragmentListVideoFavorite extends Fragment {

    @BindView(R.id.recylerView) RecyclerView recyclerView;
    private AppPreferenceTools appPreferenceTools;
    private YoutubeAdapter youtubeAdapter;
    private List<Video> listVideoFavorite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this,v);

        this.appPreferenceTools = new AppPreferenceTools(getContext());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.listVideoFavorite = new ArrayList<>();

        YoutubeService youtubeService = YoutubeProvider.get();
        Call<List<Video>> call = youtubeService.listYoutube();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                initFavoriteVideo(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });

        this.youtubeAdapter = new YoutubeAdapter(listVideoFavorite);
        this.recyclerView.setAdapter(youtubeAdapter);

        return v;
    }

    private void initFavoriteVideo(List<Video> listVideo) {
        for (Video video: listVideo){
            if(this.appPreferenceTools.getVideoFavorite(video.getId())){
                this.listVideoFavorite.add(video);
            }
        }
        this.youtubeAdapter.notifyDataSetChanged();
    }



}
