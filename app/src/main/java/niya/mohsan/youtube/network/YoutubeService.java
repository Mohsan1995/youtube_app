package niya.mohsan.youtube.network;

import java.util.List;

import niya.mohsan.youtube.model.Youtube;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mohsan on 19/06/16.
 */
public interface YoutubeService {
    static String END_POINT = "https://raw.githubusercontent.com/florent37/MyYoutube/master/";

    @GET("myyoutube.json")
    Call<List<Youtube>> listYoutube();


}
