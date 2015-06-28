package com.newpunch.wo.dianping_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public class WelcomeGuiAct extends Activity {
    @ViewInject(R.id.welcome_guide_btn)
    private Button btn;
    @ViewInject(R.id.welcome_pager)
    private ViewPager pager;
    private List<View> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_gui);
        ViewUtils.inject(this);
        initViewpager();
    }
    @OnClick(R.id.welcome_guide_btn)
    public void click(View view){
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }

    public void initViewpager(){
        list = new ArrayList<View>();
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.guide_01);
        list.add(iv);
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.guide_02);
        list.add(iv1);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.guide_03);
        list.add(iv2);
        pager.setAdapter(new MyPagerAdapter());

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    btn.setVisibility(View.VISIBLE);
                }else {
                    btn.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(list.get(position));
        }
    }
}
