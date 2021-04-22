/**
 * 订单状态机例子。
 * 一个订单的process生命周期：兑换下单->发货->签收
 * <p>
 * 每当达到一个阶段后，应触发下一阶段的行为。
 */
package com.github.simon.statemachine.order;