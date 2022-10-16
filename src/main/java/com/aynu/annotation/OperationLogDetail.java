package com.aynu.annotation;

import com.aynu.enums.OperationType;
import com.aynu.enums.OperationUnit;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)//自定义注解用于方法
@Retention(RetentionPolicy.RUNTIME)//在运行时有效（即运行时保留，可以通过此级别获取注解信息）
public @interface OperationLogDetail {
    String detail() default "";
    int level() default 0;
    OperationType operationType() default OperationType.UNKNOWN;
    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}
