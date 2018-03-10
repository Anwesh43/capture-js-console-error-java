package com.demos.anwesh;
import java.util.concurrent.*;
import java.io.*;
import java.util.*;
public class ChildProcessRunner {
    public static List<String> getProcessOutput(final String command, final File...directories) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Runtime runtime = Runtime.getRuntime();
        Future<List<String>> future = executorService.submit(new Callable<List<String>>() {
            public List<String> call() throws Exception {
                Process process = null;
                if (directories.length == 0) {
                    process = runtime.exec(command);
                }
                else {
                    process = runtime.exec(command, null, directories[0]);
                }
                process.waitFor();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = bufferedReader.readLine();
                List<String> lines = new ArrayList();
                while (line != null) {
                    lines.add(line);
                    line = bufferedReader.readLine();
                }
                return lines;
            }
        });
        List<String> results = null;
        try {
            results =  future.get();
        }
        catch(final Exception ex) {
          results = new ArrayList() {{
              add(ex.toString());
          }};
        }
        executorService.shutdown();
        return results;
    }
}
