package xapp.nurse.uitls

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * Created by hugo on 2018/1/4.
 */
class Constants {
    companion object {
        // 是否是电话号码
        fun isMobileNO(mobiles: String): Boolean {
            if (TextUtils.isEmpty(mobiles)) return false
            val p = Pattern.compile("^((13[0-9])|(17[0,0-9])|(147)|(199)|(15[^4,\\D])|(18[0,0-9]))\\d{8}$")
            val m = p.matcher(mobiles)
            return m.matches()
        }
    }
}