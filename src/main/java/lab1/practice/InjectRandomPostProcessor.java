package lab1.practice;

import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class clazz = bean.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            InjectRandom annotation = field.getAnnotation(InjectRandom.class);
            if (annotation != null) {
                Random random = new Random();
                int x = random.nextInt(annotation.value()) + 1;
                field.setAccessible(true);
                try {
                    field.set(bean, x);
                } catch (Exception runtimeException) {
                    throw new RuntimeException(runtimeException.getMessage(), runtimeException);
                }
                field.setAccessible(false);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}