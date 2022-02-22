package xyz.itbs.jokes.services;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.itbs.jokes.domain.Category;
import xyz.itbs.jokes.domain.Joke;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class JokeApiServiceImpl implements JokeApiService {
    @Cacheable("cats")
    @Override
    public Set<String> getCategories() throws Exception {
        Set<String> categorySet = new HashSet<>();
        URL url = new URL("https://api.jokes.one/jod/categories");
        JSONObject jsonObject = new JSONObject(getFromApi(url));
        jsonObject = jsonObject.getJSONObject("contents");
        JSONArray jsonArray = jsonObject.getJSONArray("categories");
        for(int i = 0 ; i < jsonArray.length() ; i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            categorySet.add(json.get("name").toString().toUpperCase());
        }
        log.info("Successfully fetched joke categories from API");
        return categorySet;
    }

    @Override
    public Joke getJoke(String cat) throws Exception {
        String urlString = "https://api.jokes.one/jod?category=" + cat.toLowerCase();
        URL url = new URL(urlString);
        String text = "";
        String title = "";
        JSONObject jsonObject = new JSONObject(getFromApi(url));
        jsonObject = jsonObject.getJSONObject("contents");
        JSONArray jsonArray = jsonObject.getJSONArray("jokes");
        for(int i = 0 ; i < jsonArray.length() ; i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            jsonObj = jsonObj.getJSONObject("joke");
            text = jsonObj.get("text").toString();
            title = jsonObj.get("title").toString();
        }
        log.info("Successfully fetched Joke from API");
        if(cat.equals("KNOCK-KNOCK")) cat = "KNOCK_KNOCK";
        return Joke.builder()
                .title(title)
                .text(text)
                .category(Category.valueOf(cat.toUpperCase()))
                .build();
    }

    private String getFromApi(URL url) {
        StringBuilder jsonString = new StringBuilder();
        try{
            //make connection
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestMethod("GET");
            // set the content type
            urlc.setRequestProperty("Content-Type", "application/json");
            urlc.setRequestProperty("X-JokesOne-Api-Secret", "YOUR API KEY HERE");
            log.info("Connect to: " + url.toString());
            urlc.setAllowUserInteraction(false);
            urlc.connect();

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            while ((l=br.readLine())!=null) {
                jsonString.append(l);

            }
            br.close();
        } catch (Exception e){
            log.error("Error occurred while fetching JSON from API");
            log.error(e.toString());
        }
        return jsonString.toString();
    }


}
