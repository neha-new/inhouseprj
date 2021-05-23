package com.inhouse.core.models;

import com.inhouse.core.dto.HeaderNavDto;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderNav {

    @SlingObject
    private Resource resource;

    private ArrayList<HeaderNavDto> headerNavDtos;

    @PostConstruct
    protected void init() {
        System.out.println("Hello World");
        headerNavDtos = new ArrayList<>();
        Resource navItemsResource = resource.getChild("navItems");
        navItemsResource.getChildren().forEach(child -> {
            HeaderNavDto dto = new HeaderNavDto();
            dto.setNavTitle((String) child.getValueMap().getOrDefault("label",""));
            dto.setLinkUrl((String) child.getValueMap().getOrDefault("link",""));
            headerNavDtos.add(dto);
        });
    }

    public ArrayList<HeaderNavDto> getHeaderNavDtos() {
        return headerNavDtos;
    }
}
