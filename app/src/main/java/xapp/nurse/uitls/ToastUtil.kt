package xapp.nurse.uitls

import android.widget.Toast
import android.app.Activity


/**
 * Created by hugo on 2018/1/4.
 */
class ToastUtil {
    companion object {
        fun show(activity: Activity?, text: String?) {
            if (activity == null || activity.isFinishing) return
            activity.runOnUiThread(Runnable {
                // TODO Auto-generated method stub
                if (text == null || text.trim { it <= ' ' }.length < 1) return@Runnable
                Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
            })
        }

        fun show(activity: Activity?, resid: Int) {
            if (resid < 0) return;
            show(activity, activity?.getString(resid))
        }
    }
}