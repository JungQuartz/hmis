package com.jung.hmis.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jung.hmis.common.base.CommonResult;
import com.jung.hmis.common.exception.ResultException;
import com.jung.hmis.common.utils.NetworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author jimlly
 * @createtime: 2017/12/6下午5:32
 * 日志aop注入
 */
@Aspect
@Component
@Slf4j
@Order(-5)
public class HttpAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    ThreadLocal<String> ID = new ThreadLocal();

    /**
     * 定义一个切入点.
     * <p>
     * 解释下：
     * <p>
     * <p>
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * <p>
     * ~ 第二个 * 任意包名
     * <p>
     * ~ 第三个 * 代表任意方法.
     * <p>
     * ~ 第四个 * 定义在web包或者子包
     * <p>
     * ~ 第五个 * 任意方法
     * <p>
     * ~ .. 匹配任意数量的参数.
     */

    @Pointcut("execution(public * com.cfpamf..*.controller..*.*(..))")
    public void webLog() {
    }


    @Before("webLog()")

    public void doBefore(JoinPoint joinPoint) throws IOException {

        startTime.set(System.currentTimeMillis());

        String uid = UUID.randomUUID().toString();
        ID.set(uid);
        MDC.put("requestId",uid.substring(24,36));
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", uid);
        jsonObject.put("URL", request.getRequestURL());
        jsonObject.put("PATH", request.getServletPath());
        jsonObject.put("HTTP_METHOD", request.getMethod());
        jsonObject.put("CLASS_METHOD", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        jsonObject.put("IP", NetworkUtils.getIpAddress(request));
        jsonObject.put("ARGS", Arrays.toString(joinPoint.getArgs()));

        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        JSONObject paramsobj = new JSONObject();
        while (enu.hasMoreElements()) {

            String paraName = enu.nextElement();

            paramsobj.put(paraName, request.getParameter(paraName));
        }
        jsonObject.put("params", paramsobj);
        if (log.isInfoEnabled()) {
            log.info("request : {}", jsonObject.toString());
        }

    }


    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) {

        // 处理完请求，返回内容.
        if (log.isInfoEnabled()) {
            log.info("response : {},耗时（毫秒）{},ID: {}  ", JSON.toJSON(object), System.currentTimeMillis() - startTime.get(), ID.get());
        }
        MDC.clear();
    }

    @AfterThrowing(throwing="ex",pointcut="execution(public * com.cfpamf..*.controller..*.*(..))")
    public void doRecoveryActions(Throwable ex){
        CommonResult result = new CommonResult();
        if(ex instanceof ResultException){
            ResultException r = (ResultException) ex;
            result.setCode(r.getCode());
            result.setMessage(r.getMessage());

        }else{
            result.setFail();

        }
        if (log.isInfoEnabled()) {
            log.info("response : {},耗时（毫秒）{},ID: {}  ", JSON.toJSON(result), System.currentTimeMillis() - startTime.get(), ID.get());
        }
        MDC.clear();
    }


}
