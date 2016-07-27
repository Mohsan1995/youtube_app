package niya.mohsan.youtube.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import niya.mohsan.video.R;
import niya.mohsan.youtube.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.contentMain) FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, new MainFragment()).commit();

    }
}
