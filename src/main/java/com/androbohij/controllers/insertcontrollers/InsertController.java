package com.androbohij.controllers.insertcontrollers;

import com.androbohij.controllers.AddTaskController;
import com.androbohij.controllers.Controller;

public abstract class InsertController extends Controller {
    
    private AddTaskController parent;
    
    public abstract void bindings();

    public AddTaskController getParent() {
        return parent;
    }

    public void setParent(AddTaskController parent) {
        this.parent = parent;
    }
    
}
