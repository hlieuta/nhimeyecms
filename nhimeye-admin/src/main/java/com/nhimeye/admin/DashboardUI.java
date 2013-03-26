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
import com.google.common.eventbus.Subscribe;
import com.nhimeye.admin.event.LoginEvent;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

@Configurable(preConstruction = true)
@Theme("dashboard")
@Title("NHIMEYE CMS")
public class DashboardUI extends UI {

    private final EventBus eventBus = new EventBus();
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardUI.class);

    CssLayout root = new CssLayout();

    LoginView loginView;

    CssLayout menu = new CssLayout();
    CssLayout content = new CssLayout();

    HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {
        {

            put("/dashboard", DashboardView.class);
            put("/spaces",SpaceView.class);
            put("/articles", DashboardView.class);
            put("/documents", ReportsView.class);
            put("/recordtypes", RecordTypeView.class);
            put("/fields", FieldView.class);
            put("/users", UserView.class);

        }
    };

    HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

    private Navigator nav;

    private HelpManager helpManager;

    @Override
    protected void init(final VaadinRequest request) {
        eventBus.register(this);

        helpManager = new HelpManager(this);

        setLocale(Locale.US);

        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();

        // Unfortunate to use an actual widget here, but since CSS generated
        // elements can't be transitioned yet, we must
        final Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        root.addComponent(bg);
        if (isAuthenticated()) {
            buildMainView();
        } else {
            loginView = new LoginView(eventBus, false, root, helpManager, this);
        }

    }


    private void buildMainView() {

        nav = new Navigator(this, content);

        for (final String route : routes.keySet()) {
            nav.addView(route, routes.get(route));
        }

        helpManager.closeAll();
        removeStyleName("login");
        root.removeComponent(loginView);

        root.addComponent(new HorizontalLayout() {
            {
                setSizeFull();
                addStyleName("main-view");
                addComponent(new VerticalLayout() {
                    // Sidebar
                    {
                        addStyleName("sidebar");
                        setWidth(null);
                        setHeight("100%");

                        // Branding element
                        addComponent(new CssLayout() {
                            {
                                addStyleName("branding");
                                final Label logo = new Label(
                                        "<span>NHIMEYE CMS</span> Dashboard",
                                        ContentMode.HTML);
                                logo.setSizeUndefined();
                                addComponent(logo);
                            }
                        });
                        menu.setImmediate(true);
                        // Main menu
                        addComponent(menu);
                        setExpandRatio(menu, 1);

                        // User menu
                        addComponent(new VerticalLayout() {
                            {
                                setSizeUndefined();
                                addStyleName("user");
                                final Image profilePic = new Image(
                                        null,
                                        new ThemeResource("img/profile-pic.png"));
                                profilePic.setWidth("34px");
                                addComponent(profilePic);
                                final Label userName = new Label(" " + SecurityContextHolder.getContext().getAuthentication().getName());
                                userName.setSizeUndefined();
                                addComponent(userName);

                                final Command cmd = new Command() {
                                    @Override
                                    public void menuSelected(
                                            final MenuItem selectedItem) {
                                        Notification
                                                .show("Not implemented in this demo");
                                    }
                                };

                                final Command settingCmd = new Command() {
                                    @Override
                                    public void menuSelected(
                                            final MenuItem selectedItem) {
                                        buildMenu(true);

                                    }
                                };
                                final MenuBar settings = new MenuBar();
                                final MenuItem settingsMenu = settings.addItem("",
                                        null);
                                settingsMenu.setStyleName("icon-cog");
                                settingsMenu.addItem("System Settings", settingCmd);
                                settingsMenu.addSeparator();
                                settingsMenu.addItem("My Account", cmd);
                                addComponent(settings);

                                final Button exit = new NativeButton("Exit");
                                exit.addStyleName("icon-cancel");
                                exit.setDescription("Sign Out");
                                addComponent(exit);
                                exit.addClickListener(new ClickListener() {
                                    @Override
                                    public void buttonClick(final ClickEvent event) {
                                        AuthenticationService.handleLogout(RequestHolder.getRequest());
                                        loginView = new LoginView(eventBus, true, root, helpManager, getUI());
                                    }
                                });
                            }
                        });
                    }
                });
                // Content
                addComponent(content);
                content.setSizeFull();
                content.addStyleName("view-content");
                setExpandRatio(content, 1);
            }

        });

        buildMenu(false);

        nav.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(final ViewChangeEvent event) {
                helpManager.closeAll();
                return true;
            }

            @Override
            public void afterViewChange(final ViewChangeEvent event) {
                final View newView = event.getNewView();
            }
        });

    }

    public void buildMenu(boolean isSystem) {
        menu.removeAllComponents();

        String[] menuItems = null;
        if (isSystem) {
            menuItems = getSystemMenu();
        } else {
            menuItems = getMenu();
        }
        for (final String view : menuItems) {
            final Button b = new NativeButton(view.substring(0, 1).toUpperCase()
                    + view.substring(1).replace('-', ' '));
            b.addStyleName("icon-" + view);
            b.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    clearMenuSelection();
                    event.getButton().addStyleName("selected");
                    if (!nav.getState().equals("/" + view))
                        nav.navigateTo("/" + view);
                }
            });


            menu.addComponent(b);


            viewNameToMenuButton.put("/" + view, b);
        }
        menu.addStyleName("menu");
        menu.setHeight("100%");

        if (!isSystem) {
            viewNameToMenuButton.get("/dashboard").setHtmlContentAllowed(true);
            viewNameToMenuButton.get("/dashboard").setCaption(
                    "Dashboard<span class=\"badge\">2</span>");

            nav.navigateTo("/dashboard");
            menu.getComponent(0).addStyleName("selected");
        } else
        {
            nav.navigateTo("/spaces");
            menu.getComponent(0).addStyleName("selected");
           helpManager.addOverlay(
                            "NHIMEYE Spaces",
                            "<p>Please double click on the space to work on</p>",
                            "login");
        }
    }

    private void clearMenuSelection() {
        for (Iterator<Component> it = menu.getComponentIterator(); it.hasNext(); ) {
            final Component next = it.next();
            if (next instanceof NativeButton) {
                next.removeStyleName("selected");
            }
        }
    }

    HelpManager getHelpManager() {
        return helpManager;
    }

    void clearDashboardButtonBadge() {
        viewNameToMenuButton.get("/dashboard").setCaption("Dashboard");
    }

    private Boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication == null ? false : authentication
                .isAuthenticated();
    }

    @Subscribe
    public void login(final LoginEvent event) {

        try {
            AuthenticationService.handleAuthentication(event.getLogin(),
                    event.getPassword(), RequestHolder.getRequest());

            buildMainView();


        } catch (BadCredentialsException e) {
            loginView.addErrorMessage("Wrong username or password.");

        } catch (Exception ex) {
            loginView.addErrorMessage("Wrong username or password.");

        }

    }

    private String[] getMenu() {
        return new String[]{"dashboard", "articles",
                "documents"};
    }

    private String[] getSystemMenu() {
        return new String[]{"spaces","recordtypes", "fields", "users"};
    }


}
