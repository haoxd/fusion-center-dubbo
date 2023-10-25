package com.fusion.center.common.pagination;


import com.fusion.center.common.constant.PaginationConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaginationDTO {

    @NotNull(message = "pageNo不允许为空")
    private Integer pageNo = PaginationConstant.DEFAULT_PAGE_NO;

    @NotNull(message = "pageSize不允许为空")
    private Integer pageSize = PaginationConstant.DEFAULT_PAGE_SIZE;

    private String searchKeywords;
}
