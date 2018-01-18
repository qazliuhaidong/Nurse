package xapp.nurse.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener

import java.util.ArrayList

import xapp.nurse.R
import xapp.nurse.adapter.StringAdapter
import xapp.nurse.base.BaseActivity

/**
 * Created by Administrator on 2017-11-24.
 */

class PersonalInformation : BaseActivity() {

    private var smart: SmartRefreshLayout? = null
    private var rc: RecyclerView? = null
    private var mList: MutableList<String>? = null
    private var adapter: StringAdapter? = null

    override val layoutId: Int
        get() = R.layout.na_activity_personal_information

    override fun afterInitView() {

        smart = findViewById(R.id.smart) as SmartRefreshLayout
        //        smart.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //        smart.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //
        //        smart.setRefreshHeader(new CircleHeader(this));
        //        smart.setOnRefreshListener(new OnRefreshListener() {
        //            @Override
        //            public void onRefresh(RefreshLayout refreshlayout) {
        //                getData();
        //                smart.finishRefresh(200);
        //            }
        //        });
        smart!!.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout) {
                getData()
                smart!!.finishRefresh(200)
            }

            override fun onRefresh(refreshlayout: RefreshLayout) {
                smart!!.finishRefresh(200)
            }
        })
        rc = findViewById(R.id.rc_smart) as RecyclerView
        rc!!.layoutManager = LinearLayoutManager(this)
        mList = ArrayList<String>()
        var i: Char = 'A'
        while (i < 'J') {
            mList!!.add("" + i.toChar())
            i++
        }
        adapter = StringAdapter(this, mList!!, R.layout.na_item_order_ywc)
        rc!!.adapter = adapter
    }

    private fun getData() {
        mList = ArrayList<String>()
        var i: Char = 'A'
        while (i < 'C') {
            mList!!.add("" + i.toChar())
            i++
        }
        adapter = StringAdapter(this, mList!!, R.layout.na_item_order_ywc)
        rc!!.adapter = adapter

    }


}
