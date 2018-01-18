package xapp.nurse.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.afollestad.materialdialogs.MaterialDialog
import com.cpoopc.retrofitrxcache.RxCacheResult

import rx.Observable
import rx.subscriptions.CompositeSubscription
import xapp.nurse.R
import xapp.nurse.base.net.RequestUtil
import xapp.nurse.base.net.MTCallBack

//fragment基类
abstract class BaseFragment : Fragment() {
    protected var rootView: View? = null
    var mRequestUtil = RequestUtil.get()
    private val mCompositeSubscription = CompositeSubscription()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater!!.inflate(layoutResource, container, false)
        return rootView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
        afterInitView()
    }

    //获取布局文件
    protected abstract val layoutResource: Int

    //初始化view
    protected abstract fun afterInitView()


    override fun onDestroyView() {

        super.onDestroyView()
//        mRxManager.clear()
        mCompositeSubscription.clear()
        rootView = null
    }

    /**
     * 跳转Class

     * @param targetClass
     */
    fun warpActivity(targetClass: Class<*>) {
        val intent = Intent(activity, targetClass)
        startActivity(intent)
    }

    /**
     * 跳转Class

     * @param targetClass
     */
    fun warpActivity(targetClass: Class<*>, isFinish: Boolean) {
        val intent = Intent(activity, targetClass)
        startActivity(intent)
        if (isFinish) {
            activity.finish()
        }
    }
    private var dialog: MaterialDialog? = null

    fun showLoading() {
        dialog = MaterialDialog.Builder(activity)
                .content("Loading...")
                .progress(true, 0)
                .show()
    }

    fun stopLoading() {
        if (dialog != null && dialog?.isShowing!!) dialog?.dismiss()
    }


    fun <T> getApiResult(request: Observable<T>, listener: MTCallBack<T>) {
        mCompositeSubscription.add(mRequestUtil.getSubscription(request, listener))
    }



    fun showDialog(string: String) {
        MaterialDialog.Builder(activity)
                .content(string)
                .contentColorRes(R.color.msg_dialog_content)
                .positiveText("确定")
                .positiveColorRes(R.color.msg_dialog_positive)
                .show()
    }

    fun showDialog(str: String, callback: MaterialDialog.SingleButtonCallback) {
        MaterialDialog.Builder(activity)
                .content(str)
                .contentColorRes(R.color.msg_dialog_content)
                .positiveText("确定")
                .onPositive(callback)
                .positiveColorRes(R.color.msg_dialog_positive)
                .show()
    }


    fun <T> add(request: Observable<T>, listener: MTCallBack<T>) {
        mCompositeSubscription.add(mRequestUtil.getSubscription(request, listener))
    }

    fun <T> getCacheApiResult(request: Observable<RxCacheResult<T>>, listener: MTCallBack<T>) {
        mCompositeSubscription.add(mRequestUtil.getRxCacheSubscription(request, listener))
    }


}
