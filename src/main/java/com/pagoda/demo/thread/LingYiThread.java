package com.pagoda.demo.thread;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 回调零一接口
 *
 * @author liuhuiqing
 * @create 2018/6/26 15:22
 **/
@Data
@Component
@Scope("prototype")
public class LingYiThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 用户ID
     */
    private Integer customerID;
    /**
     * 用户行为
     */
    private String behavior;

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("ling yi Error: " + e.toString());
            e.printStackTrace();
        }
    }

}
