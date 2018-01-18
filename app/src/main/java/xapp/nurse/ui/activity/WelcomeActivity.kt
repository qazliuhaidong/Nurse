package xapp.nurse.ui.activity

import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.na_activity_welcome.*
import library.czh.com.mylibrary.LightStatusBarUtils
import library.czh.com.mylibrary.RomUtils
import library.czh.com.mylibrary.StatusBarUtil
import xapp.nurse.R
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.Login
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.SharedUtils

/**
 * Created by Dell on 2017/10/30.
 */

class WelcomeActivity : BaseActivity() {


    var isFirstEnter: Boolean? = true
    var isLogin: Boolean? = false

    override val layoutId: Int
        get() = R.layout.na_activity_welcome

    override fun afterInitView() {
        StatusBarUtil.setTranslucent(this, 0)
        if (RomUtils.isLightStatusBarAvailable()) {
            LightStatusBarUtils.setLightStatusBar(this, true)
        } else {
            StatusBarUtil.setColor(this, 0xff363636.toInt())
        }
        isFirstEnter = SharedUtils.getPrefBoolean(baseContext, "isFirstEnter", true)
        isLogin = SharedUtils.getPrefBoolean(baseContext, "isLogin", false)
        when (isFirstEnter) {
            true -> handler.sendEmptyMessageDelayed(0,2000)
            else -> handler.sendEmptyMessageDelayed(0,2000)
        }
        versionText.setText(getString(R.string.app_name)+"  "+getPackageInfo(this)?.versionName)
    }
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    val account: String = SharedUtils.getPrefString(baseContext, "account", "")
                    val password: String = SharedUtils.getPrefString(baseContext, "password", "")
                    if (account.equals("")) {
                        warpActivity(LoginActivity::class.java, true)
                        return
                    } else {
                        getApiResult(ApiManager.Login(account, password,2),
                                MTCallBack<Login>().apply {
                                    success {
                                        if (it.code == 10000) {
                                            val utils = SaveObjectUtils(baseContext, "info")
                                            utils.setObject("login", it.data!!)
                                            warpActivity(MainActivity::class.java, true)
                                        } else {
                                            warpActivity(LoginActivity::class.java, true)
                                        }
                                    }
                                    fail { warpActivity(MainActivity::class.java, true) }
                                }


                        )
                    }


                }
                1 -> {
                    warpActivity(LoginActivity::class.java, true)
                }
            }
        }
    }
}
