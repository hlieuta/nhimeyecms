/*
 * Copyright 2013 NHIMEYE Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.nhimeye.admin;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@Theme(Reindeer.THEME_NAME)
@Title("NHIMEYE CMS")
public class MobileCheckUI extends UI {

    @Override
    protected void init(final VaadinRequest request) {
        setWidth("400px");
        setContent(new VerticalLayout() {
            {
                setMargin(true);
                addComponent(new Label(
                        "<h1>NHIMEYE CMS</h1><h3>This application is not designed for mobile devices.</h3><p>If you wish, you can continue to <a href=\""
                                + request.getContextPath()
                                + request.getPathInfo()
                                + "?mobile=false\">load it anyway</a>.</p>",
                        ContentMode.HTML));
            }
        });

    }
}
