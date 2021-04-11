package com.alexandrsmirn;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

public class Main {

    /**Asynchronously determines if the text string matches the regex pattern.*/
    public static CompletableFuture<Boolean> matchesAsync(String text, String regex) {
        return CompletableFuture.supplyAsync(() ->
                Pattern.compile(regex).matcher(text).matches()).exceptionally((t) -> false);
    }

    public static void main(String[] args) throws Exception {
        //Example. Checks if the string is a valid IP address
        var pattern
                = "\\A(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\z";
        var ip1 = "192.168.1.1";
        var ip2 = "192.qwe.1.1";

        CompletableFuture<Boolean> ip1Check = matchesAsync(ip1, pattern);
        CompletableFuture<Boolean> ip2Check = matchesAsync(ip2, pattern);

        //Do stuff...

        //...and get the result asynchronously
        boolean isIp1Valid = ip1Check.get();    //true
        boolean isIp2Valid = ip2Check.get();    //false

    }
}
