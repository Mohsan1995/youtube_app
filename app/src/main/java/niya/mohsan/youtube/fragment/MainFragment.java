package niya.mohsan.youtube.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import niya.mohsan.youtube.R;
import niya.mohsan.youtube.adapters.ViewPagerAdapter;

/**
 * Created by mohsan on 24/06/16.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_pager,container, false );
        ButterKnife.bind(this, v);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return v;
    }
}
