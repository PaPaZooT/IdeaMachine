package hr.matvidako.ideamachine.base;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import hr.matvidako.ideamachine.R;

public class UpdateItemDialogBuilder {
    public static MaterialDialog build(final Context context, final EditText etInput, View dialogContentView, final OnUpdateListener onUpdateListener) {
        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(dialogContentView, false)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .autoDismiss(true)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        onUpdateListener.onUpdate(etInput.getText().toString());
                    }
                }).build();

        showKeyboard(context, etInput);
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onUpdateListener.onUpdate(etInput.getText().toString());
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        return dialog;
    }

    private static void showKeyboard(final Context context, final View viewInFocus) {
        viewInFocus.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                viewInFocus.requestFocus();
                mgr.showSoftInput(viewInFocus, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);
    }

    public interface OnUpdateListener {
        void onUpdate(String text);
    }

}
