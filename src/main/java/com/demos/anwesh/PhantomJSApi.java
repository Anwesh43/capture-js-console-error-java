package com.demos.anwesh;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class PhantomJSApi {
    private String phantomjsPath;
    private String url;
    private String directory;
    private final Gson gson = new Gson();
    private List<String> outputLines;
    private final String JAVASCRIPT_ERROR_INDICATOR = "CONSOLE_ERRORS:";
    private final String RESOURCE_ERROR_INDICATOR = "RESOURCE_ERROR:";
    private final Type networkErrorType = new TypeToken<List<JavascriptNetworkError>>() {}.getType();
    private final Type javascriptErrorsType = new TypeToken<List<JavaScriptConsoleError>>(){}.getType();
    public PhantomJSApi(String phantomjsPath, String url, String directory) {
        this.phantomjsPath = phantomjsPath;
        this.url = url;
        this.directory = directory;
    }
    public void execute() {
        outputLines = CaptureConsoleUtils.getPhantomScriptJSON(phantomjsPath, url, directory);
    }
    public List<JavaScriptConsoleError> getJavascritErrosList() {
        if(outputLines == null) {
            return null;
        }
        for(String result : outputLines) {
            if (result.startsWith(JAVASCRIPT_ERROR_INDICATOR)) {
                return gson.fromJson(result.replace(JAVASCRIPT_ERROR_INDICATOR, ""), javascriptErrorsType);
            }
        }
        return null;
    }
    public List<JavascriptNetworkError> getNetworkErrorsList() {
        if(outputLines == null) {
            return null;
        }
        StringBuilder jsonBuilder = new StringBuilder("[");
        int i = 0;
        for(String result : outputLines) {
            if (result.startsWith(RESOURCE_ERROR_INDICATOR)) {
                if (i != 0) {
                    jsonBuilder.append(",");
                }
                jsonBuilder.append(result.replace(RESOURCE_ERROR_INDICATOR, ""));
                i++;
            }
        }
        jsonBuilder.append("]");
        return gson.fromJson(jsonBuilder.toString(), networkErrorType);
    }
}
