package com.inhouse.core.services;

import com.inhouse.core.dto.PlayListDto;

import java.util.List;

public interface YouTubeVideosService {
    List<PlayListDto> getYoutubeVideos(String playListId);

    String jsonTemp();
}


