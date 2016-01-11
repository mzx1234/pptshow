package com.mzx.pptcommon.constant;

/**
 * Created by zison on 2016/1/11.
 */
public class SystemConstant {

    /**
     * ·µ»ØÖµ×´Ì¬Âë
     */
    public enum ResponseStatusCode {
        NORMAL("00000", "OK"),
        ABNORMAL("99999", "System Error");

        private String code;
        private String description;

        ResponseStatusCode(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "ResponseStatusCode{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
