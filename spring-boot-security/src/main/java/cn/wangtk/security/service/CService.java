package cn.wangtk.security.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CService implements InitializingBean {

    public CService() {
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("c afterPropertiesSet");
    }
}
