package com.newpunch.wo.dianping_client;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.newpunch.fragment.FragmentHome;
import com.newpunch.fragment.FragmentMy;
import com.newpunch.fragment.FragmentSearch;
import com.newpunch.fragment.FragmentTuan;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.main_bottom_tabs)
    private RadioGroup group;
    @ViewInject(R.id.main_home)
    private RadioButton main_home;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        fragmentManager = getSupportFragmentManager();
        main_home.setChecked(true);
        group.setOnCheckedChangeListener(this);
        changeFragment(new FragmentHome(), false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_home:
                changeFragment(new FragmentHome(),true);
                break;
            case R.id.main_my:
                changeFragment(new FragmentMy(),true);
                break;
            case R.id.main_search:
                changeFragment(new FragmentSearch(),true);
                break;
            case R.id.main_tuan:
                changeFragment(new FragmentTuan(),true);
                break;
        }

    }
    public void changeFragment(Fragment fragment, boolean isInit){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        if(!isInit){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
