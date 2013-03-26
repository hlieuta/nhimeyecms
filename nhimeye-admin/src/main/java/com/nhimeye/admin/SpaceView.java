package com.nhimeye.admin;

import com.google.common.eventbus.EventBus;
import com.nhimeye.data.domain.Space;
import com.nhimeye.data.service.SpaceService;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

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
@Configurable(preConstruction = true)
public class SpaceView extends AbstractEntityView<Space> {

     private Button buttonSwitchSpace;
    @Autowired
    SpaceService spaceService;

    @Override
    protected void configureToolBar(HorizontalLayout toolbar) {
        buttonSwitchSpace = new Button("Switch to this space");
        buttonSwitchSpace.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((DashboardUI)getUI()).buildMenu(false);
            }
        });
        buttonSwitchSpace.setEnabled(false);
        buttonSwitchSpace.setImmediate(true);
        buttonSwitchSpace.addStyleName("small");
        toolbar.addComponent(buttonSwitchSpace);
        toolbar.setComponentAlignment(buttonSwitchSpace, Alignment.MIDDLE_LEFT);
    }

    @Override
    protected void configureTable(Table table) {
        table.setVisibleColumns(new Object[]{"name","spaceKey","spaceDomain"});
    }

    @Override
    protected Label getTitle() {
        return new Label("All Spaces");
    }

    @Override
    protected BeanContainer<BigInteger, Space> getTableContainer() {
        if (container == null) {
            container = new BeanContainer<BigInteger, Space>(Space.class);
            container.setBeanIdProperty("id");
            for (Space space : spaceService.findAllSpaces()) {
                container.addBean(space);
            }

        }
        return container;
    }

    @Override
    protected Set<String> getFilterProperties() {
        return new HashSet<String>(){
            {
                add("name");
                add("spaceKey");
                add("spaceDomain");


            }
        };
    }

    @Override
    protected void createNewButtonClicked(EventBus eventBus) {

    }

    @Override
    protected void refreshContainer() {

    }

    @Override
    protected void viewDetails(BigInteger id, EventBus eventBus) {

    }

    @Override
    protected boolean deleteItems(Set<BigInteger> selectedValues) {
        return false;
    }

    @Override
    protected void itemsSelected(Set<BigInteger> selectedValues)
    {

            if(buttonSwitchSpace != null && selectedValues != null)
            {
                buttonSwitchSpace.setEnabled(selectedValues.size() == 1);
            }
    }
}
