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
import com.nhimeye.admin.event.UnSavedChangesListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEntityEditor<E> extends Window {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityEditor.class);

    public AbstractEntityEditor(E entity, final EventBus eventBus) {
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSpacing(true);
        setContent(vLayout);
        center();
        setModal(true);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        setResizable(false);
        setClosable(false);

        addStyleName("no-vertical-drag-hints");
        addStyleName("no-horizontal-drag-hints");

        vLayout.addComponent(buildComponents(entity));


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
                if(isModified())
                {
                    ConfirmWindow confirmWindow = new ConfirmWindow("Unsaved Changes","You have not saved the changes. Do you want to save or discard any changes you've made to this item?", ConfirmWindow.Type.UnSavedChanges);
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

    public abstract  Component buildComponents(E entity);

    protected abstract  boolean isModified();

    protected abstract boolean save(EventBus eventBus);
}
