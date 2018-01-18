package xapp.nurse.ui.activity
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity

class OutfitInformation : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_outfit_information

    override fun afterInitView() {
        tv_title.text="机构信息"
        ib_back.setOnClickListener { finish() }
    }


}
