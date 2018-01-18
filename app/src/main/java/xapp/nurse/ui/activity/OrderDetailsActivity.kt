package xapp.nurse.ui.activity

import android.content.Intent
import kotlinx.android.synthetic.main.na_activity_order_details.*
import kotlinx.android.synthetic.main.na_include_details.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.nurse.R
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.GetOrder
import xapp.nurse.ui.model.Login
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.invisible
import xapp.nurse.uitls.toDate
import xapp.nurse.uitls.visible

/**
 * Created by Dell on 2017/11/1.
 */

class OrderDetailsActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.na_activity_order_details
    private var serviceArrangesId:Int=1
    private var mshpName:String?=null
    private var cardName:String?=null
    private var insuranceNo:String?=null
    private var productName:String?=null
    private var integral:Int?=null
    private var arrangeTime:Long?=null
    private var orderDescription:String?=null
    private var type:Int?=null
    var staffId: Int = -1
    private var execId:Int=-1

    override fun afterInitView() {
        setLightStatusBar()
        tv_title.text = "订单详情"
        tv_right.visible()
        tv_right.text="取消"
        val utils= SaveObjectUtils(this,"info")
        val info=utils.getObject("login", Login.DataBean::class.java)
        staffId=info?.staffId!!
        execId=intent.getIntExtra("execId",-1)
        type=intent.getIntExtra("type",-1)
        serviceArrangesId=intent.getIntExtra("serviceArrangesId",1)
        mshpName=intent.getStringExtra("mshpName")
        cardName=intent.getStringExtra("cardName")
        insuranceNo=intent.getStringExtra("insuranceNo")
        productName=intent.getStringExtra("productName")
        orderDescription=intent.getStringExtra("orderDescription")
        arrangeTime=intent.getLongExtra("arrangeTime",10000000)
        integral=intent.getIntExtra("arrangeTime",1)
        mshpName1.text=mshpName
        cardName1.text=cardName
        insuranceNo1.text=insuranceNo
        productName1.text=productName
        orderDescription1.text=orderDescription
        arrangeTime1.text= toDate(arrangeTime!!,1)
        integral1.text=integral.toString()
        if (type==0){
            register.setBackgroundResource(R.drawable.bg_shape_logoff)
            register.text="签退结束服务"
            var type:Short=2
            tv_right.invisible()
            register.setOnClickListener {
                getRegist(type,execId,staffId)
            }
            ib_back.setOnClickListener {
               finish()
            }
        }else if(type==1){
            register.setBackgroundResource(R.drawable.bg_shape_ok)
            register.text="接单"
            var type:Short=1
            var orderType:Short=1
             tv_right.setOnClickListener {
                 getData1(2,orderType,serviceArrangesId,staffId)
             }
            register.setOnClickListener {
                getData(type,orderType,serviceArrangesId,staffId)
            }
            ib_back.setOnClickListener {
                finish()
            }

        }else if(type==2){
            tv_right.invisible()
            register.setBackgroundResource(R.drawable.bg_shape_ok)
            register.text="接单"
            var type:Short=1
            var orderType:Short=2
            register.setOnClickListener {
              getData(type,orderType,serviceArrangesId,staffId)
            }
            ib_back.setOnClickListener {
                finish()
            }
        }else{
            var type:Short=1
            register.setBackgroundResource(R.drawable.bg_shape_ok)
            register.text="签到开始服务"
            tv_right.invisible()
            register.setOnClickListener {
                getRegist1(type,execId,staffId)
            }
            ib_back.setOnClickListener {
                finish()
            }
        }
    }

    private fun  getRegist(type: Short, execId: Int, staffId: Int) {

        getApiResult(ApiManager.getRegist(type,execId,staffId),MTCallBack<GetOrder>().apply {
            success {
                if(it.code==10000){
                    showDialog("签退成功")
                    finish()
                }else{
                    showDialog("请检查网络")
                }
            }
        })
    }
    private fun  getRegist1(type: Short, execId: Int, staffId: Int) {

        getApiResult(ApiManager.getRegist(type,execId,staffId),MTCallBack<GetOrder>().apply {
            success {
                if(it.code==10000){
                    showDialog("签到成功")
                    finish()
                }else{
                    showDialog("请检查网络")
                }
            }

        })


    }


    private fun  getData1(type: Short, orderType: Short, serviceArrangesId: Int, staffId: Int) {
        getApiResult(ApiManager.getOrder(type,orderType,serviceArrangesId,staffId), MTCallBack<GetOrder>().apply {
            success {
                if (it.code==10000){
                    showDialog("取消订单成功")
                    finish()
                }else {
                    showDialog("请检查网络")
                }
            }
        })
    }


    private fun  getData(type: Short, orderType: Short, serviceArrangesId: Int, staffId: Int) {
        getApiResult(ApiManager.getOrder(type,orderType,serviceArrangesId,staffId), MTCallBack<GetOrder>().apply {
            success {
                if (it.code==10000){
                    showDialog("接单成功")
                    finish()
                }else {
                    showDialog("请检查网络")
                }

            }


        })


    }


}
