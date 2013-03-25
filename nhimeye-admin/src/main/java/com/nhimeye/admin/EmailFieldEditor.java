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

import com.vaadin.data.Validator;
import com.vaadin.ui.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFieldEditor extends FieldTypeEditor {
    TextField defaultValue;
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    EmailFieldEditor(FieldDetailsView detailsView) {
        super(detailsView);
        pattern = Pattern.compile(EMAIL_PATTERN);
        defaultValue = detailsView.fieldGroup.buildAndBind("Default value:", "defaultValue", TextField.class);
        defaultValue.setNullRepresentation("");
        detailsView.addComponent(defaultValue);
        defaultValue.addValidator(new Validator() {
            @Override
            public void validate(Object value) throws InvalidValueException {
                if(defaultValue!= null && defaultValue.getValue()!= null && defaultValue.getValue() != "")
                {
                     matcher = pattern.matcher(defaultValue.getValue());
                    if(!matcher.matches())
                        throw  new InvalidValueException("Please provide the valid email address.");
                }
            }
        });

    }

    @Override
    public void removeComponents() {
        detailsView.fieldGroup.unbind(defaultValue);
        detailsView.removeComponent(defaultValue);
    }
}
