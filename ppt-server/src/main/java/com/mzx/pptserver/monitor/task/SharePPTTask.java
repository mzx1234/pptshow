package com.mzx.pptserver.monitor.task;


import com.mzx.pptserver.constant.TaskTypeConstant;
import com.mzx.pptserver.utility.BrocastObjectUtil;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * 同步ppt任务
 * Created by zison on 2016/1/2.
 */
@Component
public class SharePPTTask extends BaseTask{


    @Override
    public void execute() {
        executorManager.getThreadPoolExecutor(TaskTypeConstant.TaskType.PPT_OPTION).submit(new ConcreteSharePPTTask());
    }

    @Override
    public String getTaskName() {
        return this.getClass().getSimpleName();
    }


    private class ConcreteSharePPTTask implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {

            BrocastObjectUtil.brocastObject(new Object(), "广播ip");
            return true;
        }
    }
}

