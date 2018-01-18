package xapp.nurse.ui.activity

import android.content.Intent
import android.support.annotation.Px
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.na_activity_getorder.*
import kotlinx.android.synthetic.main.na_include_recycler.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.adapter.OrderManageAdapter
import xapp.nurse.adapter.RushOrderAdapter
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.Login
import xapp.nurse.ui.model.OrderManage
import xapp.nurse.ui.model.RushOrder
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.getColor
import xapp.nurse.uitls.gone
import xapp.nurse.uitls.visible
import java.util.*

/**
 * Created by Dell on 2017/11/1.
 */

class GetOrderActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_getorder
    var staffId: Int = -1
    private var mList: List<RushOrder.DataBean>? = null

    private var rushOrderAdapter: RushOrderAdapter? = null

    private var adapter: StringAdapter?=null

    private var list: MutableList<String>? = null


    override fun afterInitView() {
        setLightStatusBar()
        ib_back.gone()
        tv_title.text="抢单"
        tv_right.text = "换一批"
        tv_right.visible()
        val utils= SaveObjectUtils(this,"info")
        val info=utils.getObject("login", Login.DataBean::class.java)
        staffId=info?.staffId!!
        tv_right.getColor(R.color.themeColor)
        getData()
        rcv.layoutManager = LinearLayoutManager(this)
        ivClose.setOnClickListener { finish() }
    }
    override fun onStart() {
        super.onStart()
        getData()
    }
    private fun getData() {
        getApiResult(ApiManager.RushOd(staffId), MTCallBack<RushOrder>().apply {
            success {
                if (it.code==10000){
                    mList=it.data
                    if(mList!!.size==0){
                        list = java.util.ArrayList<String>()
                        var i: Char = 'A'
                        while (i < 'B') {
                            list!!.add("" + i.toChar())
                            i++
                        }
                        adapter= StringAdapter(this@GetOrderActivity,list!!,R.layout.na_view_none1)
                        rcv.adapter=adapter
                    }
                    else{
                        rushOrderAdapter=RushOrderAdapter(this@GetOrderActivity,mList!!,R.layout.na_item_order)
                        rushOrderAdapter?.setOnItemClickListener { itemView, viewType, position ->
                            var i=Intent(this@GetOrderActivity,OrderDetailsActivity::class.java)
                            i.putExtra("serviceArrangesId",rushOrderAdapter!!.data[position].serviceArrangesId)
                            i.putExtra("mshpName",rushOrderAdapter!!.data[position].mshpName)
                            i.putExtra("insuranceNo",rushOrderAdapter!!.data[position].insuranceNo)
                            i.putExtra("arrangeTime",rushOrderAdapter!!.data[position].arrangeTime)
                            i.putExtra("productName",rushOrderAdapter!!.data[position].productName)
                            i.putExtra("cardName",rushOrderAdapter!!.data[position].cardName)
                            i.putExtra("integral",rushOrderAdapter!!.data[position].integral)
                            i.putExtra("orderDescription",rushOrderAdapter!!.data[position].orderDescription)
                            i.putExtra("execId",rushOrderAdapter!!.data[position].execId)
                            i.putExtra("type",2)
                            startActivity(i)
                        }
                        rcv.adapter=rushOrderAdapter
                    }

                }

            }

        })
    }
}
