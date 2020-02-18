package com.st.common.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author: zhangH
 * @date: 2019/10/13 09:52
 * @description:
 */

@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 8545996863226528798L;

    @ApiModelProperty(notes = "总条数")
    private long total;

    @ApiModelProperty(notes = "每页条数")
    private long size;

    @ApiModelProperty(notes = "当前页数")
    private long current;

    @ApiModelProperty(notes = "结果数据集")
    private List<T> dataList;

    public PageData() {
        this.dataList = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
    }

    public PageData(IPage<T> iPage) {
        this.dataList = iPage.getRecords();
        this.total = iPage.getTotal();
        this.size = iPage.getSize();
        this.current = iPage.getCurrent();
    }


}
