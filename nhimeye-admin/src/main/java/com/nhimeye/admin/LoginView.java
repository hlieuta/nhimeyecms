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



import com.google.common.eventbus.EventBus;
import com.nhimeye.admin.event.LoginEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

public class LoginView extends VerticalLayout{

    private Label error;
    private CssLayout loginPanel;
    private TextField username;

    public LoginView (EventBus eventBus,boolean exit, CssLayout root,HelpManager helpManager, UI ui)
      {
          setSizeFull();
          addStyleName("login-layout");
          buildLoginView(eventBus,exit,root,helpManager,ui);
      }

    private void buildLoginView(final EventBus eventBus,boolean exit, CssLayout root,HelpManager helpManager, UI ui) {
        if (exit) {
            root.removeAllComponents();
        }
        helpManager.closeAll();
        HelpOverlay w = helpManager
                .addOverlay(
                        "Welcome to the NHIMEYE CMS ",
                        "<p>username and password are required, please click the ‘Sign In’ button to continue.</p>",
                        "login");
        w.center();
        ui.addWindow(w);

        root.addStyleName("login");
        root.addComponent(this);

        loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

        Label title = new Label("NHIMEYE CMS");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        username = new TextField("Username");
        username.focus();
        fields.addComponent(username);

        final PasswordField password = new PasswordField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
        fields.addComponent(signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("Sign In",
                ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                signin.click();
            }
        };

        signin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (username.getValue() != null
                        && !username.getValue().equals("")
                        && password.getValue() != null
                        && !password.getValue().equals("")) {
                    signin.removeShortcutListener(enter);
                    handleLogin(eventBus,username.getValue(),password.getValue());
                } else {
                   addErrorMessage("Wrong username or password.");
                }
            }
        });

        signin.addShortcutListener(enter);

        loginPanel.addComponent(fields);

        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    public void addErrorMessage(String message) {
        if (loginPanel.getComponentCount() > 2) {
            // Remove the previous error message
            loginPanel.removeComponent(loginPanel.getComponent(2));
        }
            // Add new error message
            error = new Label(message,
                    ContentMode.HTML);
            error.addStyleName("error");
            error.setSizeUndefined();
            error.addStyleName("light");
            // Add animation
            error.addStyleName("v-animate-reveal");

            loginPanel.addComponent(error);
            username.focus();

    }

    private void handleLogin(final EventBus eventBus, String username, String password)
    {
        LoginEvent loginEvent = new LoginEvent(username, password);
        eventBus.post(loginEvent);
    }
}
