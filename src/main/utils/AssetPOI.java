package main.utils;

import org.json.simple.JSONObject;

public class AssetPOI {
    private String name, path, type;

    public AssetPOI(JSONObject object) {
        setName(object.get("name").toString());
        setPath(object.get("path").toString());
        setType(object.get("type").toString());
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }
}
