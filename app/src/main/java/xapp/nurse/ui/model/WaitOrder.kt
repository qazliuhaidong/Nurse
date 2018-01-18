package xapp.nurse.ui.model

import java.io.Serializable

/**
 * Created by Administrator on 2017-11-27.
 */

class WaitOrder {


    /**
     * code : 10000
     * msg : 操作成功
     * data : {"waitCount":2,"todayOrder":0,"notStartCount":0,"waitList":[{"serviceArrangesId":3,"requestId":28,"execId":4,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":"巨无敌卡","productName":"体检服务","createTime":null,"arrangeTime":1511509292033,"enableGrab":null,"orderType":null,"helpStaffName":"喵星人","orderDescription":"这是这个服务申请的备注","integral":2322},{"serviceArrangesId":9,"requestId":33,"execId":9,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":null,"productName":"体检服务","createTime":null,"arrangeTime":1511745789519,"enableGrab":null,"orderType":null,"helpStaffName":"喵星人","orderDescription":"这是这个服务申请的备注","integral":2322}],"notStartList":[]}
     */

    var code: Int = 0
    var msg: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * waitCount : 2
         * todayOrder : 0
         * notStartCount : 0
         * waitList : [{"serviceArrangesId":3,"requestId":28,"execId":4,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":"巨无敌卡","productName":"体检服务","createTime":null,"arrangeTime":1511509292033,"enableGrab":null,"orderType":null,"helpStaffName":"喵星人","orderDescription":"这是这个服务申请的备注","integral":2322},{"serviceArrangesId":9,"requestId":33,"execId":9,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":null,"productName":"体检服务","createTime":null,"arrangeTime":1511745789519,"enableGrab":null,"orderType":null,"helpStaffName":"喵星人","orderDescription":"这是这个服务申请的备注","integral":2322}]
         * notStartList : []
         */

        var waitCount: Int = 0
        var todayOrder: Int = 0
        var notStartCount: Int = 0
        var waitList: List<WaitListBean>? = null
        var notStartList: List<NotStartList>? = null

        class NotStartList:Serializable {
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
            var photo: String? = null
            var insuranceType: Any? = null
            var insuranceNo: String? = null
            var acceptStatus: Int = 0
            var cardName: String? = null
            var productName: String? = null

            var createTime: Any? = null
            var arrangeTime: Long = 0
            var enableGrab: Any? = null
            var orderType: Any? = null
            var helpStaffName: String? = null
            var orderDescription: String? = null
            var integral: Int = 0
        }


        class WaitListBean {
            /**
             * serviceArrangesId : 3
             * requestId : 28
             * execId : 4
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
             * acceptStatus : 5
             * cardName : 巨无敌卡
             * productName : 体检服务
             * createTime : null
             * arrangeTime : 1511509292033
             * enableGrab : null
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
            var enableGrab: Any? = null
            var orderType: Any? = null
            var helpStaffName: String? = null
            var orderDescription: String? = null
            var integral: Int = 0
            var photo: String? = null
        }
    }
}
