package com.ricemonitoring;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ricemonitoring.util.MD5Utils;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

public class Base {
    @Test
    public void test1() {
        String str = "松江九亭";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (!sb.toString().equals("")) {
                sb.append(",");
            }
            sb.append(str.charAt(i));
        }
        String keyword = sb.toString();

        keyword = keyword.replaceAll(",", "%");

        System.out.println(keyword);
    }

    @Test
    public void test2() {
        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6006&format=json&query=112.32.103.49";
        HttpResponse res = HttpRequest.get(url).execute();
        if (200 != res.getStatus()) {
            System.out.println(123);
        } else {
            JSONObject resJson = JSONObject.parseObject(res.body());
            //JSONArray resArr = JSONArray.parseArray(resJson.getString("data"));
            System.out.println(resJson);
            //resJson = JSONObject.parseObject(resArr.get(0).toString());
        }

    }

    @Test
    public void test3() {
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuilder flag = new StringBuilder();
        for (int j = 0; j < 6; j++)
        {
            flag.append(sources.charAt(rand.nextInt(9)));
        }
        String flags = flag.toString();
        String code = MD5Utils.inputPassToFormPass(flags);
        System.out.println(flags);
        System.out.println(code);

    }
}

