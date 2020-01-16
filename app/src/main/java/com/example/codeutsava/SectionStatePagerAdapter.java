package com.example.codeutsava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment,Integer> mFragments = new HashMap<>();
    private final HashMap<String,Integer> mFragmentNumbers = new HashMap<>();
    private final HashMap<Integer,String> mFragmentName = new HashMap<>();


    public SectionStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName){
        mFragmentList.add(fragment);
        mFragments.put(fragment,mFragmentList.size()-1);
        mFragmentNumbers.put(fragmentName,mFragmentList.size()-1);
        mFragmentName.put(mFragmentList.size()-1 , fragmentName);
    }

    public Integer getFragmentNumber(String fragmentName){
        if(mFragmentNumbers.containsKey(fragmentName)){
            return mFragmentNumbers.get(fragmentName);
        }
        else{
            return null;
        }
    }

    public Integer getFragmentNumber(Fragment fragment){
        if(mFragmentNumbers.containsKey(fragment)){
            return mFragmentNumbers.get(fragment);
        }
        else{
            return null;
        }
    }

    public Integer getFragmentName(Integer fragmentNumber){
        if(mFragmentNumbers.containsKey(fragmentNumber)){
            return mFragmentNumbers.get(fragmentNumber);
        }
        else{
            return null;
        }
    }

}


