package com.aynu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 18:28
 * @description
 */
@Schema(title = "响应结果类",name = "test-show")
@Data
@Accessors(chain = true)
public class Result {
    @Schema(title = "状态")
    int status;
    @Schema(title = "描述")
    String description;

    public Result() {
    }


    public Result(int status, String description) {
        this.status = status;
        this.description = description;
    }

}
