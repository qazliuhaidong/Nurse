package xapp.nurse.ui.model

/**
 * Created by Administrator on 2017-11-27.
 */

class OrderManage {

    /**
     * code : 10000
     * msg : 操作成功
     * data : [{"serviceArrangesId":1,"requestId":28,"execId":5,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":1,"cardName":"巨无敌卡","productName":"体检服务","createTime":null,"arrangeTime":1511515304750,"enableGrab":1,"orderType":null,"helpStaffName":"喵星人","orderDescription":"这是这个服务申请的备注","integral":2322}]
     */

    var code: Int = 0
    var msg: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * serviceArrangesId : 1
         * requestId : 28
         * execId : 5
         * requestNo : null
         * serviceStatus : null
         * mshpId : 67
         * mshpNo : 000001
         * mshpName : 贺得转
         * gender : 男
         * birthday : 613926000000
         * mobile : 18780149749
         * insuranceType : null
         * insuranceNo : 42112700322311930
         * acceptStatus : 1
         * cardName : 巨无敌卡
         * productName : 体检服务
         * createTime : null
         * arrangeTime : 1511515304750
         * enableGrab : 1
         * orderType : null
         * helpStaffName : 喵星人
         * orderDescription : 这是这个服务申请的备注
         * integral : 2322
         */

        var serviceArrangesId: Int = 0
        var requestId: Int = 0
        var execId: Int = 0
        var requestNo: Any? = null
        var serviceStatus: Any? = null
        var mshpId: Int = 0
        var mshpNo: String? = null
        var mshpName: String? = null
        var gender: String? = null
        var birthday: Long = 0
        var mobile: String? = null
        var insuranceType: Any? = null
        var insuranceNo: String? = null
        var acceptStatus: Int = 0
        var cardName: String? = null
        var productName: String? = null
        var createTime: Any? = null
        var arrangeTime: Long = 0
        var enableGrab: Int = 0
        var orderType: Any? = null
        var helpStaffName: String? = null
        var orderDescription: String? = null
        var integral: Int = 0
        var photo: String? = null
    }
}
