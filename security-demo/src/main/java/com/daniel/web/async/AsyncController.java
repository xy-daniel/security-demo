package com.daniel.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public String order() throws InterruptedException {
        logger.info("main thread start...");
        Thread.sleep(1000);
        logger.info("main thread end...");
        return "success";
    }

    @RequestMapping("/order/async")
    public Callable<String> orderAsync() throws InterruptedException {
        logger.info("main thread start...");
        Callable<String> result = () -> {
            logger.info("children thread start");
            Thread.sleep(1000);
            logger.info("children thread end");
            return "success";
        };
        logger.info("main thread end...");
        return result;
    }

    /**
     * 订单提交
     * 只负责提交到队列
     */
    @RequestMapping("/order/deferred")
    public DeferredResult<String> orderDeferred() throws InterruptedException {
        logger.info("main thread start...");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        logger.info("main thread end...");
        return result;
    }

}
