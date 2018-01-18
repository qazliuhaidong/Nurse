package xapp.nurse.api

import okhttp3.MultipartBody
import rx.Observable
import xapp.nurse.api.MyService
import xapp.nurse.ui.model.*
import java.io.File


/**
 * Created by Administrator on 2017/3/9.
 */

object ApiManager {

    internal var apiservice = MyService.get().apiservice

    fun Login(userName:String,password:String,userType:Short):Observable<Login>{
        return apiservice.Login(userName,password,userType)
    }

    fun WaitOd(staffId:Int):Observable<WaitOrder>{
       return apiservice.WaitOd(staffId)
    }

    fun RushOd(partnerId:Int):Observable<RushOrder>{
        return apiservice.RushOd(partnerId)
    }

    fun OrderMg(type:Short,staffId: Int):Observable<OrderManage>{
        return apiservice.OrderMg(type,staffId)
    }

    fun getOrder(type:Short,orderType:Short,serviceArrangesId:Int,staffId:Int):Observable<GetOrder>{
        return apiservice.getOrder(type,orderType,serviceArrangesId,staffId)
    }

    fun getRegist(type:Short,execId:Int,staffId:Int):Observable<GetOrder>{
        return apiservice.getRegist(type,execId,staffId)
    }

    fun getImg(staffId:Int,partList: List<MultipartBody.Part>):Observable<GetOrder>{
        return apiservice.getImg(staffId,partList)
    }

    //注册'
    fun Register(userName:String,password:String,code:String):Observable<RegisterObject>{
        return apiservice.Register(userName,password,code)
    }

}
