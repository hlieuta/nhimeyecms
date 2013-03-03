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


import java.text.DecimalFormat;
import com.vaadin.data.Property;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DashboardView extends VerticalLayout implements View {

    private Table lastAddedArticles;
    private Table lastEditedArticles;
    private Table popularArticles;

    public DashboardView() {
        setSizeFull();
        addStyleName("dashboard-view");

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);
        top.addStyleName("toolbar");
        addComponent(top);
        final Label title = new Label("My Dashboard");
        title.setSizeUndefined();
        title.addStyleName("h1");
        top.addComponent(title);
        top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        top.setExpandRatio(title, 1);

        Button notify = new Button("2");
        notify.setDescription("Notifications (2 unread)");
        // notify.addStyleName("borderless");
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.addStyleName("icon-only");
        notify.addStyleName("icon-bell");
        notify.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                ((DashboardUI) getUI()).clearDashboardButtonBadge();
                event.getButton().removeStyleName("unread");
                event.getButton().setDescription("Notifications");

                if (notifications != null && notifications.getUI() != null)
                    notifications.close();
                else {
                    buildNotifications(event);
                    getUI().addWindow(notifications);
                    notifications.focus();
                    ((CssLayout) getUI().getContent())
                            .addLayoutClickListener(new LayoutClickListener() {
                                @Override
                                public void layoutClick(LayoutClickEvent event) {
                                    notifications.close();
                                    ((CssLayout) getUI().getContent())
                                            .removeLayoutClickListener(this);
                                }
                            });
                }

            }
        });
        top.addComponent(notify);
        top.setComponentAlignment(notify, Alignment.MIDDLE_LEFT);

        Button edit = new Button();
        edit.addStyleName("icon-viewsite");
        edit.addStyleName("icon-only");
        top.addComponent(edit);
        edit.setDescription("View Site");
        edit.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

                //TODO: handle view site
            }
        });
        top.setComponentAlignment(edit, Alignment.MIDDLE_LEFT);

        HorizontalLayout row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);

        buildLastAddedArticles();
        row.addComponent(createPanel(lastAddedArticles));

        TextArea notes = new TextArea("Notes");
        notes.setValue("Remember to:\n· Zoom in and out in the Sales view\n· Filter the transactions and drag a set of them to the Reports tab\n· Create a new report\n· Change the schedule of the movie theater");
        notes.setSizeFull();
        CssLayout panel = createPanel(notes);
        panel.addStyleName("notes");
        row.addComponent(panel);

        row = new HorizontalLayout();
        row.setMargin(true);
        row.setSizeFull();
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 2);


        buildLastEditedArticles();
        row.addComponent(createPanel(lastEditedArticles));

        buildPopularArticles();
       row.addComponent(createPanel(popularArticles));

    }

    private CssLayout createPanel(Component content) {
        CssLayout panel = new CssLayout();
        panel.addStyleName("layout-panel");
        panel.setSizeFull();

        Button configure = new Button();
        configure.addStyleName("configure");
        configure.addStyleName("icon-cog");
        configure.addStyleName("icon-only");
        configure.addStyleName("borderless");
        configure.setDescription("Configure");
        configure.addStyleName("small");
        configure.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Not implemented in this demo");
            }
        });
        panel.addComponent(configure);

        panel.addComponent(content);
        return panel;
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

    Window notifications;

    private void buildNotifications(ClickEvent event) {
        notifications = new Window("Notifications");
        VerticalLayout l = new VerticalLayout();
        l.setMargin(true);
        l.setSpacing(true);
        notifications.setContent(l);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setClosable(false);
        notifications.setResizable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - event.getRelativeX());
        notifications.setPositionY(event.getClientY() - event.getRelativeY());
        notifications.setCloseShortcut(KeyCode.ESCAPE, null);

        Label label = new Label(
                "<hr><b>"
                        + "Huy Lieu"
                        + " "
                        + "TA"
                        + " created a new report</b><br><span>25 minutes ago</span><br>"
                        + "In the Battle of the Bismarck Sea during World War II, "
                        + "American and Australian aircraft attacked a Japanese convoy, "
                        + "causing heavy troop losses. In December 1942, the Japanese Imperial "
                        + "General Headquarters decided to reinforce their position in the South West Pacific",ContentMode.HTML);
        l.addComponent(label);

        label = new Label(
                "<hr><b>"
                        + "Huy Lieu"
                        + " "
                        + "TA"
                        + " created a new report</b><br><span>25 minutes ago</span><br>"
                        + "In the Battle of the Bismarck Sea during World War II, "
                        + "American and Australian aircraft attacked a Japanese convoy, "
                        + "causing heavy troop losses. In December 1942, the Japanese Imperial "
                        + "General Headquarters decided to reinforce their position in the South West Pacific",ContentMode.HTML);
        l.addComponent(label);
    }

    private void buildPopularArticles()
    {
        popularArticles = new Table() {
            @Override
            protected String formatPropertyValue(Object rowId, Object colId,
                                                 Property<?> property) {
                if (colId.equals("Revenue")) {
                    if (property != null && property.getValue() != null) {
                        Double r = (Double) property.getValue();
                        String ret = new DecimalFormat("#.##").format(r);
                        return "$" + ret;
                    } else {
                        return "";
                    }
                }
                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        popularArticles.setCaption("Top 10 Popular Articles");

        popularArticles.setWidth("100%");
        popularArticles.setPageLength(0);
        popularArticles.addStyleName("plain");
        popularArticles.addStyleName("borderless");
        popularArticles.setSortEnabled(false);
        popularArticles.setColumnAlignment("Revenue", Align.RIGHT);
        popularArticles.setRowHeaderMode(RowHeaderMode.INDEX);

    }

    private void buildLastAddedArticles()
    {
        lastAddedArticles = new Table() {
            @Override
            protected String formatPropertyValue(Object rowId, Object colId,
                                                 Property<?> property) {
                if (colId.equals("Revenue")) {
                    if (property != null && property.getValue() != null) {
                        Double r = (Double) property.getValue();
                        String ret = new DecimalFormat("#.##").format(r);
                        return "$" + ret;
                    } else {
                        return "";
                    }
                }
                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        lastAddedArticles.setCaption("Last 10 Added Articles");

        lastAddedArticles.setWidth("100%");
        lastAddedArticles.setPageLength(0);
        lastAddedArticles.addStyleName("plain");
        lastAddedArticles.addStyleName("borderless");
        lastAddedArticles.setSortEnabled(false);
        lastAddedArticles.setColumnAlignment("Revenue", Align.RIGHT);
        lastAddedArticles.setRowHeaderMode(RowHeaderMode.INDEX);

    }

    private void buildLastEditedArticles()
    {
        lastEditedArticles = new Table() {
            @Override
            protected String formatPropertyValue(Object rowId, Object colId,
                                                 Property<?> property) {
                if (colId.equals("Revenue")) {
                    if (property != null && property.getValue() != null) {
                        Double r = (Double) property.getValue();
                        String ret = new DecimalFormat("#.##").format(r);
                        return "$" + ret;
                    } else {
                        return "";
                    }
                }
                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        lastEditedArticles.setCaption("Last 10 Added Articles");

        lastEditedArticles.setWidth("100%");
        lastEditedArticles.setPageLength(0);
        lastEditedArticles.addStyleName("plain");
        lastEditedArticles.addStyleName("borderless");
        lastEditedArticles.setSortEnabled(false);
        lastEditedArticles.setColumnAlignment("Revenue", Align.RIGHT);
        lastEditedArticles.setRowHeaderMode(RowHeaderMode.INDEX);

    }

}
