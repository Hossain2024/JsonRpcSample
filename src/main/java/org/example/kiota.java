package org.example;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.Dispatcher;

import java.util.*;

public class kiota {
    public static void main(String[] args) {
        // Create a new JSON-RPC 2.0 request dispatcher
        Dispatcher dispatcher = new Dispatcher();

        dispatcher.register(new kiotaVersionHandler());
        dispatcher.register(new kiotaGenerationHandler());


        JSONRPC2Request req = new JSONRPC2Request("getkiotaversion", "req-id-01");
        System.out.println("Request: \n" + req);

        JSONRPC2Response resp = dispatcher.process(req, null);
        System.out.println("Response: \n" + resp);


         // pass a map
        HashMap map = (HashMap) askInput();
         req = new JSONRPC2Request("generate", map, "req-id-02");
        System.out.println("Request: \n" + req);

         resp = dispatcher.process(req, null);
         System.out.println("Response: \n" + resp);

    }

    /**
     * collect userinput to generate the code
     * @return
     */
    private static Map<String, String>  askInput() {
        Map<String, String> argument = new LinkedHashMap<>();
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the path to an openapi description :  ");
        String yaml_file_dir = console.nextLine();
        System.out.println("Enter the language :  ");
        String language = console.nextLine();
        System.out.println("Enter the name of the generated client :  ");
        String client_Name = console.nextLine();
        System.out.println("Enter the namespace/package :  ");
        String namespace = console.nextLine();
        System.out.println("Enter the path to an output directory :  ");
        String output_dir = console.nextLine();
        // add the userinput to a map
        argument.put("-l", language);
        argument.put("-c", client_Name );
        argument.put("-n", namespace);
        argument.put("-d", yaml_file_dir);
        argument.put("-o", output_dir);
        return argument;
    }

}
