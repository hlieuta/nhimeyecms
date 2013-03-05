package com.nhimeye.admin;

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


import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import java.util.Set;

public abstract class AbstractEntityView<E> extends VerticalLayout implements View {

    private Table table;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        setSizeFull();
        addStyleName("abstract");

        //Set table container
        getTable().setContainerDataSource(getTableContainer());

        //Build layout
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setWidth("100%");
        toolbar.setSpacing(true);
        toolbar.setMargin(true);
        toolbar.addStyleName("toolbar");
        addComponent(toolbar);

        Label title = getTitle();
        title.addStyleName("h1");
        title.setSizeUndefined();
        toolbar.addComponent(title);
        toolbar.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

        final TextField filter = new TextField();
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(final FieldEvents.TextChangeEvent event) {
                getTableContainer().removeAllContainerFilters();
                getTableContainer().addContainerFilter(new Filter() {
                    @Override
                    public boolean passesFilter(Object itemId, Item item)
                            throws UnsupportedOperationException {

                        if (event.getText() == null
                                || event.getText().equals("")) {
                            return true;
                        }
                         Set<String> properties = getFilterProperties();
                        if(properties != null)
                        {
                            for(String propertyId : properties)
                            {
                                    boolean match = filterByProperty(propertyId, item,
                                            event.getText());
                                if(match)
                                {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public boolean appliesToProperty(Object propertyId) {
                        Set<String> properties = getFilterProperties();
                        if(properties != null)
                        {
                            for(String cid : properties)
                            {
                                if(cid.equals(propertyId))
                                    return true;

                            }
                        }
                        return false;
                    }
                });
            }
        });

        filter.setInputPrompt("Filter");
        filter.addShortcutListener(new ShortcutListener("Clear",
                KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                filter.setValue("");
                getTableContainer().removeAllContainerFilters();
            }
        });
        toolbar.addComponent(filter);
        toolbar.setExpandRatio(filter, 1);
        toolbar.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);



        final Button newReport = new Button("Create new");
        newReport.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //createNewReportFromSelection();
            }
        });
        newReport.setEnabled(true);
        newReport.addStyleName("small");
        toolbar.addComponent(newReport);
        toolbar.setComponentAlignment(newReport, Alignment.MIDDLE_LEFT);

        addComponent(getTable());
        setExpandRatio(getTable(), 1);

        getTable().addActionHandler(new Handler() {

            private Action edit = new Action("Edit details");

            private Action deleted = new Action("Delete selected");


            @Override
            public void handleAction(Action action, Object sender, Object target) {
                if (action == edit) {
                    Notification.show("Not implemented in this demo");
                } else if (action == deleted) {
                    Notification.show("Not implemented in this demo");
                }
            }

            @Override
            public Action[] getActions(Object target, Object sender) {
                return new Action[]{edit, deleted};
            }
        });

       getTable().setImmediate(true);


    }

    private boolean filterByProperty(String prop, Item item, String text) {
        if (item == null || item.getItemProperty(prop) == null
                || item.getItemProperty(prop).getValue() == null)
            return false;
        String val = item.getItemProperty(prop).getValue().toString().trim()
                .toLowerCase();
        if (val.contains(text.toLowerCase().trim()))
            return true;

        return false;
    }



    protected Table getTable() {
        if (table == null) {
            table = new Table();
            table.setSizeFull();
            table.addStyleName("borderless");
            table.setSelectable(true);
            table.setColumnCollapsingAllowed(true);
            table.setColumnReorderingAllowed(true);
            table.setMultiSelect(true);
            configureTable(table);
        }
        return table;
    }

    protected abstract void configureTable(Table table);

    protected abstract Label getTitle();

   protected abstract IndexedContainer getTableContainer();

    protected abstract Set<String> getFilterProperties();
}
