package io.cucumber.petstore.configuration;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public Object getContext(Context key) {
        return scenarioContext.get(key.toString());
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }
}
