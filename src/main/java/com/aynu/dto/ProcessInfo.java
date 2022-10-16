package com.aynu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author susuper
 * @Date 2020/9/25 8:22
 * @description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProcessInfo {
    public long totalSize = 0;
    public long readSize = 0;
    public String show = "";
    public String rate;
}
