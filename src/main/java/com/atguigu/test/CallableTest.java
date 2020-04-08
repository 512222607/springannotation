package com.airlook.data.process;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * description: CallableTest <br>
 * date: 2020/2/17 19:06 <br>
 * author: Lsq <br>
 * version: 1.0 <br>
 */
public class CallableTest {

    @Test
    public void scheduler(){

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        ScheduledFuture<List<String>> future = scheduledThreadPoolExecutor.schedule(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ", hey guys1");
                    Thread.sleep(500);
                }
            }
        }, 0, TimeUnit.MILLISECONDS);

        try {
            future.get(5, TimeUnit.SECONDS);
            System.out.println("正常执行了");
        } catch (InterruptedException e) {
            System.out.println("发生中断异常");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("发生任务内部抛出异常");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("发生获取任务超时异常");
            e.printStackTrace();
        } finally {
            future.cancel(true);
        }

        try {
            System.out.println("开始进行等待");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
