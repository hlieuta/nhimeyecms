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

import com.nhimeye.data.domain.Field;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.TextField;

public class TextFieldView extends FieldTypeView{

    com.vaadin.ui.Field<?> maxLength = null;
    TextField defaultValue;

    TextFieldView(FieldDetailsView detailsView) {
        super(detailsView);
        maxLength = detailsView.fieldGroup.buildAndBind("Max Length:","maxLength");
        defaultValue = detailsView.fieldGroup.buildAndBind("Default Value:","defaultValue",TextField.class);
        defaultValue.setNullRepresentation("");
        maxLength.addValidator(new BeanValidator(Field.class,"maxLength"));
        defaultValue.addValidator(new Validator() {
            @Override
            public void validate(Object value) throws InvalidValueException {
                if(maxLength != null)
                {
                    final int length = Integer.parseInt(maxLength.getValue().toString());
                    if(length> 0 && length < defaultValue.getValue().toString().length())
                    {
                        throw new InvalidValueException("Default Value can no be grater than Max Length.");
                    }
                }
            }
        });
        detailsView.addComponent(maxLength);
        detailsView.addComponent(defaultValue);
    }

    @Override
    public void removeComponents() {
        detailsView.fieldGroup.unbind(maxLength);
        detailsView.fieldGroup.unbind(defaultValue);
        detailsView.removeComponent(maxLength);
        detailsView.removeComponent(defaultValue);

    }
}
