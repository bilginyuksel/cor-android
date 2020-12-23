package com.cor.android.framework;

import android.content.Context;
import android.widget.Toast;

import com.cor.android.framework.basef.CordovaBaseModule;
import com.cor.android.framework.basef.CordovaMethod;
import com.cor.android.framework.basef.handler.CorError;
import com.cor.android.framework.basef.handler.CorException;
import com.cor.android.framework.basef.handler.CorLog;
import com.cor.android.framework.basef.handler.CorPack;
import com.cor.android.framework.basef.handler.Promise;

import org.json.JSONArray;
import org.json.JSONException;

public class Test extends CordovaBaseModule {

    @CordovaMethod
    public void showToast(final CorPack corPack, JSONArray args, final Promise promise) throws JSONException {
        String message = args.getString(0);
        Context context = corPack.getCordovaWebView().getContext();
        corPack.getCordova().getActivity().runOnUiThread(() -> {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            promise.success();
        });
    }
}

