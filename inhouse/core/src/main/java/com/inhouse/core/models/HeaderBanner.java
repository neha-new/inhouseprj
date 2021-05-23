package com.inhouse.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Named;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderBanner {

    @ValueMapValue
    private String strapeLineText;

    @ValueMapValue
    private String bannerHeading;

    @ValueMapValue
    private String buttonLabel;

    @ValueMapValue
    private String bannerLink;

    @ValueMapValue
    private String bannerCheckbox;

    @ValueMapValue(name = "fileReference")
    private String backgroundImage;

    @ValueMapValue
    private String bannerRichtextbox;


    public String getStrapeLineText() {
        return strapeLineText;
    }

    public String getBannerHeading() {
        return bannerHeading;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public String getBannerCheckbox() {
        return bannerCheckbox;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }
    public String getBannerRichtextbox() {
        return bannerRichtextbox;
    }

}
