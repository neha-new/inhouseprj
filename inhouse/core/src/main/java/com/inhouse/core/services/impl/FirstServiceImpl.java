package com.inhouse.core.services.impl;

import com.inhouse.core.services.FirstService;
import org.osgi.service.component.annotations.Component;

@Component (immediate = true, service = FirstService.class)
public class FirstServiceImpl implements FirstService{
    @Override
    public String getText(String address,String city){
        return address+" "+city;
    }
}

