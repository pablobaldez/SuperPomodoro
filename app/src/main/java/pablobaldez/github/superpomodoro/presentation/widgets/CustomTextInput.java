package pablobaldez.github.superpomodoro.presentation.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

/**
 * @author Pablo
 * @since 08/09/2016
 */
public class CustomTextInput extends TextInputEditText {
    public CustomTextInput(Context context) {
        super(context);
        init();
    }

    public CustomTextInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 1 && charSequence != " ") {
                    setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText().toString().trim());
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        TextInputLayout inputLayout = (TextInputLayout) getParent();
        inputLayout.setError(error);
    }

    public void setOnActionDoneClickListener(OnClickListener listener) {
        setOnEditorActionListener((v, actionId, event) -> {
            if (isActionDoneClicked(actionId, event)) {
                listener.onClick(this);
                return true;
            }
            return false;
        });
    }

    public String getString() {
        return super.getText().toString();
    }

    private boolean isActionDoneClicked(int actionId, KeyEvent event) {
        return (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_DONE);
    }

}
