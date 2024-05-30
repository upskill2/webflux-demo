package com.webflux.user_service.aspect;

import com.webflux.user_service.entity.UserTransaction;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Aspect
public class EntityStamper {

    @Before ("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void setTimestampsOnEntities(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // only intercept save calls - that's when timestamp setting should happen
        if (!signature.getName().contains("save")) {
            return;
        }

        // need to go through target.class as Spring Data repositories will be proxied
        Class<?> domainRepositoryType = AopProxyUtils.proxiedUserInterfaces(joinPoint.getTarget())[0];

        // every Spring JPA repo has two generic parameters - entity type and key type
        Class<?> entityType = (Class<?>) ((ParameterizedType) domainRepositoryType.getGenericInterfaces()[0]).getActualTypeArguments()[0];

        if (!UserTransaction.class.isAssignableFrom(entityType)) {
            return;
        }

        Object argBeingSaved = joinPoint.getArgs()[0];

        // save (single object or iteralbe of objects)
        if (argBeingSaved instanceof Iterable) {
            timestampAll((Iterable<UserTransaction>) argBeingSaved);
        } else {
            timestampOne((UserTransaction) argBeingSaved);
        }

    }

    private void timestampAll(Iterable<UserTransaction> entities) {
        entities.forEach(this::timestampOne);
    }

    private void timestampOne(UserTransaction withCreatedAt) {
        LocalDateTime now = LocalDateTime.now();

        // don't override creation date once it's been set
        if (withCreatedAt.getTransactionTime () == null) {
            withCreatedAt.setTransactionTime (now);
        }
    //    withCreatedAt.setUpdatedAt(now);
    }

}