package com.example.LibraryVol2.contextListener;

import com.example.LibraryVol2.contextListener.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyPostProxyContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names){
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            try{
                Class<?> origClass = Class.forName(originalClassName);
                Method[] methods = origClass.getMethods();
                for (Method method: methods){
                    if(method.isAnnotationPresent(PostProxy.class)){
                        Object bean = context.getBean(name);
                        Method currMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        currMethod.invoke(bean);
                    }
                }
            }catch (Exception e){
//                e.printStackTrace();
               }

        }
    }
}
