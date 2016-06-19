package niya.mohsan.youtube.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohsan on 19/06/16.
 */
public class YoutubeProvider {

    static YoutubeProvider INSTANCE;
    final YoutubeService gitHubService;

    public YoutubeProvider() {

        this.gitHubService = new Retrofit.Builder()
                .baseUrl(YoutubeService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YoutubeService.class);
    }

    public static YoutubeService get() {
        if(INSTANCE == null){
            INSTANCE = new YoutubeProvider();
        }

        return INSTANCE.gitHubService;
    }

}

