package xapp.nurse.ui.activity


import kotlinx.android.synthetic.main.na_activity_bind_wallet.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity

class BindWalletActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_bind_wallet

    override fun afterInitView() {
        setLightStatusBar()
        tv_title.text="绑定资金账户"
        ib_back.setOnClickListener {
            finish()
        }
        l_zfb.setOnClickListener {
            tv_zfb.setTextColor(resources.getColor(R.color.colorAccent))
        }


    }


}
