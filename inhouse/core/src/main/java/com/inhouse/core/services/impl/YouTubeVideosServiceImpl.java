package com.inhouse.core.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inhouse.core.dto.PlayListDto;
import com.inhouse.core.services.YouTubeVideosService;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component (immediate = true, service = YouTubeVideosService.class)
public class YouTubeVideosServiceImpl implements YouTubeVideosService {

    List<PlayListDto> videoList;

    @Override
    public List<PlayListDto> getYoutubeVideos(String playListId) {
        try {
            JsonObject jsonObject = readJsonFromUrl("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=20&playlistId="+playListId+"&key=AIzaSyC7ifsQgwNrD2BmJej8QjZ2srMoEVhafE0");
            JsonArray itemsArray = jsonObject.getAsJsonArray("items");
            videoList = new ArrayList<>();
            itemsArray.iterator().forEachRemaining(videoElement -> {
               JsonObject itemObj = videoElement.getAsJsonObject();
               JsonObject snippet = itemObj.getAsJsonObject("snippet");
               JsonObject resourceId = snippet.getAsJsonObject("resourceId");
               String videoId = resourceId.get("videoId").getAsString();

                PlayListDto video = new PlayListDto();
                video.setVideoId(videoId);
                videoList.add(video);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return videoList;
    }

    @Override
    public String jsonTemp() {
        try {
            String val = readJsonFromUrl("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=20&playlistId=PLsfMJQXMQi_EFO9dGRyC3GbEPOEErFoGO&key=AIzaSyC7ifsQgwNrD2BmJej8QjZ2srMoEVhafE0").toString();
            return val;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private static JsonObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JsonObject json = new JsonParser().parse(jsonText).getAsJsonObject();
            return json;
        }
        finally {
        is.close();
       }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

