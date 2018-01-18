package xapp.nurse.ui.activity

import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity

/**
 * Created by Dell on 2017/10/27.
 */

class UpdatePwdActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_changepwd

    override fun afterInitView() {
        setLightStatusBar()
        tv_title.text = "修改密码"
        ib_back.setOnClickListener { finish() }
    }
}
