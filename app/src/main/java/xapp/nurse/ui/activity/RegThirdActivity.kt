package xapp.nurse.ui.activity

import android.content.Intent
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.bumptech.glide.load.engine.Resource
import kotlinx.android.synthetic.main.na_activity_reg_first.*
import kotlinx.android.synthetic.main.na_activity_reg_third.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.Login
import xapp.nurse.ui.model.RegisterObject
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.SharedUtils
import xapp.nurse.uitls.ToastUtil

/**
 * Created by Dell on 2017/10/30.
 */

class RegThirdActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_reg_third

    override fun afterInitView() {
        setLightStatusBar()
        tv_title.text = "填写用户信息"
        ib_back.setOnClickListener {
            warpActivity(RegSecondActivity::class.java, true)
        }

        tv_Ok.setOnClickListener {
            var xBundle = intent.getBundleExtra("bundle")
            var mobile: String = xBundle.getString("mobile");
            var password: String = xBundle.getString("password");
            getApiResult(ApiManager.Register(mobile, password, "1234"), MTCallBack<RegisterObject>().apply {
                success {
                    when (it.code) {
                        10000 -> {
                            SharedUtils.setPrefString(baseContext, "account", mobile)
                            SharedUtils.setPrefString(baseContext, "password", password)
                            val utils = SaveObjectUtils(baseContext, "info")
                            utils.setObject("login", it.data!!)
                            warpActivity(LoginActivity::class.java, true)
                        }
                        else -> {
                            //showDialog(""+it.msg)
                            ToastUtil.show(this@RegThirdActivity, "注册失败，请稍后再试！")
                            Log.e("msg", it.msg)
                        }
                    }
                }
            })
        }
        tv_sex.setOnClickListener {
            alertShow()
        }
    }


    fun alertShow() {
        AlertView(null, null, "取消", null,
                arrayOf("男", "女"),
                this, AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
            if (position == 0) {
                showDialog("你选择了男")
            } else if (position == 1) {
                showDialog("你选择了女")
            }
        }).show()
    }
}
