package life.sbgirl.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.deploy.net.HttpResponse;
import life.sbgirl.community.community.mapper.Realtime;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : sbgirl
 * @Date: 2020/11/28 12:16
 **/
@Component
public class WeatherProvider {
    public  Realtime getTemperature() throws UnsupportedEncodingException {
        String city = java.net.URLEncoder.encode("上海", "utf-8");
        String key = "1fbccc3fc2db1e68d7709ba2debdddea";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/simpleWeather/query?city="+city+"&key="+key)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String string = response.body().string();
            JSONObject obj =JSON.parseObject(string);
            JSONObject result = obj.getJSONObject("result");
            String real=result.getString("realtime");
            Realtime r=JSON.parseObject(real,Realtime.class);
            return r;
        } catch (IOException e) {
            return null;
        }
    }
}
