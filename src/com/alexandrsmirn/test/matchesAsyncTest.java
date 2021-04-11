package com.alexandrsmirn.test;

import com.alexandrsmirn.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class matchesAsyncTest {

    @Test
    public void catastrophicBacktrackingTest() throws Exception {
        var string = "Some very long company name that does not match the regex @%!";
        var regex = "^([a-zA-Z0-9]+\\s?)*$";
        boolean result = Main.matchesAsync(string, regex).get();
        assertFalse(result);
    }

    @Test
    public void invalidRegexTest() throws Exception {
        var string = "Some string";
        var regex = "[";
        boolean result = Main.matchesAsync(string, regex).get();
        assertFalse(result);
    }

    @Test
    public void regexMatchTest() throws Exception {
        var string = "PassWord1";
        var regex = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$";
        boolean result = Main.matchesAsync(string, regex).get();
        assertTrue(result);
    }

    @Test
    public void validRegexDoesNotMatchTest() throws Exception {
        var string = "Password";
        var regex = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$";
        boolean result = Main.matchesAsync(string, regex).get();
        assertFalse(result);
    }
}