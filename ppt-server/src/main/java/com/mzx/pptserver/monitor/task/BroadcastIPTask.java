package com.mzx.pptserver.monitor.task;

import com.mzx.pptserver.constant.TaskTypeConstant;
import org.springframework.stereotype.Service;


import java.net.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 广播IP任务实现类
 * Created by zison on 2016/1/17.
 */
@Service
public class BroadcastIPTask extends BaseTask {
    @Override
    public void execute() {
        Future<Boolean> future = executorManager.getThreadPoolExecutor(TaskTypeConstant.TaskType.SHARE_IP).submit(this);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTaskName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Boolean call() throws Exception {
        while (true) {
            Thread.sleep(5000);
            DatagramSocket dgSocket = new DatagramSocket(8989);
            byte[] by = globalApplication.getIp().getBytes();
            DatagramPacket packet = new DatagramPacket(by,
                    by.length, InetAddress
                    .getByName(globalApplication.getBroadcastIP()),
                    8989);
            dgSocket.send(packet);


            dgSocket.close();
            logger.info("广播出去了");

        }
    }
}
