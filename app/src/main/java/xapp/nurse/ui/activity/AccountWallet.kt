package xapp.nurse.ui.activity

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.na_activity_account_wallet.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.base.BaseActivity

class AccountWallet : BaseActivity() {

    var list:MutableList<String>?=null
    var adapter:StringAdapter?=null



    override val layoutId: Int
        get() =R.layout.na_activity_account_wallet



    override fun afterInitView() {
        setLightStatusBar()
        tv_title.text="账户钱包"
        ib_back.setOnClickListener { finish() }
        rlTx.setOnClickListener { warpActivity(WithDrawActivity::class.java) }

        recycle_wallet.layoutManager=LinearLayoutManager(this)






    }

    override fun onStart() {
        super.onStart()
       list=ArrayList()
       for (i in 1..4) {
           list?.add("1")
       }
        adapter= StringAdapter(this,list!!,R.layout.na_item_wallet)
        recycle_wallet.adapter=adapter
    }



}
