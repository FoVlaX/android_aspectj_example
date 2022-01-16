package com.amp.runtime;

import android.os.Looper;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.xml.transform.Result;

import okhttp3.HttpUrl;
import okhttp3.Request;

@Aspect
public class TraceAspect {

    @Pointcut("execution(* *(..))")
    private void anyOperation() {}

    @Pointcut("execution(* okhttp3..*(..))")
    private void inApplicationPackage() {}

    @Pointcut("execution(* com.amp.app.featuremodule..*.*(..))")
    private void inFeatureModule() {}

    @Pointcut("anyOperation() && (inApplicationPackage() || inFeatureModule())")
    private void execute() {}


    @Around("call(* okhttp3.OkHttpClient.newCall(..))")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Log.d("IHERE","IHERE");
            Request request = (Request) joinPoint.getArgs()[0];
            HttpUrl url = request.url();
            HttpUrl newUrlWithHttp = url.newBuilder().scheme("http").build();
            Request newRequestWithHttp = request.newBuilder().url(newUrlWithHttp).build();
            Object [] newArgs = { newRequestWithHttp };
            return joinPoint.proceed(newArgs);
        } catch (Exception e) {
            Log.d("Main", e.toString());
            return joinPoint.proceed();
        }
    }

    public static TraceAspect aspectOf() {
        return new TraceAspect();
    }

}