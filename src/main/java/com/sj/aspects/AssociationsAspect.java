package com.sj.aspects;

import com.sj.utilities.Example;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Not working solution
@Aspect("perthis(com.sj.aspects.AssociationsAspect.exampleBeans())")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AssociationsAspect {
    public AssociationsAspect(){
        System.out.println("Creating aspect instance!");
    }

    @Pointcut("execution(* com.sj.utilities.Example.*(..))")
    public void exampleBeans(){};

    @Pointcut("execution(* com.sj.utilities.Example.*(..)) && this(bean)")
    public void exampleOperations(Object bean){};

    @Before(value = "exampleOperations(bean)", argNames = "jp,bean")
    public void beforeExampleMethodsExecution(JoinPoint jp, Object bean){
        System.out.println(
                "JoinPoint: " + jp.getStaticPart() +
                        "\n\taspect: " + this +
                        "\n\tproxy object: " + bean +
                        "\n\ttarget object: " + jp.getTarget()
        );
    }
}

//Multiple instances solution - collecting context through reflection JoinPoint
//@Aspect("pertarget(com.sj.aspects.AssociationsAspect.exampleOperations())")
//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//public class AssociationsAspect {
//    public AssociationsAspect(){
//        System.out.println("Creating aspect instance!");
//    }
//
//    @Pointcut("execution(* com.sj.utilities.Example.*(..))")
//    public void exampleOperations(){};
//
//    @Before("exampleOperations()")
//    public void beforeExampleMethodsExecution(JoinPoint jp){
//        System.out.println(
//                "JoinPoint: " + jp.getStaticPart() +
//                "\n\taspect: " + this +
//                "\n\tproxy object: " + jp.getThis() +
//                "\n\ttarget object: " + jp.getTarget()
//        );
//    }
//
//}

//Singleton solution - collecting context through reflection JoinPoint
//@Aspect
//@Component
//public class AssociationsAspect {
//    public AssociationsAspect(){
//        System.out.println("Creating aspect instance!");
//    }
//
//    @Pointcut("execution(* com.sj.utilities.Example.*(..))")
//    public void exampleOperations(){};
//
//    @Before("exampleOperations()")
//    public void beforeExampleMethodsExecution(JoinPoint jp){
//        System.out.println(
//                "JoinPoint: " + jp.getStaticPart() +
//                        "\n\taspect: " + this +
//                        "\n\tproxy object: " + jp.getThis() +
//                        "\n\ttarget object: " + jp.getTarget()
//        );
//    }
//
//}

//Singleton solution - collecting context through this()
//@Aspect
//@Component
//public class AssociationsAspect {
//    public AssociationsAspect(){
//        System.out.println("Creating aspect instance!");
//    }
//
//    @Pointcut("execution(* com.sj.utilities.Example.*(..)) && this(bean)")
//    public void exampleOperations(Object bean){};
//
//    @Before("exampleOperations(bean)")
//    public void beforeExampleMethodsExecution(JoinPoint jp, Object bean){
//        System.out.println(
//                "JoinPoint: " + jp.getStaticPart() +
//                        "\n\taspect: " + this +
//                        "\n\tproxy object: " + bean +
//                        "\n\ttarget object: " + jp.getTarget()
//        );
//    }
//
//}
