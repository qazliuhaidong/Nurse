package xapp.nurse.uitls

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import xapp.nurse.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import android.text.TextUtils



/**
 * Created by Dell on 2017/9/13.
 */
fun ImageView.load(url: String) {
    Glide.with(context).load(url).into(this)
}

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun TextView.getColor(@ColorRes id: Int) {
    this.setTextColor(ContextCompat.getColor(context, id))
}

fun TextView.getBackgroundColor(@ColorRes id: Int) {
    this.setBackgroundColor(ContextCompat.getColor(context, id))
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun EditText.TextContent(): String = this.text.toString().trim()
fun EditText.isEmpty(): Boolean = this.text.toString().trim().isEmpty()


fun checkMobile(mobile: String): Boolean {
    val regex = "(\\+\\d+)?1[3458]\\d{9}$"
    return Pattern.matches(regex, mobile)
}

fun checkPhone(phone: String): Boolean {
    val regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"
    return Pattern.matches(regex, phone)

}

fun String.isWeakEmpty() = this.isEmpty() && this.trim().length == 0

fun toDate(long: Long): String {
    val format = SimpleDateFormat("yyyy/MM/dd")
    return format.format(long)
}

fun toDate(long: Long, type: Int): String {
    val format1 = SimpleDateFormat("yyyy/MM/dd")
    val format2 = SimpleDateFormat("MM-dd")
    val format3 = SimpleDateFormat("HH:mm")
    val format4 = SimpleDateFormat("yyyy-MM-dd")
    val format5 = SimpleDateFormat("yyyy-MM")
    val format6 = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val format7 = SimpleDateFormat("MM月dd日 HH:mm")
    when (type) {
        1 -> return format1.format(long)
        2 -> return format2.format(long)
        3 -> return format3.format(long)
        4 -> return format4.format(long)
        5 -> return format5.format(long)
        6 -> return format6.format(long)
        7 -> return format7.format(long)
    }
    return format1.format(long)
}

fun getAge(birthDay: Long): Int {
    val cal = Calendar.getInstance()
    val yearNow = cal.get(Calendar.YEAR)
    val monthNow = cal.get(Calendar.MONTH)
    val dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH)
    cal.time = Date(birthDay)

    val yearBirth = cal.get(Calendar.YEAR)
    val monthBirth = cal.get(Calendar.MONTH)
    val dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH)

    var age = yearNow - yearBirth

    if (monthNow <= monthBirth) {
        if (monthNow === monthBirth) {
            if (dayOfMonthNow < dayOfMonthBirth) age--
        } else {
            age--
        }
    }
    return age
}




fun Context.call(tel: String) {
    MaterialDialog.Builder(this)
            .title("拨打电话")
            .content(tel)
            .contentGravity(GravityEnum.CENTER)
            .contentColor(0xff333333.toInt())
            .positiveText("确定")
            .positiveColorRes(R.color.bg_title)
            .onPositive { _, _ ->
                val intent = Intent(Intent.ACTION_DIAL)
                val data = Uri.parse("tel:" + tel)
                intent.data = data
                startActivity(intent)
            }
            .negativeText("取消")
            .negativeColorRes(R.color.bg_title)
            .show()
}



