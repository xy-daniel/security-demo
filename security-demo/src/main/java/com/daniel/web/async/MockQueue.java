package com.daniel.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //下单消息
    private String placeOrder;

    //订单完成消息
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            try {
                logger.info("接收到下单请求:" + placeOrder);
                this.placeOrder = placeOrder;
                Thread.sleep(1000);
                this.completeOrder = placeOrder;
                logger.info("下单请求处理完毕" + placeOrder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
