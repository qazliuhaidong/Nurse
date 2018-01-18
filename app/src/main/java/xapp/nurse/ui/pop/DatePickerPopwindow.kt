package xapp.medical_trace.com.medicaltrace.ui.pop

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import xapp.nurse.R
import xapp.nurse.uitls.getBackgroundColor
import xapp.nurse.uitls.getColor
import xapp.nurse.uitls.toDate


/**
 * Created by Dell on 2017/10/10.
 */

class DatePickerPopwindow(internal var context: Context, internal var ishideDay: Boolean) : PopupWindow() {
    var startYear: Int = 0
    var startMonth: Int = 0
    var startDay: Int = 1
    var endYear: Int = 0
    var endMonth: Int = 0
    var endDay: Int = 1

    var rl: RelativeLayout? = null
    var startTime: String? = null
    var endTime: String? = null
    var mConvertView: View? = null
    var tvStart: TextView? = null
    var tvEnd: TextView? = null
    var mDatePicker: DatePicker? = null
    var tvOk: TextView? = null
    var llBottom: LinearLayout? = null

    init {
        mConvertView = LayoutInflater.from(context).inflate(
                R.layout.mt_pop_datepicker, null)
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
        setOnDismissListener {
            llBottom!!.startAnimation(getOutAnimation())
        }
    }


