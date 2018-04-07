package main.config.enums;

import cmsLibraryManager.config.interfaces.EnvironmentSetup;

public enum Environment implements EnvironmentSetup {
    DEV {
        public String getEnvironmentUrl() {
            String baseUrl = "https://media-browser.dev-allsaints.com/";
            return baseUrl;
        }
    },
    PROD {
        public String getEnvironmentUrl() {
            String baseUrl = "https://media-browser.allsaints.com/";
            return baseUrl;
        }
    };
}
