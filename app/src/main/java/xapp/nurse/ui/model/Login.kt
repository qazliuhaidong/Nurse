package xapp.nurse.ui.model

import java.io.Serializable

/**
 * Created by Administrator on 2017-11-27.
 */

class Login {


    /**
     * code : 10000
     * msg : 操作成功
     * data : {"userId":9,"staffId":47,"userName":"admin5","partnerName":"成都市社保局哈哈","userType":8,"staffName":"喵星人","loginType":1,"userPwd":"2aac15029aaafec9615d310ea0bcf0f7","pwdExpiredDays":0,"isLocked":0,"createdTime":1511750398000,"lastLoginTime":null,"pwdModifyTime":null,"description":null,"partnerId":25,"picture":null,"imgPath":null,"wallet":20698.5,"staffModifyTime":1510212684307,"integral":3306,"mobile":"32132154","partnerCode":"SB-0000002","applicationBeans":null}
     */

    var code: Int = 0
    var msg: String? = null
    var data: DataBean? = null

    class DataBean : Serializable {
        /**
         * userId : 9
         * staffId : 47
         * userName : admin5
         * partnerName : 成都市社保局哈哈
         * userType : 8
         * staffName : 喵星人
         * loginType : 1
         * userPwd : 2aac15029aaafec9615d310ea0bcf0f7
         * pwdExpiredDays : 0
         * isLocked : 0
         * createdTime : 1511750398000
         * lastLoginTime : null
         * pwdModifyTime : null
         * description : null
         * partnerId : 25
         * picture : null
         * imgPath : null
         * wallet : 20698.5
         * staffModifyTime : 1510212684307
         * integral : 3306
         * mobile : 32132154
         * partnerCode : SB-0000002
         * applicationBeans : null
         */

        var userId: Int = 0
        var staffId: Int = 0
        var userName: String? = null
        var partnerName: String? = null
        var userType: Int = 0
        var staffName: String? = null
        var loginType: Int = 0
        var userPwd: String? = null
        var pwdExpiredDays: Int = 0
        var isLocked: Int = 0
        var createdTime: Long = 0
        var lastLoginTime: Any? = null
        var pwdModifyTime: Any? = null
        var description: Any? = null
        var partnerId: Int = 0
        var picture: Any? = null
        var imgPath: String? = null
        var wallet: Double = 0.toDouble()
        var staffModifyTime: Long = 0
        var integral: Int = 0
        var mobile: String? = null
        var partnerCode: String? = null
        var applicationBeans: Any? = null
    }
}
