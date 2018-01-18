package xapp.nurse.ui.activity


import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.na_activity_with_draw.*

import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.base.BaseActivity

class WithDrawCashActivity : BaseActivity() {

    var list:MutableList<String>?=null
    var adapter: StringAdapter?=null


    override val layoutId: Int
        get() = R.layout.na_activity_with_draw

    override fun afterInitView() {
        setLightStatusBar()
        recycle_draw.layoutManager=LinearLayoutManager(this)
        ib_back.setOnClickListener { finish() }
        tv_title.text="提现记录"
    }





    override fun onStart() {
        super.onStart()
        list=ArrayList()
        for (i in 1..4) {
            list?.add("1")
        }
        adapter= StringAdapter(this,list!!,R.layout.na_item_wallet)
        recycle_draw.adapter=adapter
    }



}
