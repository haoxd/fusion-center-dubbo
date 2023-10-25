package com.fusion.center.common.model.result;

import com.fusion.center.common.constant.SystemConstant;
import lombok.Getter;

/**
 * 返回状态
 *
 * @author haoxd
 */
@Getter
public enum ResultStatus {

    SUCCESS(SystemConstant.SUCCESSFUL, "成功"),
    FAIL(SystemConstant.ERROR, "失败"),

    NOT_EXIST(SystemConstant.NOT_FOUND_EXCEPTION, "找不到"),

    /**
     * 操作错误，[1000, 2000)
     * ------------------------------------------------------------------------------------------
     */
    API_FAILED("1001", "Api 接口错误"),
    BUSINESS_FAILED("1002", "业务执行异常"),

    /**
     * 参数错误，[2000, 3000)
     */
    PARAM_ILLEGAL("2000", "参数错误"),
    NO_FIND_SUB_CLASS("2055", "找不到实现类"),
    NO_FIND_METHOD("2057", "找不到实现方法"),


    ;

    private final String code;

    private final String message;

    ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
