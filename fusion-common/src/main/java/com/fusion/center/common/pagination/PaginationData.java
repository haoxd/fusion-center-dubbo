package com.fusion.center.common.pagination;

import lombok.Data;

import java.util.List;

@Data
public class PaginationData<T> {

    private List<T> bizData;

    private Pagination pagination;
}
