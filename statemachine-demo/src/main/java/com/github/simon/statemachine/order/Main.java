package com.github.simon.statemachine.order;

import com.github.shxz130.statemachine.core.fire.StateMachineFactory;
import com.github.shxz130.statemachine.core.fire.TransactionContext;
import com.github.shxz130.statemachine.demo.config.LeavePermitContextConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jetty on 2019/7/31.
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        OrderStateMachineInit.init();

        log.info("创建订单");
        TransactionContext transactionContext = new TransactionContext();
        transactionContext.setData(OrderContextConstants.CURRENT_STATE, OrderPermitState.SUBMIT_ORDER);
        StateMachineFactory.getStateMachine(OrderContextConstants.ORDER_CREATE).fire(OrderStateEvent.SUBMIT_ORDER, transactionContext);

        log.info("发货审批");
        OrderBO orderBO = (OrderBO) transactionContext.getData(OrderContextConstants.ORDER_CREATE);
        TransactionContext transactionContext2 = new TransactionContext();
        transactionContext2.setData(OrderContextConstants.ORDER_CREATE, orderBO);
        transactionContext2.setData(OrderContextConstants.CURRENT_STATE, OrderPermitState.DELIVERED_PERMIT);
        StateMachineFactory.getStateMachine(OrderContextConstants.ORDER_CREATE).fire(OrderStateEvent.DELIVERED_PERMIT_SUCCESS, transactionContext2);

        log.info("签收审批");
        OrderBO orderBO2 = (OrderBO) transactionContext.getData(OrderContextConstants.ORDER_CREATE);
        TransactionContext transactionContext3 = new TransactionContext();
        transactionContext3.setData(OrderContextConstants.ORDER_CREATE, orderBO2);
        transactionContext3.setData(OrderContextConstants.CURRENT_STATE, OrderPermitState.SIGNED_PERMIT);
        StateMachineFactory.getStateMachine(OrderContextConstants.ORDER_CREATE).fire(OrderStateEvent.SIGNED_PERMIT_SUCCESS, transactionContext3);
    }
}
