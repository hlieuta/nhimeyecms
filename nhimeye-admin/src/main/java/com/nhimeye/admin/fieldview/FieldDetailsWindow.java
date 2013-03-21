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

import com.google.common.eventbus.EventBus;
import com.nhimeye.admin.ConfirmWindow;
import com.nhimeye.admin.event.NewItemAddedEvent;
import com.nhimeye.admin.event.UnSavedChangesListener;
import com.nhimeye.data.domain.Field;
import com.nhimeye.data.service.FieldService;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction = true)
public class FieldDetailsWindow extends Window {

    private FieldDetailsView form;
    private static final Logger LOGGER = LoggerFactory.getLogger(FieldDetailsWindow.class);

    @Autowired
    private FieldService fieldService;

    public FieldDetailsWindow(Field field, final EventBus eventBus) {
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSpacing(true);
        if(field.getName() == null)
        {
            setCaption("New Field");
        } else
        {
            setCaption("Edit: " + field.getName());
        }
        setContent(vLayout);
        center();
        setModal(true);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        setResizable(false);
        setClosable(false);

        addStyleName("no-vertical-drag-hints");
        addStyleName("no-horizontal-drag-hints");

        buildForm(field);
        vLayout.addComponent(form);


        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName("footer");
        footer.setWidth("100%");
        footer.setMargin(true);

        HorizontalLayout buttonHolder = new HorizontalLayout();
        buttonHolder.setWidth(200,Unit.PIXELS);
        buttonHolder.setMargin(true);
        buttonHolder.setSpacing(true);
        Button ok = new Button("Save");
        ok.addStyleName("wide");
        ok.addStyleName("default");
        ok.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                     if(save(eventBus))
                     {
                         close();
                     }
            }
        });
        buttonHolder.addComponent(ok);

        Button close = new Button("Close");
        close.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(unSavedChanges())
                {
                    ConfirmWindow confirmWindow = new ConfirmWindow("Unsaved Changes","You have not saved field. Do you want to save or discard any changes you've made to this field?", ConfirmWindow.Type.UnSavedChanges);
                    getUI().addWindow(confirmWindow);
                    confirmWindow.addUnSavedChangesListener(new UnSavedChangesListener() {
                        @Override
                        public void buttonSaveClick() {
                          if(save(eventBus))
                          {
                              close();
                          }
                        }

                        @Override
                        public void buttonDiscardClick() {
                            close();
                        }
                    });
                }else
                {
                    close();
                }


            }
        });
        buttonHolder.addComponent(close);
        footer.addComponent(buttonHolder);
        footer.setComponentAlignment(buttonHolder, Alignment.TOP_RIGHT);
        vLayout.addComponent(footer);
    }

    private boolean unSavedChanges() {
        return form.fieldGroup.isModified();
    }

    private Component buildForm(Field field) {
        form = new FieldDetailsView(field);
        return form;
    }

    private boolean save(EventBus eventBus)
    {
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