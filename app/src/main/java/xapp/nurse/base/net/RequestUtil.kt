package xapp.nurse.base.net

import com.cpoopc.retrofitrxcache.RxCacheResult

import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/4/5.
 */

class RequestUtil private constructor() {

    //无缓存
    fun <T> getSubscription(request: Observable<T>, listener: MTCallBack<T>): Subscription {

        val sub: Subscription
        sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> listener.onSuccess(t) }) { throwable -> listener.onFailure(throwable) }
        return sub
    }

    //缓存
    fun <T> getRxCacheSubscription(request: Observable<RxCacheResult<T>>, listener: MTCallBack<T>): Subscription {

        val sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tRxCacheResult -> listener.onSuccess(tRxCacheResult.resultModel) }) { throwable -> listener.onFailure(throwable) }
        return sub
    }

    companion object {

        fun get(): RequestUtil {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = RequestUtil()
        }
    }
}
