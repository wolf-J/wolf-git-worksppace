package wolf_j.com.github.common;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import wolf_j.com.github.common.annotation.ValidationBean;

@Component  
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {  
  
    @Override  
    public void onApplicationEvent(ContextRefreshedEvent event) {  
        // 根容器为Spring容器  
        if(event.getApplicationContext().getParent()==null){  
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(ValidationBean.class);  
            for(Object bean:beans.values()){  
                System.out.println(bean==null?"null":bean.getClass().getName());  
            }  
            System.out.println("=====ContextRefreshedEvent====="+event.getSource().getClass().getName());  
        }  
    }  
}