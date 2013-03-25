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
import com.nhimeye.admin.event.NewItemAddedEvent;
import com.nhimeye.data.domain.Field;
import com.nhimeye.data.service.FieldService;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction = true)
public class FieldDetailsWindow extends AbstractEntityWindow<Field> {

    private FieldDetailsView form;

    @Autowired
    private FieldService fieldService;

    public FieldDetailsWindow(Field entity, EventBus eventBus) {
        super(entity, eventBus);
        if(entity.getName() == null)
        {
            setCaption("New Field");
        } else
        {
            setCaption("Edit: " + entity.getName());
        }
    }


    @Override
    public Component buildComponents(Field field) {
        form = new FieldDetailsView(field);
        return form;
    }

    @Override
    protected boolean isModified() {
        return form.fieldGroup.isModified();
    }

    @Override
    protected boolean save(EventBus eventBus) {
        try
        {
            form.commit();
            fieldService.saveField(form.getEntity());
            eventBus.post(new NewItemAddedEvent());
            return true;
        }catch (FieldGroup.CommitException e)
        {
            Notification
                    .show("Commit failed: " + e.getCause().getMessage(),
                            Notification.Type.TRAY_NOTIFICATION);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage(),e);
            }

        }
        return false;
    }
}