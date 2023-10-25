package com.fusion.center.common.pagination;

import lombok.Data;

@Data
public class Pagination {
    private long total;

    private long pageNo;

    private long pageSize;

    public Pagination(long total, long pageNo, long pageSize) {
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
