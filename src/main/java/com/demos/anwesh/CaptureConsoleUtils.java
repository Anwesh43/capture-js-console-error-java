package com.demos.anwesh;
import java.net.*;
import java.util.List;
import java.io.File;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class CaptureConsoleUtils {
    private final static String LIST_OF_FILES_COMMAND = "ls";
    private final static String PHANTOM_SCRIPT = "get_console_output_with_details.js";
    private final static String PHANTOM_COMMAND = "phantomjs "+ PHANTOM_SCRIPT;
    private final static String PHANTOM_JS_PATH_COMMAND = "which phantomjs";
    private final static String JAVASCRIPT_ERROR_INDICATOR = "CONSOLE_ERRORS:";
    private final static String HOME_DIRECTORY_KEY = "user.home";
    private final static String PHANTOM_JS_INSTALLATION_PATH_WINDOWS = "AppData/Roaming/npm/phantomjs.cmd";
    public static List<String> getListOfFiles() {
        return ChildProcessRunner.getProcessOutput(LIST_OF_FILES_COMMAND);
    }
    private static String getPhantomJSPath() {
        List<String> result = ChildProcessRunner.getProcessOutput(PHANTOM_JS_PATH_COMMAND);
        if(result.size() == 1) {
            return result.get(0);
        }
        return null;
    }
    public static String getJavascriptErrrosJSON(String phantomjsPath, String url, String directory) {
        List<String> results =  ChildProcessRunner.getProcessOutput(PHANTOM_COMMAND.replace("phantomjs", phantomjsPath) + " "+ url, new File(directory));
        for(String result : results) {
            if (result.startsWith(JAVASCRIPT_ERROR_INDICATOR)) {
                return result.replace(JAVASCRIPT_ERROR_INDICATOR, "");
            }
        }
        return null;
    }

    public static String getJavascriptErrrosJSONForWindows(String url, String directory) {
        String homeDirectory = System.getProperty(HOME_DIRECTORY_KEY);
        return getJavascriptErrrosJSON(String.format("%s/%s", homeDirectory, PHANTOM_JS_INSTALLATION_PATH_WINDOWS), url, directory);
    }
    public static List<JavaScriptConsoleError> getJavascritErrosList(String phantomjsPath, String url, String directory) {
        String javascriptErrorsJson = getJavascriptErrrosJSON(phantomjsPath, url, directory);
        Gson gson = new Gson();
        Type javascriptErrorsType = new TypeToken<List<JavaScriptConsoleError>>(){}.getType();
        return gson.fromJson(javascriptErrorsJson, javascriptErrorsType);
    }
}
