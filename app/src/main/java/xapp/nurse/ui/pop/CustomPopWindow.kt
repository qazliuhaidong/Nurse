package xapp.medical_trace.com.medicaltrace.ui.pop

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ListView
import android.widget.PopupWindow
import xapp.nurse.R


/**
 * Created by Administrator on 2017/3/17.
 */

class CustomPopWindow(internal var context: Context) : PopupWindow() {
    var popList: ListView? = null
    private var mConvertView: View?=null

    init {
        mConvertView = LayoutInflater.from(context).inflate(
                R.layout.mt_pop_list, null)
        calWidthAndHeight(context)
        contentView = mConvertView
        initView(mConvertView!!)
        isFocusable = true
        isClippingEnabled = true
        isOutsideTouchable = true// 设置可在外部点击
        // 一定要设置背景 否则点击屏幕外部popwindow不会消失
        setBackgroundDrawable(BitmapDrawable())
        // 事件拦截
        setTouchInterceptor(View.OnTouchListener { v, event ->
            // 如果点击的是区域外 popwindow就消失
            if (event.action == MotionEvent.ACTION_OUTSIDE) {
                dismiss()
                return@OnTouchListener true// 返回true 表示这个点击事件被当前控件消费掉了
            }
            false
        })
        mConvertView!!.setOnClickListener { dismiss() }
    }

    /**
     * 计算popwindow的宽和高
     */
    private fun calWidthAndHeight(context: Context) {
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)

        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.MATCH_PARENT
    }

    private fun initView(rootView: View) {
        popList = rootView.findViewById(R.id.pop_list) as ListView
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int, gravity: Int) {
        if (Build.VERSION.SDK_INT >= 24) {
            val rect = Rect()
            anchor.getGlobalVisibleRect(rect)
            val h = anchor.resources.displayMetrics.heightPixels - rect.bottom
            height = h
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity)
    }
}
