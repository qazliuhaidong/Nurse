package xapp.nurse.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.util.Log
import kotlinx.android.synthetic.main.na_fragment_order.*
import kotlinx.android.synthetic.main.na_fragment_order_type.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.adapter.OrderManageAdapter
import xapp.nurse.adapter.OrderManageAdapter1
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseFragment
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.activity.OrderDetailsActivity
import xapp.nurse.ui.activity.PeopleOrderActivity
import xapp.nurse.ui.model.Login
import xapp.nurse.ui.model.OrderManage
import xapp.nurse.ui.model.WaitOrder
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.gone
import xapp.nurse.uitls.toast
import xapp.nurse.uitls.visible
import java.util.*

/**
 * Created by Dell on 2017/10/24.
 */

class OrderFragment : BaseFragment() {

    var staffId: Int = -1
    private var mList: List<OrderManage.DataBean>? = null

    private var msgAdapter: OrderManageAdapter? = null

    private var msgAdapter1: OrderManageAdapter1? = null

    private var list: MutableList<String>? = null

    private var adapter:StringAdapter?=null

    private var tp=-1

    override val layoutResource: Int
        get() = R.layout.na_fragment_order

    override fun afterInitView() {
        ib_back.gone()
        tv_title.text = "订单管理"
        iv_right.visible()
        iv_right.setImageResource(R.mipmap.topbar_search)
         val utils=SaveObjectUtils(activity,"info")
        val info=utils.getObject("login",Login.DataBean::class.java)
         staffId=info?.staffId!!
        rcv1.layoutManager = LinearLayoutManager(activity)
        var type:Short=0
        getData(type,info.staffId)

        rl_fragment_order.setOnClickListener { warpActivity(PeopleOrderActivity::class.java) }
        tv_dpd.setOnClickListener {
            tv_dpd.setBackgroundResource(R.drawable.bg_shape_radius_top)
            tv_ywc.setBackgroundColor(0xf5f5f5)
            var type:Short=0
            tp=0
            getData(type,info.staffId)
        }
        tv_ywc.setOnClickListener {
            tv_dpd.setBackgroundColor(0xf5f5f5)
            tv_ywc.setBackgroundResource(R.drawable.bg_shape_radius_top)
            var type:Short=1
            tp=1
            getData1(type,info.staffId)
        }
    }

    private fun getOrder() {
        add(ApiManager.WaitOd(staffId),MTCallBack<WaitOrder>().apply {
            success {
                if (it.code==10000){
                    tvDJ.setText("待接订单:  "+it.data!!.waitCount.toString())
                    todayOrder.setText("今日接单 "+it.data!!.todayOrder.toString())
                    notStartCount.setText("未开始 "+it.data!!.notStartCount.toString())
                }
            }

        })


    }

    override fun onStart() {
        super.onStart()
        getOrder()
        if (tp==0){
            getData(0,staffId)
        }else if(tp==1){
            getData1(1,staffId)
        }

    }


    private fun  getData(type: Short, staffId: Int) {
        add(ApiManager.OrderMg(type,staffId), MTCallBack<OrderManage>().apply {
            success {
                if (it.code==10000){
                   mList=it.data
                    val str = "<font color='#FF8800'>${it.data!!.size}</font> 个订单进行中"
                    tvStatistica2.text = Html.fromHtml(str)
                    if(mList!!.size==0){
                        list = java.util.ArrayList<String>()
                        var i: Char = 'A'
                        while (i < 'B') {
                            list!!.add("" + i.toChar())
                            i++
                        }
                        adapter= StringAdapter(activity,list!!,R.layout.na_view_none)
                        rcv1.adapter=adapter
                    }
                    else{
                        msgAdapter= OrderManageAdapter(activity,mList!!,R.layout.na_item_order_going)
                        msgAdapter?.setOnItemClickListener { itemView, viewType, position ->
                            var i= Intent(activity,OrderDetailsActivity::class.java)
                            i.putExtra("serviceArrangesId",msgAdapter!!.data[position].serviceArrangesId)
                            i.putExtra("mshpName",msgAdapter!!.data[position].mshpName)
                            i.putExtra("insuranceNo",msgAdapter!!.data[position].insuranceNo)
                            i.putExtra("arrangeTime",msgAdapter!!.data[position].arrangeTime)
                            i.putExtra("productName",msgAdapter!!.data[position].productName)
                            i.putExtra("cardName",msgAdapter!!.data[position].cardName)
                            i.putExtra("integral",msgAdapter!!.data[position].integral)
                            i.putExtra("orderDescription",msgAdapter!!.data[position].orderDescription)
                            i.putExtra("execId",msgAdapter!!.data[position].execId)
                            i.putExtra("type",0)
                            startActivity(i)
                        }
                        rcv1.adapter = msgAdapter
                    }

                }
            }

        })

    }
    private fun  getData1(type: Short, staffId: Int) {

        add(ApiManager.OrderMg(type,staffId), MTCallBack<OrderManage>().apply {
            success {
                if (it.code==10000){
                    mList=it.data
                    val str = "<font color='#FF8800'>${it.data!!.size}</font> 个订单已完成"
                    tvStatistica2.text = Html.fromHtml(str)
                    if(mList!!.size==0) {
                        list = java.util.ArrayList<String>()
                        var i: Char = 'A'
                        while (i < 'B') {
                            list!!.add("" + i.toChar())
                            i++
                        }
                        adapter = StringAdapter(activity, list!!, R.layout.na_view_none)
                        rcv1.adapter = adapter
                    }
                   else{
                        msgAdapter1= OrderManageAdapter1(activity,mList!!,R.layout.na_item_order_ywc)
                        rcv1.adapter = msgAdapter1
                    }


                }

            }


        })

    }


    companion object {
        fun get(): OrderFragment {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = OrderFragment()
        }
    }
}
