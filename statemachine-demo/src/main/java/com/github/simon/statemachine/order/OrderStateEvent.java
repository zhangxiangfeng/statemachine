package com.github.simon.statemachine.order;


import lombok.Getter;

public enum OrderStateEvent {
    SUBMIT_ORDER("SUBMIT_ORDER", "已下单"),

    DELIVERED_PERMIT("DELIVERED_PERMIT", "发货审核"),
    DELIVERED_PERMIT_SUCCESS("DELIVERED_PERMIT_SUCCESS", "发货审核通过"),
    DELIVERED_PERMIT_REFUSE("DELIVERED_PERMIT_REFUSE", "发货审核拒绝"),

    SIGNED_PERMIT("SIGNED_PERMIT", "签收审核"),
    SIGNED_PERMIT_SUCCESS("SIGNED_PERMIT_SUCCESS", "签收审核通过"),
    SIGNED_PERMIT_REFUSE("SIGNED_PERMIT_REFUSE", "签收审核拒绝"),
    ;
    @Getter
    private String code;

    @Getter
    private String label;

    OrderStateEvent(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
