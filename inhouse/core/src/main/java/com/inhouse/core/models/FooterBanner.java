package com.inhouse.core.models;

import com.day.cq.wcm.api.Page;
import com.inhouse.core.dto.HeaderNavDto;
import com.inhouse.core.dto.SocialShareItemsDto;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterBanner {

    @ValueMapValue
    private String footerLogoName;

    @ValueMapValue
    private String footerRichTextBox;

    @ValueMapValue
    private String footerHeadingText;

    @ValueMapValue
    private String contactDetails;

    @ValueMapValue
    private String companyAddress;

    @ValueMapValue
    private String newsPage;

    @SlingObject
    private Resource resource;

    @SlingObject
    private ResourceResolver resourceResolver;

    private ArrayList<SocialShareItemsDto> socialShareItemsDtos;

    private Map<String,String> newsPageMap;

    @PostConstruct
    protected void init() {

        socialShareItemsDtos = new ArrayList<>();
        Resource socialShareItemsresource = resource.getChild("socialShareItems");
        if(socialShareItemsresource != null) {
            socialShareItemsresource.getChildren().forEach(child -> {
                SocialShareItemsDto socialShareItemsDto = new SocialShareItemsDto();
                socialShareItemsDto.setSocialShareImage((String) child.getValueMap().getOrDefault("SocialShareImage", ""));
                socialShareItemsDto.setSocialShareLink((String) child.getValueMap().getOrDefault("SocialShareLink", ""));
                socialShareItemsDtos.add(socialShareItemsDto);

            });
        }
        Resource newsPageResource = resourceResolver.getResource(newsPage);
        Page newsPages = newsPageResource.adaptTo(Page.class);
        newsPageMap = new HashMap<>();
        Iterator<Page> iterator = newsPages.listChildren();
        while (iterator.hasNext()) {
            Page childPage = iterator.next();
            String pageTitle = childPage.getPageTitle();
            String pagePath = childPage.getPath();
            newsPageMap.put(pagePath,pageTitle);
            //output this props values just like you want to
        }
    }

    public String getFooterHeadingText() {
        return footerHeadingText;
    }
    public ArrayList<SocialShareItemsDto> getSocialShareItemsDtos() {
        return socialShareItemsDtos;
    }

    public String getFooterLogoName() {
        return footerLogoName;
    }

    public String getFooterRichTextBox() {
        return footerRichTextBox;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getNewsPage() {
        return newsPage;
    }

    public Map<String, String> getNewsPageMap() {
        return newsPageMap;
    }

}
