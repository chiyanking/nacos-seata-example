package cn.wangtk.security.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class BService implements InitializingBean  {
    public BService() {
        System.out.println("init BService !");
    }

    @Autowired
    AService aService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("b afterPropertiesSet");
    }

    public AService getaService() {
        return aService;
    }

    public void setaService(AService aService) {
        this.aService = aService;
    }
}
