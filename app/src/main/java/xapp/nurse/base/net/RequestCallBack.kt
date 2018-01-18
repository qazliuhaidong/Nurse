package xapp.nurse.base.net


/**
 * Created by Administrator on 2017/4/5.
 */

interface RequestCallBack<T> {
    fun onSuccess(it: T)

    fun onFailure(e: Throwable)
}
