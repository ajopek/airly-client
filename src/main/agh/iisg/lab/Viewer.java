package agh.iisg.lab;

import agh.iisg.lab.airly_api.RequestType;

import java.util.HashMap;
import java.util.Optional;

public class Viewer {
    private HashMap<ArgType, String> executionOptions = new HashMap<>();

    public Viewer(HashMap<ArgType, Optional<String>> parsedArgs) {
                parsedArgs.entrySet().stream()
                    .filter(entry -> entry.getValue().isPresent())
                        //all null optionals are filtered, so get is safe
                    .forEach(entry -> executionOptions.put(entry.getKey(), entry.getValue().get()));
    }

    private void putApiKey() {
        String apiKey = System.getenv("API_KEY");
        if (apiKey.isEmpty()) {
            if (!executionOptions.containsKey(ArgType.ApiKey)) {
                throw new IllegalArgumentException("No api key has been specified.");
            }
        } else {
            executionOptions.put(ArgType.ApiKey, apiKey);
        }
    }

    private RequestType getRequestType() {
        RequestType type = null;
        if (executionOptions.containsKey(ArgType.Latitiude) && executionOptions.containsKey(ArgType.Longtitiude)) {
            type = RequestType.MapPoint;
        }
        if (executionOptions.containsKey(ArgType.SensorId)) {
            type = RequestType.Sensor;
        }
        if (type == null) {
            throw new IllegalArgumentException("Please specify latitiude and longtitude or sensor id");
        }
        return type;
    }

    public void execute() {

    }
}
