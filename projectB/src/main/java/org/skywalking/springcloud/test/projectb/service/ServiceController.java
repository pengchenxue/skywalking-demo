package org.skywalking.springcloud.test.projectb.service;

import java.util.Random;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;
import org.skywalking.springcloud.test.projectb.dao.DatabaseOperateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RestController
public class ServiceController {


    @Autowired
    private DatabaseOperateDao operateDao;

    @RequestMapping("/projectB/{value}")
    public String home(@PathVariable("value") String value) throws InterruptedException {
        log.info("===============test================");
        Thread.sleep(new Random().nextInt(2) * 1000);
        operateDao.saveUser(value);
        operateDao.selectUser(value);
        return value + "-" + UUID.randomUUID().toString();
    }
}
