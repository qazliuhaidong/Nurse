package xapp.nurse.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import xapp.nurse.R;

/**
 * Created by Administrator on 2017-12-27.
 */

public class TesActivity extends AppCompatActivity{

    private TextView textView;
//    private MyCountDownTimer timer;
    private final long TIME = 60 * 1000L;
    private final long INTERVAL = 1000L;

//    public class MyCountDownTimer extends CountDownTimer {
//        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            long time = millisUntilFinished / 1000;
//
//            if (time <= 59) {
//                textView.setText(String.format("重新发送%02d秒", time));
//            } else {
//                textView.setText(String.format("重新发送%02d秒", time / 60, time % 60));
//            }
//        }
//
//        @Override
//        public void onFinish() {
//            textView.setText("获取验证码");
//            cancelTimer();
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        textView = (TextView) findViewById(R.id.tv);
//        startTimer();
//    }
//
//    public void start(View view) {
//        startTimer();
//    }
//
//
//
//    /**
//     * 开始倒计时
//     */
//    private void startTimer() {
//        if (timer == null) {
//            timer = new MyCountDownTimer(TIME, INTERVAL);
//        }
//        timer.start();
//    }
//
//    /**
//     * 取消倒计时
//     */
//    private void cancelTimer() {
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        cancelTimer();
//    }


}
