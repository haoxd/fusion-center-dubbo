
package com.fusion.center.common.design.builder;

import java.io.Serializable;

public interface Builder<T> extends Serializable {
    T build();
}
