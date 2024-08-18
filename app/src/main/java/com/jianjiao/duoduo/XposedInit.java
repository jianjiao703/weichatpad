package com.jianjiao.duoduo;


import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedInit implements IXposedHookLoadPackage {
    public String TAG = "__尖叫__xp";
    ClassLoader classLoader;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws ClassNotFoundException {
        classLoader = lpparam.classLoader;
        //!"com.baidu.searchbox".equals(lpparam.packageName) ||
        if (!"com.tencent.mm".equals(lpparam.packageName)) {
            return;
        }
        Log.d(TAG, "开始hook：" + lpparam.packageName);
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.ei", lpparam.classLoader, "A", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(Boolean.TRUE);
            }
        });
    }
}