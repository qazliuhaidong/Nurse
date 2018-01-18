package xapp.nurse.base.net

/**
 * Created by Dell on 2017/10/11.
 */
open class MTCallBack<T> : RequestCallBack<T> {
    private var _OnSucess: ((t: T) -> Unit)? = null
    private var _OnFailure: ((e: Throwable) -> Unit)? = null
    fun success(listener: (t: T) -> Unit) {
        _OnSucess = listener
    }

    override fun onSuccess(it: T) {
        _OnSucess?.invoke(it)
    }

    fun fail(listener: (e: Throwable) -> Unit) {
        _OnFailure = listener
    }

    override fun onFailure(e: Throwable) {
        _OnFailure?.invoke(e)
    }
}