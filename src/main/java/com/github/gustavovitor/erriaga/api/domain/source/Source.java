package com.github.gustavovitor.erriaga.api.domain.source;

import lombok.Data;

public class Source {
    private String backendUrl;
    private String frontendUrl;

    public String getBackendUrl() {
        return "https://github.com/gustavovitor/erriaga-api";
    }

    public String getFrontendUrl() {
        return "https://github.com/gustavovitor/erriaga-ui";
    }
}
