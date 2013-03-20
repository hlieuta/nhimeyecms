package com.nhimeye.admin.fieldview;

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

public class NumberFieldView extends FieldTypeView {

    TextField maxValue;
    TextField minValue;
    TextField defaultValue;

    public NumberFieldView(FieldDetailsView detailsView) {
        super(detailsView);
        maxValue = detailsView.fieldGroup.buildAndBind("Max value:", "max", TextField.class);
        maxValue.setConverter(Integer.class);
        minValue = detailsView.fieldGroup.buildAndBind("Min value:", "min", TextField.class);
        minValue.setConverter(Integer.class);
        defaultValue = detailsView.fieldGroup.buildAndBind("Default value:", "defaultValue", TextField.class);
        defaultValue.setNullRepresentation("");
        defaultValue.addValidator(new Validator() {
            @Override
            public void validate(Object value) throws InvalidValueException {
                if (maxValue != null && minValue != null && defaultValue != null && defaultValue.getValue() != null && defaultValue.getValue() != "") {
                   try
                   {

                        Integer max = (Integer)maxValue.getConvertedValue();
                        Integer min = (Integer)minValue.getConvertedValue();
                        Integer df = Integer.parseInt(defaultValue.getValue());
                       if (df < min || df > max) {
                           throw new InvalidValueException("Default value does not fall between min and max.");
                       }
                   }catch (Exception ex)
                   {
                       throw new InvalidValueException(ex.getMessage());
                   }


                }

            }
        });

        minValue.addValidator(new Validator() {
            @Override
            public void validate(Object value) throws InvalidValueException {
                if(minValue != null && maxValue != null)
                {
                    try
                    {
                        Integer max = (Integer)maxValue.getConvertedValue();
                        Integer min = (Integer)minValue.getConvertedValue();
                        if(min > max)
                        {
                            throw new InvalidValueException("Min value can not be greater than max value.");
                        }
                    }catch (Exception ex)
                    {
                        throw new InvalidValueException(ex.getMessage());
                    }
                }
            }
        });

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
