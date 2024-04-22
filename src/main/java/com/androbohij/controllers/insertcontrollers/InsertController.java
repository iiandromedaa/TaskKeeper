package com.androbohij.controllers.insertcontrollers;

import com.androbohij.controllers.AddTaskController;
import com.androbohij.controllers.Controller;

public abstract class InsertController extends Controller {
    
    private AddTaskController parent;
    
    public abstract void initialize();
    
}
