package xapp.nurse.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import kotlinx.android.synthetic.main.na_activity_people_order.*
import kotlinx.android.synthetic.main.na_fragment_order.*
import kotlinx.android.synthetic.main.na_include_recycler.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.adapter.WaitOrderAdapter
import xapp.nurse.adapter.WaitOrderAdapter1
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.Login
import xapp.nurse.ui.model.WaitOrder
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.invisible
import xapp.nurse.uitls.visible
import java.util.*

/**
 * Created by Dell on 2017/10/30.
 */

class PeopleOrderActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_people_order
    private var mList: List<WaitOrder.DataBean.WaitListBean>? = null

    private var mList1: List<WaitOrder.DataBean.NotStartList>? = null

    private var waitOrderAdapter: WaitOrderAdapter? = null

    private var waitOrderAdapter1:WaitOrderAdapter1? =null
    var type:Int=0
    private var adapter: StringAdapter?=null

    private var list: MutableList<String>? = null

    private var staffId: Int = 0
    override fun afterInitView() {
        setLightStatusBar()
        tv_title.text = "待接订单"
        val utils = SaveObjectUtils(this, "info")
        val info = utils.getObject("login", Login.DataBean::class.java)
        staffId = info?.staffId!!
        rcv.layoutManager = LinearLayoutManager(this)
        ib_back.setOnClickListener { finish() }
        getData()
        tv_pending.setOnClickListener {
            pending_line.visible()
            completed_line.invisible()
            type=0
            getData()
        }
        tv_completed.setOnClickListener {
            pending_line.invisible()
            completed_line.visible()
            type=1
            getData1()
        }

    }

    override fun onStart() {
        super.onStart()
        if (type==0){
            getData()
        }else if (type==1){
            getData1()
        }
    }



    private fun getData1() {
        getApiResult(ApiManager.WaitOd(staffId),MTCallBack<WaitOrder>().apply {
            success {
                if (it.code==10000){
                mList1=it.data!!.notStartList
                    val str = "待接单 <font color='#cccccc'>${it.data!!.waitCount}</font> "
                    val str1 = "未开始 <font color='#cccccc'>${it.data!!.notStartCount}</font> "
                    tv_pending.text=Html.fromHtml(str)
                    tv_completed.text=Html.fromHtml(str1)
                    if(mList1!!.size==0){
                        list = java.util.ArrayList<String>()
                        var i: Char = 'A'
                        while (i < 'B') {
                            list!!.add("" + i.toChar())
                            i++
                        }
                        adapter= StringAdapter(this@PeopleOrderActivity,list!!,R.layout.na_view_none1)
                        rcv.adapter=adapter
                    }
                        else{
                     waitOrderAdapter1=WaitOrderAdapter1(this@PeopleOrderActivity,mList1!!,R.layout.na_item_order_going)
                     waitOrderAdapter1?.setOnItemClickListener { itemView, viewType, position ->
                         var i= Intent(this@PeopleOrderActivity,OrderDetailsActivity::class.java)
                         i.putExtra("serviceArrangesId",waitOrderAdapter1!!.data[position].serviceArrangesId)
                         i.putExtra("mshpName",waitOrderAdapter1!!.data[position].mshpName)
                         i.putExtra("insuranceNo",waitOrderAdapter1!!.data[position].insuranceNo)
                         i.putExtra("arrangeTime",waitOrderAdapter1!!.data[position].arrangeTime)
                         i.putExtra("productName",waitOrderAdapter1!!.data[position].productName)
                         i.putExtra("cardName",waitOrderAdapter1!!.data[position].cardName)
                         i.putExtra("integral",waitOrderAdapter1!!.data[position].integral)
                         i.putExtra("orderDescription",waitOrderAdapter1!!.data[position].orderDescription)
                         i.putExtra("execId",waitOrderAdapter1!!.data[position].execId)
//                          var bundle:Bundle=Bundle()
//                         bundle.putSerializable("WaitOrder",waitOrderAdapter1!!.data[position])
//                         i.putExtra("bundle",bundle)
                         i.putExtra("type",3)
                         startActivity(i)
                     }
                        rcv.adapter=waitOrderAdapter1
                 }


                }
            }
        })
    }

    private fun getData() {
        getApiResult(ApiManager.WaitOd(staffId), MTCallBack<WaitOrder>().apply {
            success {
                if (it.code==10000){
                mList=it.data!!.waitList
                    val str = "待接单 <font color='#cccccc'>${it.data!!.waitCount}</font> "
                    val str1 = "未开始 <font color='#cccccc'>${it.data!!.notStartCount}</font> "
                    tv_pending.text=Html.fromHtml(str)
                    tv_completed.text=Html.fromHtml(str1)
                    if(mList!!.size==0){
                        list = java.util.ArrayList<String>()
                        var i: Char = 'A'
                        while (i < 'B') {
                            list!!.add("" + i.toChar())
                            i++
                        }
                        adapter= StringAdapter(this@PeopleOrderActivity,list!!,R.layout.na_view_none1)
                        rcv.adapter=adapter
                    }
                  else{
                      waitOrderAdapter=WaitOrderAdapter(this@PeopleOrderActivity,mList!!,R.layout.na_item_order)
                      waitOrderAdapter?.setOnItemClickListener { itemView, viewType, position ->
                          var i= Intent(this@PeopleOrderActivity,OrderDetailsActivity::class.java)
                          i.putExtra("serviceArrangesId",waitOrderAdapter!!.data[position].serviceArrangesId)
                          i.putExtra("mshpName",waitOrderAdapter!!.data[position].mshpName)
                          i.putExtra("insuranceNo",waitOrderAdapter!!.data[position].insuranceNo)
                          i.putExtra("arrangeTime",waitOrderAdapter!!.data[position].arrangeTime)
                          i.putExtra("productName",waitOrderAdapter!!.data[position].productName)
                          i.putExtra("cardName",waitOrderAdapter!!.data[position].cardName)
                          i.putExtra("integral",waitOrderAdapter!!.data[position].integral)
                          i.putExtra("orderDescription",waitOrderAdapter!!.data[position].orderDescription)
                          i.putExtra("execId",waitOrderAdapter!!.data[position].execId)
                          i.putExtra("type",1)
                          startActivity(i)
                      }
                        rcv.adapter=waitOrderAdapter
                  }

                }
            }


        })
    }

}
