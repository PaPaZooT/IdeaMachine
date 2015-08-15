package hr.matvidako.ideamachine.base;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import hr.matvidako.ideamachine.R;

public class AddItemDialogBuilder {

    public static MaterialDialog build(final Context context, int hintStringResId, String startingText, OnAddListener onAddListener) {
        View dialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_new_item, null, false);
        final EditText etNewItem = (EditText) dialogContentView.findViewById(R.id.new_item);
        etNewItem.setText(startingText);
        etNewItem.setHint(hintStringResId);
        return build(context, etNewItem, dialogContentView, onAddListener);
    }

    public static MaterialDialog build(final Context context, int hintStringResId, OnAddListener onAddListener) {
        return build(context, hintStringResId, "", onAddListener);
    }

    public static MaterialDialog build(final Context context, final EditText etInput, View dialogContentView, final OnAddListener onAddListener) {
        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(dialogContentView, false)
                .positiveText(R.string.add)
                .negativeText(R.string.add_and_next)
                .neutralText(R.string.cancel)
                .autoDismiss(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        onAddListener.onAdd(etInput.getText().toString());
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        onAddListener.onAdd(etInput.getText().toString());
                        etInput.setText("");
                        etInput.requestFocus();
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                }).build();


        showKeyboard(context, etInput);
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onAddListener.onAdd(etInput.getText().toString());
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

    public interface OnAddListener {
        void onAdd(String text);
    }

}
