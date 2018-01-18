package xapp.nurse.api

import okhttp3.MultipartBody
import retrofit2.http.*
import rx.Observable
import xapp.nurse.ui.model.*
import java.io.File


/**
 * Created by Administrator on 2017/3/9.
 */

interface ApiManagerService {


//登录接口
    @FormUrlEncoded
    @POST("system/login")
    fun Login(@Field("userName") userName: String,
              @Field("password") password:String,
              @Field("userType")userType:Short):Observable<Login>
//查询待接订单
    @FormUrlEncoded
    @POST("app/nurses/getWaitOrderByNurse")
    fun WaitOd(@Field("staffId") staffId:Int):Observable<WaitOrder>
//查询公开订单（抢单）
    @FormUrlEncoded
    @POST("app/nurses/getPublicOrder ")
    fun RushOd(@Field("partnerld")partnerld:Int):Observable<RushOrder>
//订单管理（进行中or已完成）
    @FormUrlEncoded
    @POST("app/nurses/getOrderByNurse")
    fun OrderMg(@Field("type")type:Short,
                @Field("staffId")staffId: Int
                ):Observable<OrderManage>
//接单或者拒绝
    @FormUrlEncoded
    @POST("app/nurses/successOrRefuseOrderByNurse")
    fun getOrder(@Field("type")type:Short,
                 @Field("orderType")orderType:Short,
                 @Field("serviceArrangesId")serviceArrangesId:Int,
                 @Field("staffId")staffId:Int):Observable<GetOrder>
//签到或者签退
    @FormUrlEncoded
    @POST("app/nurses/signByNurse")
    fun getRegist(@Field("type")type:Short,
                  @Field("execId")execId:Int,
                  @Field("staffId")staffId:Int):Observable<GetOrder>
//上传图片
    @Multipart
    @POST("app/partnerStaff/uploadAvatar")
    fun getImg(@Part("staffId")staffId:Int,
               @Part partList: List<MultipartBody.Part>):Observable<GetOrder>

    //注册接口
    @FormUrlEncoded
    @POST("app/nurses/registerNurse")
    fun Register(@Field("mobile") userName: String,
              @Field("password") password:String,
              @Field("code") code:String):Observable<RegisterObject>
}
