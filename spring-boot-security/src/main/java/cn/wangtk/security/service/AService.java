package cn.wangtk.security.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class AService implements InitializingBean {

    public AService() {
        System.out.println("init AService");
    }

    @Autowired
    BService bService;
    @Autowired
    CService cService;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("a afterPropertiesSet");
    }

    public BService getbService() {
        return bService;
    }

    public void setbService(BService bService) {
        this.bService = bService;
    }

    public CService getcService() {
        return cService;
    }

    public void setcService(CService cService) {
        this.cService = cService;
    }
}
