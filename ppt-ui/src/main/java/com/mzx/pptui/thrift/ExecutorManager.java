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
     * ͨ���������ͻ�ȡ�̳߳ض���
     * @param taskType ��������
     * @return ��Ӧ���̳߳ض���
     */
    public ExecutorService getThreadPoolExecutor(TaskType taskType) {
        return threadPoolMap.get(taskType);
    }


    /**
     * spring�������ʼ�����֮�󣬿�ʼ�����õ�ThreadPoolExecutor����ʼ����
     */
    @PostConstruct
    public final void initThreadPoolExecutor() {
        TaskType[] taskTypes = TaskType.values();
        threadPoolMap = new HashMap<TaskType, ExecutorService>(taskTypes.length);
        for (TaskType taskType : taskTypes) {
            //ÿ���������һ�����ֻ��1�������̹߳������̳߳أ���֤һ�����񲻻�ͬʱִ�С�
            threadPoolMap.put(taskType, new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MICROSECONDS,
                    new LinkedBlockingDeque<Runnable>()));
        }
    }
}
