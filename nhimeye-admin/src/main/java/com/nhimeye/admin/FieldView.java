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

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

import java.util.Set;

public class FieldView extends AbstractEntityView {


    @Override
    protected void configureTable(Table table) {

    }

    @Override
    protected Label getTitle() {
        return new Label("All Fields");
    }

    @Override
    protected IndexedContainer getTableContainer() {
        IndexedContainer revenue = new IndexedContainer();
        return revenue;
    }

    @Override
    protected Set<String> getFilterProperties() {
        return null;
    }
}