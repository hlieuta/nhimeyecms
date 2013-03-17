package com.nhimeye.admin;/*
 * Copyright 2013 NhimEye Inc.
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

import com.nhimeye.admin.event.ConfirmDeleteListener;
import com.nhimeye.admin.event.UnSavedChangesListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.*;

public class ConfirmWindow extends Window {

       private ConfirmDeleteListener confirmDeleteListener;
       private UnSavedChangesListener unSavedChangesListener;

    public void addConfirmDeleteListener(ConfirmDeleteListener confirmDeleteListener)
    {
        this.confirmDeleteListener = confirmDeleteListener;
    }

    public void addUnSavedChangesListener(UnSavedChangesListener unSavedChangesListener)
    {
        this.unSavedChangesListener = unSavedChangesListener;
    }

    public ConfirmWindow(String caption,String message, Type type)
    {
        super(caption);
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        layout.setWidth("400px");
        layout.setMargin(true);
        layout.setSpacing(true);

        setModal(true);
        setResizable(false);
        setDraggable(false);
        addStyleName("dialog");
        setClosable(false);

        Label messageLabel = new Label(message);
        layout.addComponent(messageLabel);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth("100%");
        buttons.setSpacing(true);
        layout.addComponent(buttons);

        switch (type)
        {
             case UnSavedChanges:
                 Button discard = new Button("Don't Save");
                 discard.addStyleName("small");
                 discard.addClickListener(new Button.ClickListener() {
                     @Override
                     public void buttonClick(Button.ClickEvent event) {
                         if(unSavedChangesListener != null)
                             unSavedChangesListener.buttonDiscardClick();
                         close();
                     }
                 });
                 buttons.addComponent(discard);
                 buttons.setExpandRatio(discard, 1);

                 Button cancel = new Button("Cancel");
                 cancel.addStyleName("small");
                 cancel.addClickListener(new Button.ClickListener() {
                     @Override
                     public void buttonClick(Button.ClickEvent event) {
                         close();
                     }
                 });
                 buttons.addComponent(cancel);

                 Button ok = new Button("Save");
                 ok.addStyleName("default");
                 ok.addStyleName("small");
                 ok.addStyleName("wide");
                 ok.addClickListener(new Button.ClickListener() {
                     @Override
                     public void buttonClick(Button.ClickEvent event) {
                         if(unSavedChangesListener!= null)
                             unSavedChangesListener.buttonSaveClick();
                         close();
                     }
                 });
                 buttons.addComponent(ok);
                 ok.focus();

                 addShortcutListener(new ShortcutListener("Cancel",
                         ShortcutAction.KeyCode.ESCAPE, null) {
                     @Override
                     public void handleAction(Object sender, Object target) {
                         close();

                     }
                 });
                 break;
             case DeleteConfirm:

                 Label spacer = new Label();
                 buttons.addComponent(spacer);
                 buttons.setExpandRatio(spacer, 1);
                 Button dCancel = new Button("Cancel");
                 dCancel.addStyleName("small");
                 dCancel.addClickListener(new Button.ClickListener() {
                     @Override
                     public void buttonClick(Button.ClickEvent event) {
                         close();
                     }
                 });
                 buttons.addComponent(dCancel);

                 Button dDelete = new Button("Delete");
                 dDelete.addStyleName("danger");
                 dDelete.addStyleName("small");
                 dDelete.addStyleName("wide");
                 dDelete.addClickListener(new Button.ClickListener() {
                     @Override
                     public void buttonClick(Button.ClickEvent event) {
                         close();
                         if(confirmDeleteListener != null)
                             confirmDeleteListener.buttonDeleteClick();
                     }
                 });
                 buttons.addComponent(dDelete);
                 dDelete.focus();

                 addShortcutListener(new ShortcutListener("Cancel",
                         ShortcutAction.KeyCode.ESCAPE, null) {
                     @Override
                     public void handleAction(Object sender, Object target) {
                         close();
                     }
                 });
                 break;
        }




    }

    public enum Type {

        UnSavedChanges, DeleteConfirm;
    }


}
