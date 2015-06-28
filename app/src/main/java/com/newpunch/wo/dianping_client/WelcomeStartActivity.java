package com.newpunch.wo.dianping_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.newpunch.dianping_client.utils.ShareUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2015/6/27.
 */
public class WelcomeStartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
//        new Handler(new Handler.Callback(){
//            @Override
//            public boolean handleMessage(Message msg) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                return false;
//            }
//        }).sendEmptyMessageDelayed(0, 3000);
        Timer timer = new Timer();
        timer.schedule(new Task(), 3000);
    }
    class Task extends TimerTask{

        @Override
        public void run() {
            if(ShareUtils.getWelcomeBoolean(getBaseContext())){
                startActivity(new Intent(WelcomeStartActivity.this, MainActivity.class));
            }else {
                startActivity(new Intent(WelcomeStartActivity.this, WelcomeGuiAct.class));
                ShareUtils.putWelcomeBoolean(getBaseContext(),true);
            }
            finish();
        }
    }
}
