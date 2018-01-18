package xapp.nurse.ui.activity

import android.text.InputType
import android.text.TextUtils
import android.widget.EditText
import com.xnumberkeyboard.android.XNumberKeyboardView
import kotlinx.android.synthetic.main.na_activity_reg_first.*
import kotlinx.android.synthetic.main.na_activity_reg_second.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.R.style.editText_style
import xapp.nurse.base.BaseActivity
import xapp.nurse.uitls.ToastUtil
import java.lang.reflect.Method

/**
 * Created by Dell on 2017/10/30.
 */

class RegSecondActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_reg_second

    override fun afterInitView() {
        setLightStatusBar()
//        disableShowSoftInput(et_code1)
//        disableShowSoftInput(et_code2)
        tv_title.text = "设置密码"
        ib_back.setOnClickListener { finish() }
        tvOk2.setOnClickListener {
            if (TextUtils.isEmpty(et_code1.text) || TextUtils.isEmpty(et_code2.text)) {
                ToastUtil.show(this, R.string.please_input_password)
                return@setOnClickListener
            }
            if (et_code1.text.length < 6) {
                ToastUtil.show(this, R.string.passs_not_less_six)
                return@setOnClickListener
            }
            if (!et_code1.text.toString().equals(et_code2.text.toString())) {
                ToastUtil.show(this, R.string.two_passwords_are_diff)
                return@setOnClickListener
            }
            var xBundle = intent.getBundleExtra("bundle")
            xBundle.putString("password", et_code1.text.toString())
            warpActivity(RegThirdActivity::class.java, true, xBundle)
        }
//        et_code1.setOnTouchListener { view, motionEvent ->
//            keyboardView2.setIOnKeyboardListener(et_code1,object :XNumberKeyboardView.IOnKeyboardListener{
//                override fun onInsertKeyEvent(text: String?) {
//                    et_code1.append(text)
//                }
//
//                override fun onDeleteKeyEvent() {
//                    var start=et_code1.length()-1
//                    if(start>=0){
//                        et_code1.text.delete(start,start+1)
//                    }
//                }
//
//            })
//            false
//        }
//
//        et_code2.setOnTouchListener { view, motionEvent ->
//            keyboardView2.setIOnKeyboardListener(et_code2,object :XNumberKeyboardView.IOnKeyboardListener{
//                override fun onInsertKeyEvent(text: String?) {
//                    et_code2.append(text)
//                }
//
//                override fun onDeleteKeyEvent() {
//                    var start=et_code2.length()-1
//                    if(start>=0){
//                        et_code2.text.delete(start,start+1)
//                    }
//                }
//
//            })
//
//
//            false
//        }


//
//        keyboardView2.setIOnKeyboardListener(et_code1,object :XNumberKeyboardView.IOnKeyboardListener{
//            override fun onInsertKeyEvent(text: String?) {
//                et_code1.append(text)
//            }
//
//            override fun onDeleteKeyEvent() {
//                var start=et_code1.length()-1
//                if(start>=0){
//                    et_code1.text.delete(start,start+1)
//                }
//            }
//
//        })


    }

}
