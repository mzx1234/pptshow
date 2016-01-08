package com.mzx.pptserver.monitor;


import com.mzx.pptserver.constant.TaskTypeConstant.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * 任务执行器管理类。
 * @author zison
 */
@Component
public class ExecutorManager {

    /**
     * 日志对象。
     */
    private static Logger logger = LoggerFactory.getLogger(ExecutorManager.class);

    /**
     * 每个任务对应一个单工作线程线程池。
     */
    private ConcurrentHashMap<TaskType, ExecutorService> threadPoolMap;

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
        threadPoolMap = new ConcurrentHashMap<>(taskTypes.length);
        for (TaskType taskType : taskTypes) {
            //每种任务分配一个最多只有1个工作线程工作的线程池，保证一种任务不会同时执行。
            threadPoolMap.put(taskType, new ExceptionCatchableExecutor(1, 1, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
        }
    }

    /**
     * 可以捕捉到线程执行异常的线程池，主要针对ThreadPoolExecutor实现afterExecute方法。<br/>
     * 如果我们关心线程池执行的结果，则需要使用submit来提交task，
     * 那么在afterExecute中对异常的处理也需要通过Future接口调用get方法去取结果，才能拿到异常。<br/>
     * 如果我们不关心这个任务的结果，可以直接使用ExecutorService中的execute方法（实际是继承Executor接口）来直接去执行任务。<br/>
     * 这样的话，Runnable没有经过多余的封装，在runWorker中得到的异常也直接能在afterExecute中捕捉。<br/>
     * @author lijing
     */
    private class ExceptionCatchableExecutor extends ThreadPoolExecutor {

        /**
         * {@link ThreadPoolExecutor#ThreadPoolExecutor}
         * @param corePoolSize {@link ThreadPoolExecutor#ThreadPoolExecutor}
         * @param maximumPoolSize {@link ThreadPoolExecutor#ThreadPoolExecutor}
         * @param keepAliveTime {@link ThreadPoolExecutor#ThreadPoolExecutor}
         * @param unit {@link ThreadPoolExecutor#ThreadPoolExecutor}
         * @param workQueue {@link ThreadPoolExecutor#ThreadPoolExecutor}
         */
        public ExceptionCatchableExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                          TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        /**
         * 主要用于捕捉线程池中的线程产生的异常。
         * @param r 实际执行的任务。
         * @param t 产生的异常。
         */
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t == null && r instanceof Future<?>) {
                try {
                    Future<?> future = (Future<?>) r;
                    if (future.isDone()) {
                        //get方法可以返回正常结束的执行结果，也可以抛出执行的异常。
                        future.get();
                    }
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } catch (Throwable e) {
                    t = e.getCause();
                }
            }
            if (t != null) {
                logger.error(t.getMessage(), t);
                System.out.println(t.getMessage());
            }
        }
    }
}
