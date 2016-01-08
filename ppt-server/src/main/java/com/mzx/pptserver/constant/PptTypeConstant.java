package com.mzx.pptserver.constant;

/**
 * ppt文件类型常量
 * Created by zison on 2016/1/2.
 */
public class PptTypeConstant {

    public enum PPTType {
        PPTX(1,"PPTX 文件"),
        PPT(2, "PPT 文件");

        int pptType;
        String des;

        PPTType(int i, String s) {
            this.pptType = i;
            this.des = s;
        }


        public int getPptType() {
            return pptType;
        }

        public String getDes() {
            return des;
        }

    }
}
