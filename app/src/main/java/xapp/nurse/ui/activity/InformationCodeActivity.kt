package xapp.nurse.ui.activity
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.na_activity_information_code.*

import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity

class InformationCodeActivity : BaseActivity() {


    private var textView: TextView? = null
    private var timer:  InformationCodeActivity.MCountDownTimer? = null
    private val TIME = 60 * 1000L
    private val INTERVAL = 1000L

    inner class MCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val time = millisUntilFinished / 1000
            if (time <= 59) {
                textView!!.text = String.format("重新发送%02d秒", time)
            } else {
                textView!!.text = String.format("重新发送%02d秒", time / 60, time % 60)
            }
        }

        override fun onFinish() {
            textView!!.text = "获取验证码"
            cancelTimer()
        }
    }


    fun start(view: View) {
        startTimer()
    }

    private fun startTimer() {
        if (timer == null) {
            timer=MCountDownTimer(TIME,INTERVAL)

        }
        timer!!.start()
    }


    private fun cancelTimer() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelTimer()
    }

    override val layoutId: Int
        get() = R.layout.na_activity_information_code

    override fun afterInitView() {
        setLightStatusBar()
        textView = findViewById(R.id.time) as TextView
        time.setOnClickListener {
            startTimer()
        }

//        startTimer()
        tv_title.text = "验证信息"
        ib_back.setOnClickListener { finish() }
    }


}
