package xapp.nurse.ui.activity

import android.content.Context
import android.content.Intent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.xnumberkeyboard.android.XNumberKeyboardView

import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity
import xapp.nurse.uitls.visible
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import kotlinx.android.synthetic.main.na_activity_withdrawal.*


class WithDrawActivity : BaseActivity(), XNumberKeyboardView.IOnKeyboardListener {
    override fun onInsertKeyEvent(text: String?) {
        et_withdraw!!.append(text)
    }

    override fun onDeleteKeyEvent() {
        val start=et_withdraw.length()-1
        if(start>=0){
            et_withdraw.text.delete(start,start+1)
        }
    }


    override val layoutId: Int
        get() = R.layout.na_activity_withdrawal




    override fun afterInitView() {
        setLightStatusBar()
        disableShowSoftInput(et_withdraw)
        ib_back.setOnClickListener { finish() }
        tv_title.text="提现"
        iv_right.visible()
        iv_right.setImageResource(R.mipmap.topbar_date)
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(et_withdraw, 0)
        et_withdraw.inputType=EditorInfo.TYPE_CLASS_PHONE
        tvOk.setOnClickListener {
             var intent=Intent(this,RegFirstActivity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)
        }
        iv_right.setOnClickListener {
            warpActivity(WithDrawCashActivity::class.java)
        }
        keyboardView?.setIOnKeyboardListener(et_withdraw,this)
        wx.setOnClickListener {
            alertShow()
        }

    }


    fun alertShow(){
    AlertView(null, null, "取消", null,
            arrayOf("支付宝账号", "微信账号"),
            this, AlertView.Style.ActionSheet, OnItemClickListener { o, position ->

        if (position == 0) {
           showDialog("你选择了支付宝")
        } else if (position == 1) {
            showDialog("你选择了微信")
        }
    }).show()


}





}
