package com.aynu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 存储 人员统计 柱状图的数据
 */

//List<TebleDto>
@Data
@Accessors(chain = true)
public class TableDto {
    String department;  //院系
    int sex;  //性别
    int count;  //数量

    public TableDto() {
        
    }

    public TableDto(String department, int sex, int count) {
        this.department = department;
        this.sex = sex;
        this.count = count;
    }

    public boolean tableCompare(TableDto tableDto){
        if (this.getDepartment().equals(tableDto.getDepartment()) && this.getSex()==tableDto.getSex()){
            return true;
        }else{
            return false;
        }
    }


}
