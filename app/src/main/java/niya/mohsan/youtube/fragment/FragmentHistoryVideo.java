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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import niya.mohsan.video.R;
import niya.mohsan.youtube.adapters.YoutubeAdapter;
import niya.mohsan.youtube.controller.RealmController;
import niya.mohsan.youtube.model.Video;

/**
 * Created by mohsan on 27/07/16.
 */
public class FragmentHistoryVideo extends Fragment {

    @BindView(R.id.recylerView) RecyclerView recyclerView;
    private List<Video> listVideoHistory = new ArrayList<>();
    private YoutubeAdapter youtubeAdapter;
    private RealmController realmController;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this,v);
        realmController = RealmController.getInstance(getContext());
        realm = realmController.getRealm();
        RealmResults<Video> video = realmController.getVideoHistory();

        videoToList(video);

        youtubeAdapter = new YoutubeAdapter(listVideoHistory);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(youtubeAdapter);


        return v;
    }

    private void videoToList(RealmResults<Video> video) {
        for (int i = 0; i < video.size(); i++) {
            listVideoHistory.add(video.get(i));
        }

    }

    


}
