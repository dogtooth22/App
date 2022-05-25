package com.grimlin31.buddywalkowner.Controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter{
    int numoftabs;

    public PagerController(@NonNull FragmentManager fm, int behaviour){
        super(fm,behaviour);
        this.numoftabs = behaviour;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Basicos();
            case 1:
                return new Salud();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
