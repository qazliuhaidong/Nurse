package xapp.nurse.api

import android.util.Log

import com.cpoopc.retrofitrxcache.BasicCache
import com.cpoopc.retrofitrxcache.RxCacheCallAdapterFactory
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xapp.nurse.api.ApiManagerService
import xapp.nurse.application.XApplication
import xapp.nurse.uitls.SharedUtils

/**
 * Created by Administrator on 2017/3/23.
 */

class MyService private constructor() {


    // 使用RxJava作为回调适配器(开发版)
    //                .client(okHttpClient) // 使用RxJava作为回调适配器(发布版)
    val apiservice: ApiManagerService
        get() = Retrofit.Builder()
                .baseUrl("http://211.159.184.159:8080/medical-plus/")
//                .baseUrl("http://192.168.3.229:8080/")
//                .baseUrl("http://114.215.88.129:8080/medical-plus/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxCacheCallAdapterFactory.create(BasicCache.fromCtx(XApplication.context), false))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build().create(ApiManagerService::class.java)
    private val okHttpClient = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .build()


    companion object {

        fun get(): MyService {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = MyService()
        }

        private fun getOkHttpClient(): OkHttpClient {
            //日志显示级别
            val level = HttpLoggingInterceptor.Level.BODY
            //新建log拦截器
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.e("httpLog", "OkHttp====Message:" + message) })
            loggingInterceptor.level = level
            //定制OkHttp
            val httpClientBuilder = OkHttpClient.Builder().cookieJar(cookieJar)


            val interceptor2 = Interceptor { chain ->
                val response = chain.proceed(chain.request())
                //存入Session
                if (response.header("sessionId") != null) {
                    SharedUtils.setPrefString(XApplication.context!!, "sessionId", response.header("sessionId"))
                }

                response
            }

            val interceptor3 = Interceptor { chain ->
                val request = chain.request()
                        .newBuilder()
                        .addHeader("OsType", "Android")
                        .addHeader("sessionId", SharedUtils.getPrefString(XApplication.context!!, "sessionId", ""))
                        .build()
                chain.proceed(request)
            }
            //OkHttp进行添加拦截器loggingInterceptor
            httpClientBuilder.addInterceptor(loggingInterceptor).addInterceptor(interceptor2).addInterceptor(interceptor3)
            return httpClientBuilder.build()
        }

        private val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(XApplication.context!!))
    }
}
