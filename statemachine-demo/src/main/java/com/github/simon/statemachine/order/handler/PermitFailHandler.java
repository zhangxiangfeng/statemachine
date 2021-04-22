package com.github.simon.statemachine.order.handler;

import com.github.shxz130.statemachine.core.config.Handler;
import com.github.shxz130.statemachine.core.fire.StateMachine;
import com.github.shxz130.statemachine.core.fire.TransactionContext;
import com.github.simon.statemachine.order.OrderBO;
import com.github.simon.statemachine.order.OrderContextConstants;
import com.github.simon.statemachine.order.OrderPermitState;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jetty on 2019/7/31.
 */
@Slf4j
public class PermitFailHandler implements Handler {

    public void handle(TransactionContext context, StateMachine stateMachine) {
        OrderBO orderBO = (OrderBO) context.getData(OrderContextConstants.ORDER_CREATE);
        orderBO.setOrderProcessState(OrderPermitState.SUCCESS.getCode());

        log.info("[{}],permit=[{}],审批意见:[{}]", this.getClass().getSimpleName(), orderBO);
    }
}
