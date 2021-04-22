package com.github.simon.statemachine.order;

import lombok.Data;

/**
 * 订单业务对象
 */
@Data
public class OrderBO {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private String orderProcessState;
    /**
     * 订单UID
     */
    private Long userId;
}