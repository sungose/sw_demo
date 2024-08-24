package com.echo.enums;

public enum InboundAndOutboundEnum {

    INBOUND(0, "入库"),
    OUTBOUND(1, "出库");

    private final int code;

    private final String msg;


    /**
     * 构造方法
     */
    InboundAndOutboundEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}