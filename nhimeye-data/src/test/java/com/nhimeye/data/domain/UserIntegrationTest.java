package com.nhimeye.data.domain;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = User.class, transactional = false)
public class UserIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
