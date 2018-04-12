package com.manager.common.dto.result;

/**
 * response code
 */
public enum Code {

    SUCCESS(200, "success"),

    ER_PARAMS(300, "error parameter"),

    REQUEST_FAIL(400, "request fail"),

    FAIL(500, "system error"),

    BUSY(501, "busy");

    private final int code;
    private final String msg;

    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * parse code
     * default fail
     *
     * @param code code
     * @return return
     */
    public static Code parseCode(int code) {
        Code[] codes = Code.values();
        for (Code temp : codes) {
            if (code == temp.code()) {
                return temp;
            }
        }
        return Code.FAIL;
    }

    /**
     * get code
     *
     * @return return code
     */
    public int code() {
        return code;
    }

    /**
     * get message
     *
     * @return return message desc
     */
    public String msg() {
        return msg;
    }

}
