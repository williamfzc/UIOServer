/*
MIT License

Copyright (c) 2018 William Feng

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.github.williamfzc.simhand2.ActionHandler;


import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.util.Log;

import com.github.williamfzc.simhand2.SHUtils;
import com.github.williamfzc.simhand2.Selector;

import java.util.Map;

public abstract class BaseActionHandler {
    private static final String TAG = "BaseActionHandler";

    UiDevice mDevice;

    // all possible args
    String widgetName;
    String delayTime;
    String actionName;
    String selector;
    String points;

    BaseActionHandler(UiDevice mDevice) {
        this.mDevice = mDevice;
    }

    void initParams(Map<String, String> targetMap) {
        this.widgetName = SHUtils.getParamFromMap(targetMap, "widgetName", "");
        this.delayTime = SHUtils.getParamFromMap(targetMap, "delayTime", "");
        this.actionName = SHUtils.getParamFromMap(targetMap, "actionName", "");
        this.selector = SHUtils.getParamFromMap(targetMap, "selector", "");
        this.points = SHUtils.getParamFromMap(targetMap, "points", "");
    }

    UiObject findElement() {
        UiObject targetElement;

        // invalid widget name
        if ("".equals(widgetName)) {
            return null;
        }

        // default selector: text
        if ("".equals(selector)) {
            selector = "text";
        }

        switch (selector) {
            case "id":
                targetElement = Selector.findElementById(mDevice, widgetName);
                break;
            case "text":
                targetElement = Selector.findElementByText(mDevice, widgetName);
                break;
            case "desc":
                targetElement = Selector.findElementByDesc(mDevice, widgetName);
                break;
            default:
                Log.w(TAG, "unexpected selector: " + selector);
                targetElement = Selector.findElementByText(mDevice, widgetName);
        }

        return targetElement;
    }

    public abstract boolean apply(Map<String, String> paramsMap);
}
