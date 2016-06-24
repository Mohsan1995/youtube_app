package niya.mohsan.youtube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.video.R;
import niya.mohsan.youtube.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.contentMain)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, new MainFragment()).commit();

        ButterKnife.bind(this);



    }
}
