package xapp.nurse.ui.model;

import java.util.List;

/**
 * Created by Administrator on 2017-11-27.
 */

public class RushOrder {


    /**
     * code : 10000
     * msg : 操作成功
     * data : [{"serviceArrangesId":9,"requestId":33,"execId":9,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":null,"productName":"体检服务","createTime":null,"arrangeTime":1511745789519,"enableGrab":1,"orderType":null,"helpStaffName":null,"orderDescription":"这是这个服务申请的备注","integral":2322},{"serviceArrangesId":3,"requestId":28,"execId":4,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":"巨无敌卡","productName":"体检服务","createTime":null,"arrangeTime":1511509292033,"enableGrab":1,"orderType":null,"helpStaffName":null,"orderDescription":"这是这个服务申请的备注","integral":2322},{"serviceArrangesId":1,"requestId":28,"execId":5,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":"巨无敌卡","productName":"体检服务","createTime":null,"arrangeTime":1511515304750,"enableGrab":1,"orderType":null,"helpStaffName":null,"orderDescription":"这是这个服务申请的备注","integral":2322},{"serviceArrangesId":2,"requestId":30,"execId":2,"requestNo":null,"serviceStatus":null,"mshpId":67,"mshpNo":"000001","mshpName":"贺得转","gender":"男","birthday":613926000000,"mobile":"18780149749","insuranceType":null,"insuranceNo":"42112700322311930","acceptStatus":5,"cardName":null,"productName":"体检服务","createTime":null,"arrangeTime":1511749210146,"enableGrab":1,"orderType":null,"helpStaffName":null,"orderDescription":"这是这个服务申请的备注","integral":2322}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * serviceArrangesId : 9
         * requestId : 33
         * execId : 9
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
         * cardName : null
         * productName : 体检服务
         * createTime : null
         * arrangeTime : 1511745789519
         * enableGrab : 1
         * orderType : null
         * helpStaffName : null
         * orderDescription : 这是这个服务申请的备注
         * integral : 2322
         */

        private int serviceArrangesId;
        private int requestId;
        private int execId;
        private Object requestNo;
        private Object serviceStatus;
        private int mshpId;
        private String mshpNo;
        private String mshpName;
        private String gender;
        private long birthday;
        private String mobile;
        private Object insuranceType;
        private String insuranceNo;
        private int acceptStatus;
        private String cardName;
        private String productName;
        private Object createTime;
        private long arrangeTime;
        private String photo;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardName() {
            return cardName;
        }

        private int enableGrab;
        private Object orderType;
        private Object helpStaffName;
        private String orderDescription;
        private int integral;

        public int getServiceArrangesId() {
            return serviceArrangesId;
        }

        public void setServiceArrangesId(int serviceArrangesId) {
            this.serviceArrangesId = serviceArrangesId;
        }

        public int getRequestId() {
            return requestId;
        }

        public void setRequestId(int requestId) {
            this.requestId = requestId;
        }

        public int getExecId() {
            return execId;
        }

        public void setExecId(int execId) {
            this.execId = execId;
        }

        public Object getRequestNo() {
            return requestNo;
        }

        public void setRequestNo(Object requestNo) {
            this.requestNo = requestNo;
        }

        public Object getServiceStatus() {
            return serviceStatus;
        }

        public void setServiceStatus(Object serviceStatus) {
            this.serviceStatus = serviceStatus;
        }

        public int getMshpId() {
            return mshpId;
        }

        public void setMshpId(int mshpId) {
            this.mshpId = mshpId;
        }

        public String getMshpNo() {
            return mshpNo;
        }

        public void setMshpNo(String mshpNo) {
            this.mshpNo = mshpNo;
        }

        public String getMshpName() {
            return mshpName;
        }

        public void setMshpName(String mshpName) {
            this.mshpName = mshpName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getInsuranceType() {
            return insuranceType;
        }

        public void setInsuranceType(Object insuranceType) {
            this.insuranceType = insuranceType;
        }

        public String getInsuranceNo() {
            return insuranceNo;
        }

        public void setInsuranceNo(String insuranceNo) {
            this.insuranceNo = insuranceNo;
        }

        public int getAcceptStatus() {
            return acceptStatus;
        }

        public void setAcceptStatus(int acceptStatus) {
            this.acceptStatus = acceptStatus;
        }





        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public long getArrangeTime() {
            return arrangeTime;
        }

        public void setArrangeTime(long arrangeTime) {
            this.arrangeTime = arrangeTime;
        }

        public int getEnableGrab() {
            return enableGrab;
        }

        public void setEnableGrab(int enableGrab) {
            this.enableGrab = enableGrab;
        }

        public Object getOrderType() {
            return orderType;
        }

        public void setOrderType(Object orderType) {
            this.orderType = orderType;
        }

        public Object getHelpStaffName() {
            return helpStaffName;
        }

        public void setHelpStaffName(Object helpStaffName) {
            this.helpStaffName = helpStaffName;
        }

        public String getOrderDescription() {
            return orderDescription;
        }

        public void setOrderDescription(String orderDescription) {
            this.orderDescription = orderDescription;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }
    }
}
