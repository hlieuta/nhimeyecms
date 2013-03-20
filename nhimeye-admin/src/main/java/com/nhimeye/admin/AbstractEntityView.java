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


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.nhimeye.admin.event.ConfirmDeleteListener;
import com.nhimeye.admin.event.NewItemAddedEvent;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
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

import java.math.BigInteger;
import java.util.Set;

public abstract class AbstractEntityView<E> extends VerticalLayout implements View {

    private Table table;
    private final EventBus eventBus = new EventBus();
    private Set<java.math.BigInteger> selectedValues = null;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        eventBus.register(this);
        setSizeFull();
        addStyleName("abstract");

        //Set table container
        getTable().setContainerDataSource(getTableContainer());
        configureTable(getTable());
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
                        if (properties != null) {
                            for (String propertyId : properties) {
                                boolean match = filterByProperty(propertyId, item,
                                        event.getText());
                                if (match) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public boolean appliesToProperty(Object propertyId) {
                        Set<String> properties = getFilterProperties();
                        if (properties != null) {
                            for (String cid : properties) {
                                if (cid.equals(propertyId))
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


        final Button buttonCreateNew = new Button("Create new");
        buttonCreateNew.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                createNewButtonClicked(eventBus);

            }
        });
        buttonCreateNew.setEnabled(true);
        buttonCreateNew.addStyleName("small");
        toolbar.addComponent(buttonCreateNew);
        toolbar.setComponentAlignment(buttonCreateNew, Alignment.MIDDLE_LEFT);

        addComponent(getTable());
        setExpandRatio(getTable(), 1);

        getTable().addActionHandler(new Handler() {

            private Action edit = new Action("Edit details");
            private Action deleted = new Action("Delete selected");


            @Override
            public void handleAction(Action action, Object sender, Object target) {
                if (action == edit) {
                    if(selectedValues.size() == 1)
                    {
                        viewDetails(selectedValues.iterator().next(),eventBus);
                    }else
                    {
                        Notification.show("Please select an item to edit details.", Notification.Type.ERROR_MESSAGE);
                    }

                } else if (action == deleted) {
                    if (selectedValues != null && selectedValues.size() > 0) {

                        ConfirmWindow confirmWindow = new ConfirmWindow("Delete Confirm", "Are you sure you want to delete the selected item?", ConfirmWindow.Type.DeleteConfirm);
                        getUI().addWindow(confirmWindow);
                        confirmWindow.addConfirmDeleteListener(new ConfirmDeleteListener() {
                            @Override
                            public void buttonDeleteClick() {
                                try {
                                    if (deleteItems(selectedValues)) {
                                        refreshTableData();
                                    }
                                } catch (Exception ex) {
                                    Notification.show("Can not delete the selected item. Please contact your system administrator", Notification.Type.ERROR_MESSAGE);
                                    ex.printStackTrace();
                                }
                            }
                        });


                    }

                }
            }

            @Override
            public Action[] getActions(Object target, Object sender) {
                return new Action[]{edit, deleted};
            }
        });

        getTable().setImmediate(true);

        getTable().addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                selectedValues = (Set<java.math.BigInteger>) event
                        .getProperty().getValue();
            }
        });


    }

    private void refreshTableData() {
        refreshContainer();
        getTable().setContainerDataSource(getTableContainer());
        configureTable(getTable());
    }

    private boolean filterByProperty(String prop, Item item, String text) {
        if (item == null || item.getItemProperty(prop) == null
                || item.getItemProperty(prop).getValue() == null)
            return false;
        String val = item.getItemProperty(prop).getValue().toString().trim()
                .toLowerCase();
        if (val.startsWith(text.toLowerCase().trim()))
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

        }
        return table;
    }

    @Subscribe
    public void onNewItemAdded(NewItemAddedEvent event) {
        refreshTableData();

    }

    protected abstract void configureTable(Table table);

    protected abstract Label getTitle();

    protected abstract BeanContainer<BigInteger, E> getTableContainer();

    protected abstract Set<String> getFilterProperties();

    protected abstract void createNewButtonClicked(EventBus eventBus);

    protected abstract void refreshContainer();

    protected abstract void viewDetails(BigInteger id,EventBus eventBus);

    protected abstract boolean deleteItems(Set<BigInteger> selectedValues);
}
