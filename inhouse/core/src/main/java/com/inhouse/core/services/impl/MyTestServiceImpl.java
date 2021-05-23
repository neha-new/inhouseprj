package com.inhouse.core.services.impl;

import com.inhouse.core.services.MyTestService;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = MyTestService.class)
public class MyTestServiceImpl implements MyTestService {
    @Override
    public String getText(String fname, String lname) {
        return fname+" "+lname;
    }
}
