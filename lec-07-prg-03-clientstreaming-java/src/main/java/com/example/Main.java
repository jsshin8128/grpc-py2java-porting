package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.out.println("server 또는 client 입력하세요");
            return;
        }
        
        String mode = args[0].toLowerCase();
        
        if (mode.equals("server")) {
            ClientStreamingServer.main(new String[0]);
        } else if (mode.equals("client")) {
            ClientStreamingClient.main(new String[0]);
        } else {
            System.out.println("잘못 입력했어요");
        }
    }
}

