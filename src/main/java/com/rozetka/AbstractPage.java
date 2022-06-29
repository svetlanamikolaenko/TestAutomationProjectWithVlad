package com.rozetka;

import com.rozetka.framework.config.TestConfig;

public abstract class AbstractPage {
     protected final String BASE_URL = TestConfig.CONFIG.baseUrl();
     public abstract void openPage();
}
