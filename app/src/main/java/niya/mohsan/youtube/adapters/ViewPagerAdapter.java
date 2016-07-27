package niya.mohsan.youtube.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import niya.mohsan.youtube.fragment.FragmentListVideo;
import niya.mohsan.youtube.fragment.FragmentListVideoFavorite;

/**
 * Created by mohsan on 24/06/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment= new Fragment();

        if(position==0){
            fragment= new FragmentListVideo();
        }else if(position==1){
            fragment = new FragmentListVideoFavorite();
        }else if(position==2){
            fragment= new FragmentListVideo();
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String name="";
        if(position==0){
            name ="Liste vidéo";
        }else if(position==1){
            name= "Favoris";
        }else if(position==2){
            name= "Historique";
        }

        return name;

    }
}
