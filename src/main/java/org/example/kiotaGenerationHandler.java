package org.example;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class kiotaGenerationHandler implements RequestHandler {

    @Override
    public String[] handledRequests() {
        return new String[]{"generate"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        if (request.getMethod().equals("generate")) {
            //get the map param that was passes to request object
            StringBuilder builder = new StringBuilder();
            Map map = request.getNamedParams();
            builder.append("generate ");
            for (Object k : map.keySet()) {
                        builder.append(k).append(" ").
                        append(map.get(k)).append(" ");
            }
            try {
                String test = builder.toString();
               // Process process = new ProcessBuilder("kiota", builder.toString()).start();
               // Process process = new ProcessBuilder("kiota",  "generate -l java -c postclient -n postclient.java -d C:\\Users\\v-malhossain\\Documents\\Eoutput\\posts-api.yml -o C:\\Users\\v-malhossain\\Documents\\Eoutput").start();
                // postclient", "-n, postclient.java", "-d C:\\Users\\v-malhossain\\Documents\\Eoutput\\posts-api.yml", "-o C:\\Users\\v-malhossain\\Documents\\Eoutput").start();
                // Read the command output
                Process process = new ProcessBuilder("kiota", "generate", "-l", "java", "-c", "postclient", "-n", "postclient.java", "-d", "C:\\Users\\v-malhossain\\Documents\\Eoutput\\posts-api.yml", "-o", "C:\\Users\\v-malhossain\\Documents\\Eoutput").start();

                // Read the command output
                BufferedReader errorreader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                StringBuilder output = new StringBuilder();
                while ((line = errorreader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                //process.waitfor- for the process to finish
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    return new JSONRPC2Response(true, request.getID());
                } else {
                    return new JSONRPC2Response(false, request.getID());
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            return new JSONRPC2Response("method was not found", request.getID());
        }

        return null;
    }




}

