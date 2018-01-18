package xapp.nurse.ui.activity

import android.content.Context
import kotlinx.android.synthetic.main.na_activity_welcome.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity
import xapp.nurse.uitls.visible
import android.content.pm.PackageManager
import android.content.pm.PackageInfo



/**
 * Created by Dell on 2017/10/13.
 */

class AboutActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_welcome

    override fun afterInitView() {
        setLightStatusBar()
        rl_title.visible()
        tv_line.visible()
        versionText.setText(getString(R.string.app_name)+"  "+getPackageInfo(this)?.versionName)
        ib_back.setOnClickListener { finish() }
    }


}
