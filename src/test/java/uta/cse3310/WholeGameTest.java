package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import uta.cse3310.WordSearchGame.GameState;

import java.util.ArrayList;

//import java.lang.annotation.Native;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WholeGameTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @return
     */
    public WholeGameTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(WholeGameTest.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    // This test is very close to a system level test.
    // Well, the system without the UI.
    // Inputs and Outputs are provided by JSON strings.
    //
    //
    // Should be able to test all of the logic in the program
    // with these tests.
    //
    // The challenge is doing it without repeating a lot of code, or making
    // it tightly coupled to the specific implementation.
    // To minimize coupling, the majority of the tests should deal with
    // json strings.
    ////////////////////////////////////////////////////////////////////////////
    // Routines to replace those in App.java
    ///////////////////////////////////////////////////////////////////////////

    private String update(WordSearchGame G, String msg) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        UserEvent U = gson.fromJson(msg, UserEvent.class);
        G.Update(U);
        String jsonString = gson.toJson(G);
        return jsonString;
    }

    ////////////////////////////////////////////////////////////////////////////
    public void testwinner() {
        WordSearchGame game = new WordSearchGame();
        String msg = new String();
        String result = new String();

        game.startGame();
        msg = "{\"GameId\":1,\"button\":\"join\",\"nickname\":\"ed\"}";
        result = update(game, "{\"GameId\":1,\"button\":\"startGame\",\"nickname\":\"ed\"}");

        msg = "{\"GameId\":1,\"button\":\"join\",\"nickname\":\"dd\"}";
        result = update(game, "{\"GameId\":1,\"button\":\"join\",\"nickname\":\"dd\"}");

        msg = "{\"GameId\":1,\"button\":\"readyUp\",\"nickname\":\"ed\"}";
        result = update(game, "{\"GameId\":1,\"button\":\"readyUp\",\"nickname\":\"ed\"}");

        msg = "{\"GameId\":1,\"button\":\"readyUp\",\"nickname\":\"dd\"}";
        result = update(game, "{\"GameId\":1,\"button\":\"readyUp\",\"nickname\":\"dd\"}");

        msg = "{\"GameId\":1,\"button\":\"startGame\",\"nickname\":\"ed\"}";
        result = update(game, "{\"GameId\":1,\"button\":\"startGame\",\"nickname\":\"ed\"}");

        result = update(game, "{\"GameId\":1,\"button\":\"startGame\",\"nickname\":\"dd\"}");
        msg = "{\"GameId\":1,\"button\":\"selectedCells\",\"nickname\":\"ed\",\"selectedCells\":[[1,23],[1,26]],\"Color\":\"#ff0000\"}";
        result = update(game,"{\"GameId\":1,\"button\":\"selectedCells\",\"nickname\":\"ed\",\"selectedCells\":[[1,23],[1,26]],\"Color\":\"#ff0000\"}");
        
        result = update(game, "{\"GameId\":1,\"button\":\"GameOver\"}");

        assertTrue(game.gameState == GameState.ENDED);


    }
}