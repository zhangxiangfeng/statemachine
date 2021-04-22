package com.github.simon.statemachine.order;


import lombok.Getter;

public enum OrderPermitState {

    SUBMIT_ORDER("SUBMIT_ORDER", "已下单"),
    DELIVERED_PERMIT("DELIVERED_PERMIT", "发货审核"),
    SIGNED_PERMIT("SIGNED_PERMIT", "签收审核"),

    QA_ORDER("QA_ORDER", "问题订单"),
    SUCCESS("SUCCESS", "完美订单"),
    ;

    @Getter
    private String code;

    @Getter
    private String label;

    OrderPermitState(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
