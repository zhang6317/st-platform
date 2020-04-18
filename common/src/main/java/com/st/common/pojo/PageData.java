package com.st.common.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author: zhangH
 * @date: 2019/10/13 09:52
 * @description:
 */

@Data
public class PageData<T> extends PageParam implements Serializable {

    private static final long serialVersionUID = 8545996863226528798L;

    @ApiModelProperty(notes = "总条数")
    private long total;

    @ApiModelProperty(notes = "结果数据集")
    private List<T> dataList;

    public PageData() {
        this.dataList = Collections.emptyList();
        this.total = 0L;
        this.setPageSize(50L);
        this.setCurrentPage(1L);
    }

    public PageData(IPage<T> iPage) {
        this.dataList = iPage.getRecords();
        this.total = iPage.getTotal();
        this.setPageSize(iPage.getSize());
        this.setCurrentPage(iPage.getCurrent());
    }


}
