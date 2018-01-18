package xapp.nurse.application

import android.app.Application
import android.content.Context

import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import xapp.nurse.R


/**
 * Created by Dell on 2017/9/27.
 */

class XApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this

    }

    companion object {
        /**
         * 全局的上下文
         */
        /**
         * 获取context

         * @return
         */
        var context: Context? = null
            private set

        lateinit var instance: XApplication
            internal set

        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreater(DefaultRefreshHeaderCreater { context, layout ->
                layout.setPrimaryColorsId(android.R.color.white, R.color.Text_gray)//全局设置主题颜色
                ClassicsHeader(context)

            })
        }
    }
}
