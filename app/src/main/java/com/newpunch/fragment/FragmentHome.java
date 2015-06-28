package com.newpunch.fragment;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.newpunch.dianping_client.utils.ShareUtils;
import com.newpunch.wo.dianping_client.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/6/28.
 */
public class FragmentHome extends Fragment implements LocationListener{
    @ViewInject(R.id.index_top_city)
    private TextView topCity;
    private String cityName;
    private LocationManager locationManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_index, null);
        ViewUtils.inject(this, view);
        topCity.setText(ShareUtils.getCityName(getActivity()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        checkGPSIsOpen();
    }

    private void checkGPSIsOpen() {
        //获取当前的LocationManager对象
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isOpen = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        if(isOpen){
            //进去GPS设置页面
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent,0);
        }
        //开始定位
        startLocation();
    }

    private void startLocation() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 1){
                topCity.setText(cityName);
            }
            return false;
        }
    });

    private void updateWithNewLocation(Location location) {
        double lat = 0.0, lng = 0.0;
        if(location != null){
            lat = location.getLatitude();
            lng = location.getLongitude();
        }else {
            cityName = "无法获取城市信息";
        }
        //通过经纬度获取城市地址，地址会有很多，这个和经纬度精确度有关
        List<Address> list = null;
        Geocoder ge = new Geocoder(getActivity());
        try {
           list =  ge.getFromLocation(lat, lng, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list!=null && list.size()>0){
            for(int i = 0; i < list.size(); i++){
                Address ad = list.get(i);
                cityName = ad.getLocality();
            }
        }
        handler.sendEmptyMessage(1);
    }
    @Override
    public void onLocationChanged(Location location) {
        updateWithNewLocation(location);
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //保存城市
        ShareUtils.putCityName(getActivity(), cityName);
        //停止定位
        stopLocation();
    }

    private void stopLocation() {
        locationManager.removeUpdates(this);
    }
}
