package com.aynu.Utils;

import com.alibaba.fastjson.JSON;
import com.aynu.bean.OperationLog;
import com.aynu.annotation.OperationLogDetail;
import com.aynu.bean.User;
import com.aynu.bean.UserInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * LogAspect  aop切面类
 */

@Aspect
@Component
public class LogAspect {
    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //controller切入点
    @Pointcut("@annotation(com.aynu.annotation.OperationLogDetail)")
//    @Pointcut("execution(* com.aynu.controller.LoginController.*(..))")
    public void controllerAspect(){}





    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res = joinPoint.proceed();
            time = System.currentTimeMillis()-time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint,res,time);
            }catch (Exception e){
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void addOperationLog(JoinPoint joinPoint,Object res,long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperationLog operationLog = new OperationLog();
        operationLog.setRunTime(time);
//        operationLog.setReturnValue(JSON.toJSONString(res));
//        operationLog.setId(UUID.randomUUID().toString());
//        operationLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setCreateTime(new Date());
//        operationLog.setMethod(signature.getDeclaringTypeName()+"." + signature.getName());
//        operationLog.setUserId("#{currentUserId}");
//        operationLog.setUserName("#{currentUserName}");
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        if(annotation != null){
            operationLog.setLever(annotation.level());

            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
            UserInfo user = (UserInfo)session.getAttribute("USER_SESSION");
            if (user!=null){
                operationLog.setDescribe("账号："+user.getNumber()+",姓名："+user.getName()+"在执行："+getDetail(((MethodSignature)joinPoint.getSignature()).getParameterNames(),joinPoint.getArgs(),annotation));
            }else{
                operationLog.setDescribe(getDetail(((MethodSignature)joinPoint.getSignature()).getParameterNames(),joinPoint.getArgs(),annotation));
            }


            operationLog.setOperationType(annotation.operationType().getValue());
            operationLog.setOperationUnit(annotation.operationUnit().getValue());
        }
        logger.info("记录日志"+operationLog.toString());
    }

    public String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation){

        Map<Object, Object> map = new HashMap<>(4);
        for(int i = 0;i < argNames.length;i++){
            map.put(argNames[i],args[i]);
        }

        String detail = annotation.detail();
        try {
//            detail = "'" + "#{currentUserName}" + "'=》" + annotation.detail();
            detail = annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detail;
    }



}
