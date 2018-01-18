package xapp.nurse.ui.activity

import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import kotlinx.android.synthetic.main.na_activity_main.*
import xapp.nurse.R
import xapp.nurse.base.BaseActivity
import xapp.nurse.uitls.toast
import xapp.nurse.ui.fragment.OrderFragment
import xapp.nurse.ui.fragment.PersonalFragment
import xapp.nurse.ui.model.Login
import xapp.nurse.uitls.invisible
import xapp.nurse.uitls.visible


class MainActivity : BaseActivity() {
    private var index: Int = 0
    private var currentIndex: Int = 0
    var fragments: Array<Fragment?> = arrayOfNulls<Fragment?>(3)
    override val layoutId: Int
        get() = R.layout.na_activity_main


    override fun afterInitView() {

        setLightStatusBar()
        fragments[0] = OrderFragment.get()
        supportFragmentManager.beginTransaction()
                .add(R.id.frg, fragments[0])
                .hide(fragments[0])
                .show(fragments[0])
                .commit()
        l_order.setOnClickListener {
            ivOrder.setImageResource(R.mipmap.tab_order_nor)
            ivPersonal.setImageResource(R.mipmap.tab_my_nor)
            tvOrder.setTextColor(resources.getColor(R.color.themeColor))
            tvPersonal.setTextColor(resources.getColor(R.color.Text_gray))
            shape_point.visible()
            index = 0
            replaceFragment()
        }
        l_Personal.setOnClickListener {
            ivOrder.setImageResource(R.mipmap.tab_order_sel)
            ivPersonal.setImageResource(R.mipmap.tab_my_sel)
            tvPersonal.setTextColor(resources.getColor(R.color.themeColor))
            tvOrder.setTextColor(resources.getColor(R.color.Text_gray))
            shape_point.invisible()
            index = 1
            replaceFragment()
        }
        rlQd.setOnClickListener {
            shape_point.visible()
            warpActivity(GetOrderActivity::class.java)
        }
    }

    private fun replaceFragment() {
        if (index != currentIndex) {
            val ft = supportFragmentManager.beginTransaction()
            ft.hide(fragments[currentIndex])
            if (fragments[index] == null) {
                when (index) {
                    0 -> fragments[index] = OrderFragment.get()
                    1 -> fragments[index] = PersonalFragment.get()
                }
            }
            if (!fragments[index]!!.isHidden) {
                ft.add(R.id.frg, fragments[index]!!).hide(fragments[index]!!).show(fragments[index]!!)
                ft.commit()
            } else {
                ft.show(fragments[index]!!).commit()
            }
            currentIndex = index
        }
    }


    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
