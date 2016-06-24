package niya.mohsan.youtube.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import niya.mohsan.youtube.fragment.FragmentList;

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
            fragment= new FragmentList();
        }else if(position==1){
            fragment = new FragmentList();
        }else if(position==2){
            fragment= new FragmentList();
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
