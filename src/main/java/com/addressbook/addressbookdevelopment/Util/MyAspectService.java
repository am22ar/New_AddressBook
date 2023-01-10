package com.addressbook.addressbookdevelopment.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAspectService {

    Logger logger = LoggerFactory.getLogger(MyAspectService.class);

    @Pointcut(value = "execution(* com.address_book_development.*.*.*(..))")
    public void myPointCut(){}

    @Around("myPointCut()")
    //proceedingjoinpoint is using reflection api internally
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        //object mapper is used to convert any object type into json format
        ObjectMapper objectMapper = new ObjectMapper();
        //getSignature is used to get the method name
        String methodName = joinPoint.getSignature().getName();
        //getTarget is used to get class name
        String className = joinPoint.getTarget().getClass().toString();
        //array is used to store the inputs from postman
        Object[] array = joinPoint.getArgs();

        System.out.println("class invoked: "+className+" method: "+methodName+"()"+ objectMapper.writeValueAsString(array) );
        Object object = joinPoint.proceed();
        System.out.println(className+" method: "+methodName+ objectMapper.writeValueAsString(array));
        return object;
    }
}
