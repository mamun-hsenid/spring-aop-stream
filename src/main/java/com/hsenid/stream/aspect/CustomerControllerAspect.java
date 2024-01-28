package com.hsenid.stream.aspect;

import com.hsenid.stream.domain.Customer;
import com.hsenid.stream.domain.CustomerType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class CustomerControllerAspect {
    
    Logger logger = LoggerFactory.getLogger(CustomerControllerAspect.class);

    @Before("execution(* com.hsenid.stream.controller.CustomerController.addCustomer(..))")
    public void beforeAddCustomer(JoinPoint jointPoint) {
        logger.info("Before adding customer checking the perameter");
    }

    @After("execution(* com.hsenid.stream.controller.CustomerController.addCustomer(..))")
    public void afterAddCustomer(JoinPoint jointPoint) {
        logger.info(Arrays.stream(jointPoint.getArgs()).collect(Collectors.toList()).toString());

    }

    @Before("execution(* com.hsenid.stream.controller.CustomerController.getAllCustomer())")
    public void beforeGetAllCustomer(JoinPoint jp) {
        logger.info("Initiating getting all customer data --- method name :  "+jp.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.hsenid.stream.controller.CustomerController.getAllCustomer())", returning = "result")
    public void afterbeforeGetAllCustomer(JoinPoint jp, List<Customer> result) {

        if (result!=null && !result.isEmpty()) {
            result.stream()
                  .forEach(System.out::println);
        } else {
            logger.info("No result found");
        }
    }


    @AfterReturning("execution(* com.hsenid.stream.controller.CustomerController.addCustomer())")
    public void afterReturningAddCustomer(JoinPoint jointPoint){
        logger.info("after returning =====  ");
        logger.info(Arrays.stream(jointPoint.getArgs()).collect(Collectors.toList()).toString());

    }

    @AfterReturning(value = "execution(* com.hsenid.stream.controller.CustomerController.getAllFemaleCustomer())", returning = "result")
    public void afterReturningGetAllFemaleCustomer(JoinPoint jointPoint, List<Customer> result){
        logger.info("after returning advice started ===== ");
        if (result!=null && !result.isEmpty()) {
            result.stream()
                  .forEach(System.out::println);
        } else {
            logger.info("No result found");
        }
        logger.info("after returning advice finished ===== ");
    }

    @Around(value = "execution(* com.hsenid.stream.controller.CustomerController.getAllFemaleCustomer())")
    public List<Customer> aroundGetAllCustomer(ProceedingJoinPoint joinPoint){
        try {
            logger.info("--- proceeding the execution ---");
            List<Customer> customers = (List<Customer>) joinPoint.proceed();
            if (customers!=null && !customers.isEmpty()) {
                customers = customers.stream()
                    .filter(customer -> {
                        boolean isFemale = customer.getGender().equals(CustomerType.FEMALE.name());
                        if(isFemale){
                            System.out.println(customer);
                        }
                        return isFemale;
                    })
                    .collect(Collectors.toList());
            } else {
                logger.info("No result found");
            }
            logger.info("--- execution of method finished. Here's the result set ---");
            return customers;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
