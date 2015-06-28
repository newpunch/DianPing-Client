package com.newpunch.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newpunch.wo.dianping_client.R;

/**
 * Created by Administrator on 2015/6/28.
 */
public class FragmentSearch extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_index, null);
        return view;
    }
}
