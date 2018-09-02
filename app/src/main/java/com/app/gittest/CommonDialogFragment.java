package com.app.gittest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class CommonDialogFragment extends DialogFragment {

    private OnDialogCallBack mOnDialogCallBack;
    private int mThemeId;
    private int mAnimId;

    public interface OnDialogCallBack {

        void onConfirm();

        void onCancel();
    }

    public static CommonDialogFragment newInstance(int themeId, int animId, @NonNull OnDialogCallBack call) {
        CommonDialogFragment instance = new CommonDialogFragment();
        instance.mOnDialogCallBack = call;
        instance.mThemeId = themeId;
        instance.mAnimId = animId;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return create(getContext(), mThemeId);
    }

    class CommonDialog extends Dialog {
        CommonDialog(@NonNull Context context) {
            this(context, 0);
        }

        CommonDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
            init(context);
        }

        private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.tv_confirm) {
                    mOnDialogCallBack.onConfirm();
                } else if (id == R.id.tv_cancel) {
                    mOnDialogCallBack.onCancel();
                }
            }
        };

        private void init(Context context) {
            View contentView = View.inflate(context, R.layout.layout_custom_dialog, null);
            setContentView(contentView);
            Window window = getWindow();
            if (window != null) {
                if (mAnimId > 0) {
                    window.setWindowAnimations(mAnimId);
                }
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = 900;
            }
            contentView.findViewById(R.id.tv_confirm).setOnClickListener(listener);
            contentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);
        }
    }

    CommonDialog create(Context context, int themeId) {
        if (themeId <= 0) {
            return new CommonDialog(context, R.style.dialog);
        }
        return new CommonDialog(context, themeId);
    }
}
