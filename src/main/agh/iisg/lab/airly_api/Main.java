package agh.iisg.lab.airly_api;

import agh.iisg.lab.ArgParser;
import agh.iisg.lab.ArgType;
import agh.iisg.lab.Viewer;

import java.util.HashMap;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ArgParser argParser = new ArgParser();
        HashMap<ArgType, Optional<String>> parsedArgs = argParser.readArgs(args);
        Viewer viewer = new Viewer(parsedArgs);
        viewer.execute();
    }
}