    private fun initView(rootView: View) {
        val time = Date().time
        val year = toDate(time).split("/")[0].toInt()
        startYear = year
        endYear = year
        val month = toDate(time).split("/")[1].toInt()
        startMonth = month
        endMonth = month
        val day = toDate(time).split("/")[2].toInt()
        startDay = day
        endDay = day

        if (ishideDay) {
            startTime = toDate(time, 5)
            endTime = toDate(time, 5)
        } else {
            startTime = toDate(time, 4)
            endTime = toDate(time, 4)
        }
        rl = rootView.findViewById(R.id.rl) as RelativeLayout
        llBottom = rootView.findViewById(R.id.ll_bottom) as LinearLayout
        rl!!.setOnClickListener { dismiss() }
        tvOk = rootView.findViewById(R.id.tv_ok) as TextView
        tvStart = rootView.findViewById(R.id.tv_start) as TextView
        tvStart!!.text = "起" + startTime
        tvEnd = rootView.findViewById(R.id.tv_end) as TextView
        tvEnd!!.text = "至" + endTime
        mDatePicker = rootView.findViewById(R.id.datePicker) as DatePicker
        setDatePickerDividerColor(mDatePicker!!, R.color.bg_title)
        mDatePicker!!.maxDate = time

        if (ishideDay) {
            hideDay(mDatePicker!!)
        }

        mDatePicker!!.init(year, month, startDay, { view, year, monthOfYear, dayOfMonth ->
            startYear = year
            startMonth = monthOfYear + 1
            startDay = dayOfMonth
            if (ishideDay) {
                startTime = year.toString() + "-" + (monthOfYear + 1).toString()
                tvStart!!.text = "起" + startTime
            } else {
                startTime = year.toString() + "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth
                tvStart!!.text = "起" + startTime
            }
        })

        tvStart!!.setOnClickListener {
            tvStart!!.getBackgroundColor(R.color.bg_title)
            tvStart!!.getColor(R.color.white)
            tvEnd!!.getBackgroundColor(R.color.white)
            tvEnd!!.getColor(R.color.Text_gray)

            mDatePicker!!.init(startYear, startMonth - 1, startDay, { view, year, monthOfYear, dayOfMonth ->
                startYear = year
                startMonth = monthOfYear + 1
                startDay = dayOfMonth
                if (ishideDay) {
                    startTime = year.toString() + "-" + (monthOfYear + 1).toString()
                    tvStart!!.text = "起" + startTime
                } else {
                    startTime = year.toString() + "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth
                    tvStart!!.text = "起" + startTime
                }
            })
        }
        tvEnd!!.setOnClickListener {
            tvEnd!!.getBackgroundColor(R.color.bg_title)
            tvEnd!!.getColor(R.color.white)
            tvStart!!.getBackgroundColor(R.color.white)
            tvStart!!.getColor(R.color.Text_gray)
            mDatePicker!!.init(endYear, endMonth - 1, endDay, { view, year, monthOfYear, dayOfMonth ->
                endYear = year
                endMonth = monthOfYear + 1
                endDay = dayOfMonth
                if (ishideDay) {
                    endTime = year.toString() + "-" + (monthOfYear + 1).toString()
                    tvEnd!!.text = "至" + endTime
                } else {
                    endTime = year.toString() + "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth
                    tvEnd!!.text = "至" + endTime
                }
            })
        }

        tvOk!!.setOnClickListener {
            val format = if (ishideDay) SimpleDateFormat("yyyy-MM") else SimpleDateFormat("yyyy-MM-dd")
            if (format.parse(startTime).time > format.parse(endTime).time) {
                Toast.makeText(context, "初始时间不能大于结束时间", Toast.LENGTH_LONG).show()
            } else {
                if (ishideDay) {
                    onFinishDate?.StartAndEndtime(getFirstDayOfMonth(format.parse(startTime)).time, getLastDayOfMonth(format.parse(endTime)).time)
                } else {
                    onFinishDate?.StartAndEndtime(format.parse(startTime).time, format.parse(endTime).time)
                }
                dismiss()
            }
        }

        llBottom!!.startAnimation(getInAnimation())
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

    private fun setDatePickerDividerColor(datePicker: DatePicker, id: Int) {
        // Divider changing:

        // 获取 mSpinners
        val llFirst = datePicker.getChildAt(0) as LinearLayout

        // 获取 NumberPicker
        val mSpinners = llFirst.getChildAt(0) as LinearLayout
        for (i in 0..mSpinners.childCount - 1) {
            val picker = mSpinners.getChildAt(i) as NumberPicker

            val pickerFields = NumberPicker::class.java.declaredFields
            for (pf in pickerFields) {
                if (pf.name == "mSelectionDivider") {
                    pf.isAccessible = true
                    try {
                        pf.set(picker, ColorDrawable(context.resources.getColor(id)))
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                    } catch (e: Resources.NotFoundException) {
                        e.printStackTrace()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }

                    break
                }
            }
        }
    }

    private fun hideDay(mDatePicker: DatePicker) {
        try {
            /* 处理android5.0以上的特殊情况 */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android")
                if (daySpinnerId != 0) {
                    val daySpinner = mDatePicker.findViewById(daySpinnerId)
                    if (daySpinner != null) {
                        daySpinner.visibility = View.GONE
                    }
                }
            } else {
                val datePickerfFields = mDatePicker.javaClass.declaredFields
                for (datePickerField in datePickerfFields) {
                    if ("mDaySpinner" == datePickerField.name || "mDayPicker" == datePickerField.name) {
                        datePickerField.isAccessible = true
                        var dayPicker = Any()
                        try {
                            dayPicker = datePickerField.get(mDatePicker)
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }

                        (dayPicker as View).visibility = View.GONE
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    var onFinishDate: finishDate? = null


    interface finishDate {
        fun StartAndEndtime(startTime: Long, endTime: Long)
    }


    fun getInAnimation(): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.push_bottom_in)
    }

    fun getOutAnimation(): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.push_bottom_out)
    }

    fun getLastDayOfMonth(sDate1: Date): Date {
        val cDay1 = Calendar.getInstance()
        cDay1.time = sDate1
        val lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH)
        val lastDate = cDay1.time
        lastDate.date = lastDay
        return lastDate
    }

    fun getFirstDayOfMonth(sDate1: Date): Date {
        val cDay1 = Calendar.getInstance()
        cDay1.time = sDate1
        val FirstDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH)
        val FirstDate = cDay1.time
        FirstDate.date = FirstDay
        return FirstDate
    }

}

