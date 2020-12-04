package com.magnesiatech.springaopexample.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPoints {

    @Pointcut( "execution(* com.magnesiatech.springaopexample.repository.*.*(..))" )
    public void repositoryLayerExecution(){}

    //Annotation point cut
    @Pointcut("@annotation(com.magnesiatech.springaopexample.aspect.annotation.TrackTime)")
    public void trackTimeAnnotation(){}

}
