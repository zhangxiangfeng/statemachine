package com.github.simon.statemachine.order.handler;

import com.github.shxz130.statemachine.core.config.Handler;
import com.github.shxz130.statemachine.core.fire.StateMachine;
import com.github.shxz130.statemachine.core.fire.TransactionContext;
import com.github.simon.statemachine.order.OrderBO;
import com.github.simon.statemachine.order.OrderContextConstants;
import com.github.simon.statemachine.order.OrderPermitState;
import com.github.simon.statemachine.order.OrderStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jetty on 2019/7/31.
 */
@Slf4j
public class OrderCreateHandler implements Handler {

    public void handle(TransactionContext context, StateMachine stateMachine) {
        OrderBO order = new OrderBO();
        order.setOrderNo(String.valueOf(System.currentTimeMillis()));
        order.setOrderProcessState(OrderPermitState.SUBMIT_ORDER.getCode());//设置为初始状态
        order.setUserId(100000L);

        log.info("[{}],permit=[{}]", this.getClass().getSimpleName(), order);

        context.setData(OrderContextConstants.ORDER_CREATE, order);

        stateMachine.fire(OrderStateEvent.DELIVERED_PERMIT, context);
    }

}
