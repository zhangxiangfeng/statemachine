package com.github.simon.statemachine.order;

import com.github.shxz130.statemachine.core.config.Handler;
import com.github.shxz130.statemachine.core.fire.StateMachine;
import com.github.shxz130.statemachine.core.fire.StateMachineConfig;
import com.github.shxz130.statemachine.core.fire.StateMachineFactory;
import com.github.simon.statemachine.order.handler.OrderCreateHandler;
import com.github.simon.statemachine.order.handler.OrderPermit2Handler;
import com.github.simon.statemachine.order.handler.PermitFailHandler;
import com.github.simon.statemachine.order.handler.PermitSuccessHandler;

/**
 * Created by jetty on 2019/7/31.
 */
public class OrderStateMachineInit {

    public static void init() {
        StateMachineFactory.register(OrderContextConstants.ORDER_CREATE, buildOrderStreamStateMachine());
    }

    private static StateMachine buildOrderStreamStateMachine() {
        StateMachineConfig<OrderPermitState, OrderStateEvent, Handler> stateMachineConfig = new StateMachineConfig();

        stateMachineConfig.from(OrderPermitState.SUBMIT_ORDER)
                .permit(OrderStateEvent.SUBMIT_ORDER)
                .handle(new OrderCreateHandler())
                .to(OrderPermitState.DELIVERED_PERMIT)
                .build();

        stateMachineConfig.from(OrderPermitState.DELIVERED_PERMIT) //发货审批
                .permit(OrderStateEvent.DELIVERED_PERMIT)  //待审批
                .handle(new PermitFailHandler())     //发货人查阅
                .to(OrderPermitState.DELIVERED_PERMIT)    //查阅完，仍旧是发货审核状态
                .build();

        stateMachineConfig.from(OrderPermitState.DELIVERED_PERMIT) //发货审批
                .permit(OrderStateEvent.DELIVERED_PERMIT_SUCCESS)  //同意审批
                .handle(new OrderPermit2Handler())     //发货审核
                .to(OrderStateEvent.SIGNED_PERMIT)    //发货审核完毕后，进入签收审核
                .build();
        stateMachineConfig.from(OrderPermitState.DELIVERED_PERMIT) //发货审批
                .permit(OrderStateEvent.DELIVERED_PERMIT_REFUSE)  //拒绝审批
                .handle(new PermitFailHandler())     //拒绝后进行处理
                .build();


        stateMachineConfig.from(OrderPermitState.SIGNED_PERMIT) //签收审批
                .permit(OrderStateEvent.SIGNED_PERMIT_SUCCESS)  //同意审批
                .handle(new PermitSuccessHandler())     //成功处理
                .build();
        stateMachineConfig.from(OrderPermitState.SIGNED_PERMIT) //发货审批
                .permit(OrderStateEvent.DELIVERED_PERMIT_SUCCESS)  //拒绝审批
                .handle(new PermitFailHandler())     //拒绝后进行处理
                .build();

        return new StateMachine(stateMachineConfig);
    }

}
