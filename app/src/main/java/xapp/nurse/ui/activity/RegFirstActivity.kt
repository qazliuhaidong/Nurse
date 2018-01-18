package xapp.nurse.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.xnumberkeyboard.android.XNumberKeyboardView
import kotlinx.android.synthetic.main.na_activity_reg_first.*

import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity
import xapp.nurse.uitls.Constants
import xapp.nurse.uitls.ToastUtil
import java.lang.reflect.Method
import java.util.*

/**
 * Created by Dell on 2017/10/30.
 */

class RegFirstActivity : BaseActivity() {
    private var timer: RegFirstActivity.MCountDownTimer? = null
    private val TIME = 59 * 1000L
    private val INTERVAL = 1000L


    inner class MCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val time = millisUntilFinished / 1000
            if (time <= 60) {
                tv_code!!.text = String.format("重新发送%02d秒", time)
            } else {
                tv_code!!.text = String.format("重新发送%02d秒", time / 60, time % 60)
            }
        }

        override fun onFinish() {
            tv_code!!.text = "获取验证码"
            cancelTimer()
        }
    }


    fun start(view: View) {
        startTimer()
    }

    private fun startTimer() {
        if (timer == null) {
            timer = MCountDownTimer(TIME, INTERVAL)

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
        get() = R.layout.na_activity_reg_first

    override fun afterInitView() {
        setLightStatusBar()
        disableShowSoftInput(et_code)
        disableShowSoftInput(et_phone)
        tv_title.text = "用户注册"
        ib_back.setOnClickListener {
            finish()
        }

        if (intent.getIntExtra("type", 0) == 1) {
            tv_title.text = "验证"
            hint_text.text = "请输入手机验证码，以验证身份"
            tvOk.text = "确认"
        } else {
            tv_title.text = "用户注册"
            hint_text.text = "手机号作为登录账号，和找回密码的途径请填写您常用的手机号注册"
            tvOk.text = "下一步"
        }


        tv_code.setOnClickListener {
            et_code.setText("1234")
            startTimer()
        }

        keyboardView.setIOnKeyboardListener(et_phone, object : XNumberKeyboardView.IOnKeyboardListener {
            override fun onInsertKeyEvent(text: String?) {
                et_phone.append(text)
            }

            override fun onDeleteKeyEvent() {
                var start = et_phone.length() - 1
                if (start >= 0) {
                    et_phone.text.delete(start, start + 1)
                }
            }
        })


        et_phone.setOnTouchListener { view, motionEvent ->
            keyboardView.setIOnKeyboardListener(et_phone, object : XNumberKeyboardView.IOnKeyboardListener {
                override fun onDeleteKeyEvent() {
                    var start = et_phone.length() - 1
                    if (start >= 0) {
                        et_phone.text.delete(start, start + 1)
                    }
                }

                override fun onInsertKeyEvent(text: String?) {
                    et_phone.append(text)
                }

            })

            false
        }


        et_code.setOnTouchListener { view, motionEvent ->
            keyboardView.setIOnKeyboardListener(et_code, object : XNumberKeyboardView.IOnKeyboardListener {
                override fun onDeleteKeyEvent() {
                    var start = et_code.length() - 1
                    if (start >= 0) {
                        et_code.text.delete(start, start + 1)
                    }
                }

                override fun onInsertKeyEvent(text: String?) {
                    et_code.append(text)
                }

            })


            false
        }


        tvOk.setOnClickListener {
            if (TextUtils.isEmpty(et_phone.text) || !Constants.isMobileNO(et_phone.text.toString())) {
                ToastUtil.Companion.show(this, R.string.please_input_phone)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_code.text)) {
                ToastUtil.Companion.show(this, R.string.please_input_code)
                return@setOnClickListener
            }
            var xBundle= Bundle();
            xBundle.putString("mobile",et_phone.text.toString());
            warpActivity(RegSecondActivity::class.java, false,xBundle)
        }
    }


}
