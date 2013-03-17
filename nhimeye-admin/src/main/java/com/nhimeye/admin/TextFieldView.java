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

public class TextFieldView extends FieldTypeView{

    TextField maxLenght;
    TextField defaultValue;

    TextFieldView(FieldDetailsView detailsView) {
        super(detailsView);
        maxLenght = new TextField("Max length:");
        defaultValue = new TextField("Default value:");
        detailsView.addComponent(maxLenght);
        detailsView.addComponent(defaultValue);
    }

    @Override
    public void removeComponents() {
        detailsView.removeComponent(maxLenght);
        detailsView.removeComponent(defaultValue);
    }
}
