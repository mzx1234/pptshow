package com.mzx.pptserver.monitor.task;


import com.mzx.pptserver.constant.TaskTypeConstant;
import org.springframework.stereotype.Component;


/**
 * 同步ppt任务
 * Created by zison on 2016/1/2.
 */
@Component
public class SharePPTTask extends BaseTask{


    @Override
    public void execute() {
        executorManager.getThreadPoolExecutor(TaskTypeConstant.TaskType.PPT_OPTION).submit(this);
    }

    @Override
    public String getTaskName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }


}

