package com.mzx.pptserver.monitor.task;



import com.mzx.pptserver.monitor.ExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zison on 2016/1/2.
 */
public abstract class BaseTask {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ExecutorManager executorManager;

    /**
     * 任务实际执行的逻辑。
     */
    public abstract void execute();

    /**
     * 获取任务名称。
     * @return 任务名称。
     */
    public abstract String getTaskName();
}
