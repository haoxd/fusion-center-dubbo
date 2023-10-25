

package com.fusion.center.validation;

import java.io.Serializable;

/**
 * ExistProvider.
 */
public interface ExistProvider {
    
    
    
    /**
     * existed.
     *
     * @param key key
     * @return existed, if not existed nullable
     */
    Boolean existed(Serializable key);
}
