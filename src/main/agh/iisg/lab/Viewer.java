package agh.iisg.lab;

import java.util.HashMap;
import java.util.Optional;

public class Viewer {
    HashMap<ArgType, String> executionOptions = new HashMap<>();

    public Viewer(HashMap<ArgType, Optional<String>> parsedArgs) {
                parsedArgs.entrySet().stream()
                    .filter(entry -> entry.getValue().isPresent())
                        //all null optionals are filtered, so get is safe
                    .forEach(entry -> executionOptions.put(entry.getKey(), entry.getValue().get()));
    }
}
