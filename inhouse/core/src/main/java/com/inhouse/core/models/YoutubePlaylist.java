package com.inhouse.core.models;
import com.inhouse.core.dto.PlayListDto;
import com.inhouse.core.services.YouTubeVideosService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class YoutubePlaylist {

    private List<PlayListDto> playlistDtosList;

    @ValueMapValue
    private String playListId;


    private String jsonString;

    @OSGiService
    protected YouTubeVideosService youTubeVideosService;

    @PostConstruct
    protected void init() {
        playlistDtosList = youTubeVideosService.getYoutubeVideos(playListId);
        jsonString = youTubeVideosService.jsonTemp();
    }

    public List<PlayListDto> getPlaylistDtosList() {
        return playlistDtosList;
    }

    public String getPlayListId() {
        return playListId;
    }

    public String getJsonString() {
        return jsonString;
    }

}
