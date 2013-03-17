package com.nhimeye.admin;/*
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

import com.vaadin.ui.TextField;

public class NumberFieldView extends FieldTypeView {

    TextField maxValue;
    TextField minValue;
    TextField defaultValue;
    public NumberFieldView(FieldDetailsView detailsView) {
        super(detailsView);
        maxValue = new TextField("Max:");
        minValue = new TextField("Min:");
        defaultValue = new TextField("Default:") ;
        detailsView.addComponent(minValue);
        detailsView.addComponent(maxValue);
        detailsView.addComponent(defaultValue);

    }

    @Override
    public void removeComponents() {
        detailsView.removeComponent(maxValue);
        detailsView.removeComponent(minValue);
        detailsView.removeComponent(defaultValue);

    }
}
