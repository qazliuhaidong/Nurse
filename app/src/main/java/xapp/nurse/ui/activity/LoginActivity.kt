package xapp.nurse.ui.activity

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.na_activity_login.*
import library.czh.com.mylibrary.StatusBarUtil
import xapp.nurse.R
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.Login
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.SharedUtils
import xapp.nurse.uitls.TextContent

/**
 * Created by Dell on 2017/10/30.
 */

class LoginActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_login

    override fun afterInitView() {
        tvLogin.setOnClickListener {
            if(getAccount().isEmpty()){
                showDialog("账号不能为空")
                return@setOnClickListener
            }
            if(getPwd().isEmpty()){
                showDialog("密码不能为空")
                return@setOnClickListener
            }
            var account:String=getAccount()
            var password:String=getPwd()
            getApiResult(ApiManager.Login(account,password,2), MTCallBack<Login>().apply {
                success {
                    when(it.code){
                        10000 -> {
                           SharedUtils.setPrefString(baseContext, "account", account)
                           SharedUtils.setPrefString(baseContext, "password", password)
                            val utils = SaveObjectUtils(baseContext, "info")
                            utils.setObject("login", it.data!!)
                            showDialog("登录成功")
                            val intent:Intent=Intent(this@LoginActivity,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else -> {
                        showDialog(""+it.msg)
                    }

                    }
                }



            })

            }
        tvReg.setOnClickListener {
            warpActivity(RegFirstActivity::class.java,false)
        }


    }
    private fun getAccount():String{
        return et_account.text.toString()
    }
    private fun getPwd():String{
        return et_pwd.text.toString()
    }

}
