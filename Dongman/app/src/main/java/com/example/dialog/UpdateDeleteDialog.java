package com.example.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.example.R;

/**
 * UpdateDeleteDialog
 *
 * @author fangyuan
 * @date 2019-07-08
 */
public class UpdateDeleteDialog extends Dialog {
    public interface OnDialogClickListener {
        void onUpdate();
        
        void onDelete();
    }
    
    private OnDialogClickListener mClickListener;
    
    public void setOnDialogClickListener(OnDialogClickListener listener) {
        this.mClickListener = listener;
    }
    
    public UpdateDeleteDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_update_delete);
        findViewById(R.id.tv_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onUpdate();
                }
                dismiss();
            }
        });
        
        findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onDelete();
                }
                dismiss();
            }
        });
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutParams layoutParams = window.getAttributes();
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        layoutParams.width = (int) (screenWidth * 0.77f);
        window.setAttributes(layoutParams);
    }
}
