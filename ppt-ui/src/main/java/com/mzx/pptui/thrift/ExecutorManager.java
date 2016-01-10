package com.mzx.pptui.thrift;

import com.mzx.pptui.constant.TaskTypeConstant.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


/**
 *
 * Created by zison on 2016/1/9.
 */
@Service
public class ExecutorManager {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<TaskType, ExecutorService> threadPoolMap ;

    /**
     * 通过任务类型获取线程池对象。
     * @param taskType 任务类型
     * @return 相应的线程池对象。
     */
    public ExecutorService getThreadPoolExecutor(TaskType taskType) {
        return threadPoolMap.get(taskType);
    }


    /**
     * spring将对象初始化完成之后，开始给内置的ThreadPoolExecutor做初始化。
     */
    @PostConstruct
    public final void initThreadPoolExecutor() {
        TaskType[] taskTypes = TaskType.values();
        threadPoolMap = new HashMap<TaskType, ExecutorService>(taskTypes.length);
        for (TaskType taskType : taskTypes) {
            //每种任务分配一个最多只有1个工作线程工作的线程池，保证一种任务不会同时执行。
            threadPoolMap.put(taskType, new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MICROSECONDS,
                    new LinkedBlockingDeque<Runnable>()));
        }
    }
}
