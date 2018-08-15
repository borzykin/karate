/*
 * The MIT License
 *
 * Copyright 2018 Intuit Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intuit.karate.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author pthomas3
 */
abstract class ResultElement extends HashMap<String, Object> {

    private final String name;
    private final List<StepResult> steps = new ArrayList();
    
    private boolean failed;
    private long duration;
    
    public ResultElement(String name) {
        this.name = name;
        put("name", name);
        put("steps", steps);
    }
    
    public void addStepResult(StepResult stepResult) {
        steps.add(stepResult);
        Result result = stepResult.getResult();
        duration += result.getDuration();
        if (result.isFailed()) {
            failed = true;
        }
    }    

    public String getName() {
        return name;
    }
        
    public long getDuration() {
        return duration;
    }

    public List<StepResult> getSteps() {
        return steps;
    }

    public boolean isFailed() {
        return failed;
    }        
    
    abstract boolean isBackground();

}
