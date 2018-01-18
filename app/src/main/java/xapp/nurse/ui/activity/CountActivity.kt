package xapp.nurse.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import kotlinx.android.synthetic.main.na_activity_count.*
import kotlinx.android.synthetic.main.na_activity_with_draw.*
import kotlinx.android.synthetic.main.na_include_title.*
import xapp.medical_trace.com.medicaltrace.ui.pop.DatePickerPopwindow
import xapp.nurse.R
import xapp.nurse.R.layout.na_activity_count
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.base.BaseActivity
import xapp.nurse.uitls.toDate

class CountActivity : BaseActivity() {
    var startTime: String = ""
    var endTime: String = ""
    var list:MutableList<String>?=null
    var adapter: StringAdapter?=null




    override val layoutId: Int
        get() = R.layout.na_activity_count

    override fun afterInitView() {
        setLightStatusBar()
        ib_back.setOnClickListener { finish() }
        tv_title.text="统计"
        recycle_count.layoutManager= LinearLayoutManager(this)
        l_data.setOnClickListener {
            val pop=DatePickerPopwindow(this,false)
            pop.showAtLocation(activity_count,Gravity.BOTTOM,0,0)
            pop.onFinishDate = object : DatePickerPopwindow.finishDate {
                override fun StartAndEndtime(startTime: Long, endTime: Long) {
                    this@CountActivity.startTime = toDate(startTime, 4)
                    this@CountActivity.endTime = toDate(endTime, 4)
                    tv_date.text = this@CountActivity.startTime + "至" + this@CountActivity.endTime
//                    getTrackStatistics()
                }
            }
        }

    }


    override fun onStart() {
        super.onStart()
        list=ArrayList()
        for (i in 1..4) {
            list?.add("1")
        }
        adapter= StringAdapter(this,list!!,R.layout.na_item_wallet)
        recycle_count.adapter=adapter


    }



}
