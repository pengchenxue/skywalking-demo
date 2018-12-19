package org.skywalking.springcloud.test.projectc.service;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
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
    private HttpClientCaller httpClientCaller;

    @RequestMapping("/projectC/{value}")
    public String home(@PathVariable("value") String value) throws InterruptedException, IOException {

        log.info("===============test============traceid={}",TraceContext.traceId());
        Thread.sleep(new Random().nextInt(2) * 1000);
        httpClientCaller.call("http://www.baidu.com");
        return value + "-" + UUID.randomUUID().toString();
    }
}
