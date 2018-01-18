package xapp.nurse.base


import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.EditText

import com.zhy.autolayout.AutoLayoutActivity

import com.afollestad.materialdialogs.MaterialDialog
import com.cpoopc.retrofitrxcache.RxCacheResult
import library.czh.com.mylibrary.LightStatusBarUtils
import library.czh.com.mylibrary.RomUtils
import library.czh.com.mylibrary.StatusBarUtil
import rx.Observable
import rx.subscriptions.CompositeSubscription
import xapp.nurse.R
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.base.net.RequestUtil
import xapp.nurse.uitls.AppManager
import java.lang.reflect.Method


//Activity基类
abstract class BaseActivity : AutoLayoutActivity() {
    var mContext: Context? = null
    var mRequestUtil = RequestUtil.get()
    var mSavedInstanceState: Bundle? = null
    private val mCompositeSubscription = CompositeSubscription()

    override fun onStart() {
        super.onStart()
        Log.d("当前Activity", "==》 (${javaClass.simpleName}.kt:1)")
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBeforeSetcontentView()
        setContentView(layoutId)
        StatusBarUtil.setColor(this,resources.getColor(R.color.themeColor), 0)
        mContext = this
        afterInitView()
    }

    /**
     * 设置layout前配置
     */









    private fun doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.appManager.addActivity(this)
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // 默认不自动弹起软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

    }


    //获取布局文件
    abstract val layoutId: Int

    //初始化view
    abstract fun afterInitView()


    /**
     * 跳转Class

     * @param targetClass
     */
    fun warpActivity(targetClass: Class<*>) {
        val intent = Intent(this, targetClass)
        startActivity(intent)
    }

    /**
     * 跳转Class

     * @param targetClass
     */
    fun warpActivity(targetClass: Class<*>, int: Int) {
        if (int == 1) {
            val i = Intent(this, targetClass)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
    }


   //设置关闭软键盘光标正常显示
    fun disableShowSoftInput(editText: EditText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.inputType = InputType.TYPE_NULL
        } else {
            val cls = EditText::class.java
            var method: Method
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
                method.isAccessible = true
                method.invoke(editText, false)
            } catch (e: Exception) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", Boolean::class.javaPrimitiveType)
                method.isAccessible = true
                method.invoke(editText, false)
            } catch (e: Exception) {
            }

        }
    }



    //浅色状态栏
    fun setLightStatusBar() {
        StatusBarUtil.setColor(this, 0xfffafafa.toInt(), 0)
        if (RomUtils.isLightStatusBarAvailable()) {
            LightStatusBarUtils.setLightStatusBar(this, true)
        } else {
            StatusBarUtil.setColor(this, 0xff363636.toInt())
        }
    }

    /**
     * 跳转Class

     * @param targetClass
     */

    fun warpActivity(targetClass: Class<*>, isFinish: Boolean) {
        runOnUiThread(Runnable {
            val intent = Intent(this, targetClass)
            startActivity(intent)
            if (isFinish) {
                finish()
            }
        })
    }

    /**
     * 跳转Class

     * @param targetClass
     */
    fun warpActivity(targetClass: Class<*>, isFinish: Boolean, bundle: Bundle) {
        val intent = Intent(this, targetClass)
        intent.putExtra("bundle", bundle)
        startActivity(intent)
        if (isFinish) {
            finish()
        }
    }


    fun showDialog(string: String) {
        MaterialDialog.Builder(this)
                .content(string)
                .contentColorRes(R.color.msg_dialog_content)
                .positiveText("确定")
                .positiveColorRes(R.color.msg_dialog_positive)
                .show()
    }

    fun showDialog(str: String, callback: MaterialDialog.SingleButtonCallback) {
        MaterialDialog.Builder(this)
                .content(str)
                .contentColorRes(R.color.msg_dialog_content)
                .positiveText("确定")
                .onPositive(callback)
                .positiveColorRes(R.color.msg_dialog_positive)
                .show()
    }

    private var mLoadingDialog: MaterialDialog? = null

    fun showLoading() {
        mLoadingDialog = MaterialDialog.Builder(this)
                .content("Loading...")
                .progress(true, 0)
                .show()
    }

    fun stopLoading() {
        if (mLoadingDialog != null && mLoadingDialog?.isShowing!!) mLoadingDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeSubscription.clear()
        AppManager.appManager.finishActivity(this)
    }


    fun <T> getApiResult(request: Observable<T>, listener: MTCallBack<T>) {
        mCompositeSubscription.add(mRequestUtil.getSubscription(request, listener))
    }

    fun <T> getCacheApiResult(request: Observable<RxCacheResult<T>>, listener: MTCallBack<T>) {
        mCompositeSubscription.add(mRequestUtil.getRxCacheSubscription(request, listener))
    }


     fun getPackageInfo(context: Context): PackageInfo? {
        var pi: PackageInfo? = null

        try {
            val pm = context.getPackageManager()
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS)

            return pi
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return pi
    }
}
