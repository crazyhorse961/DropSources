package com.crazyhoorse961.sourcedrops.utils;/**
 * Created by nini7 on 01.04.2017.
 */



import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author crazyhoorse961
 */
public class Dumper {

    private String getErrors(){
        List<String> data = new ArrayList<String>();
        for (Map.Entry<Thread, StackTraceElement[]> thr : Thread.getAllStackTraces().entrySet()) {
            StringJoiner joiner = new StringJoiner("\n", thr.getKey().getName() + "\n{\n", "\n}");
            boolean containsError = false;
            for (StackTraceElement elem : thr.getValue()) {
                if (elem.getClassName().contains("com.crazyhoorse961.sourcedrops")) {
                    joiner.add(" Error at line " + elem.getLineNumber() + " at method " + elem.getMethodName() + " at class " + elem.getClassName() + " (" + elem.getFileName() + ")");
                    containsError = true;
                }
            }
            if (containsError)
                data.add(joiner.toString());
        }
        return data.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    public String generateHaste(){
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL( "https://hastebin.com/documents");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(getErrors());
            wr.flush();
            wr.close();

            //Get Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return "https://hastebin.com/" + new JSONObject(rd.readLine()).getString("key");

        } catch (IOException e) {
            return null;
        } finally {
            if (connection == null) return null;
            connection.disconnect();
        }
    }

}
