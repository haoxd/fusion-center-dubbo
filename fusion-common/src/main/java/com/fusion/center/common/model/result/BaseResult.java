package com.fusion.center.common.model.result;


import com.fusion.center.common.constant.SystemConstant;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BaseResult implements Serializable {

    private static final long serialVersionUID = -5771016784021901099L;

    protected String message;

    protected String code;

    public boolean successful() {
        return !this.fail();
    }

    public boolean fail() {
        return !SystemConstant.SUCCESSFUL.equals(code);
    }
}
