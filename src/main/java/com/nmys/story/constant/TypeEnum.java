package com.nmys.story.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangjianbing
 * @date 2019/9/3
 */
public class TypeEnum {

    public enum Account {

        CONSUMER("XF", "xfAccountBusiness", "消费者账户"),
        MANAGER("GLZ", "glzAccountBusiness", "管理者账户"),
        EMBRAVE("JL", "jlAccountBusiness", "激励账户"),
        WELFARE("FL", "flAccountBusiness", "福利账户"),
        PRODUCT("CP", "cpAccountBusiness", "产品账户"),
        GAT("GAT", "gatAccountBusiness", "关爱通账户"),
        ACTIVITY("HD", "hdAccountBusiness", "活动账户");

        @Getter
        @Setter
        private String key;

        @Getter
        @Setter
        private String value;

        @Getter
        @Setter
        private String name;

        Account(String key, String value, String name) {
            this.key = key;
            this.value = value;
            this.name = name;
        }

        public static String getValue(String key) {
            for (Account account : values()) {
                if (account.getKey().equals(key)) {
                    return account.getValue();
                }
            }
            return null;
        }

        public static String getName(String key) {
            for (Account account : values()) {
                if (account.getKey().equals(key)) {
                    return account.getName();
                }
            }
            return null;
        }
    }


    public enum TransStatus {

        WAITING("0", "未处理"),
        FINISHED("1", "完成"),
        FAILURE("2", "失败"),
        PROCESSING("3", "处理中"),
        CLOSED("4", "关闭"),
        CANCEL("5", "取消");

        private String code;
        private String msg;

        TransStatus(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsgByCode(String code) {
            for (TransStatus t : TransStatus.values()) {
                if (t.getCode().equals(code)) {
                    return t.msg;
                }
            }
            return null;
        }
        public static boolean isExist(String code){
            for (TransStatus t : TransStatus.values()) {
                if (t.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public enum TransType {

        TRANSFER("1", "转账"),
        FREEZE("2", "冻结"),
        UNFREEZE("3", "解冻");
        
        private String code;
        private String msg;

        TransType(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsgByCode(String code) {
            for (TransStatus t : TransStatus.values()) {
                if (t.getCode().equals(code)) {
                    return t.msg;
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
