package com.mzx.pptui.constant;

/**
 * task 类型常量
 * Created by zison on 2016/1/2.
 */
public class TaskTypeConstant {

    public enum TaskType {
        SHARE_IP(1, "广播IP任务"),
        PPT_OPTION(2, "PPT操作任务"),
        CONNECT(3, "连接交换信息任务");

        public int getTaskType() {
            return taskType;
        }

        public String getDes() {
            return des;
        }

        private int taskType;
        private String des;


        TaskType(int taskType, String des) {
            this.taskType = taskType;
            this.des = des;
        }


    }
}
