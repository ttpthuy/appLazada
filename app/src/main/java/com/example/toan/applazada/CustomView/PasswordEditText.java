package com.example.toan.applazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.toan.applazada.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("AppCompatCustomView")
public class PasswordEditText extends EditText {

    Drawable eye, eyeStrike;
    boolean visible = false;
    boolean useTrike = false;
    boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .40f); // xác định độ mờ của image
    String MATCHER_PATTERN1 = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // Mẫu bắt lỗi cho password
    Pattern pattern1;
    Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
        khoitao(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao(attrs);
    }

    private void khoitao(AttributeSet attrs) {
        this.pattern1 = Pattern.compile(MATCHER_PATTERN1);
        if (attrs != null) {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);
            this.useTrike = array.getBoolean(R.styleable.PasswordEditText_useTrike, false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate, false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        if (this.useValidate) {
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {

                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) view.getParent().getParent();

                        matcher = pattern1.matcher(chuoi);
                        if(matcher.matches()){
                            textInputLayout.setErrorEnabled(false);
                        }else{
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu không hợp lệ, vui lòng nhập lại!");
                        }
                    }

                }
            });
        }
        caidat();
    }

    public void caidat() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useTrike && !visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);// làm mờ đi hình ảnh eye, hoặc làm đục đi hình ảnh
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { // Bắt sự kiện chạm màn hình
        // Lấy hành động chạm màn hình và vị trí của drawable eye để lựu chọn thao tác tắt hay mở eye cho phù hợp
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width() - 40)) { // Vị trí của drawable eye bằng vị trí X bên phải trừ cho phần bao bọc bên ngoài của drawable
            visible = !visible; // cho chạm nhiều lần và thay đổi giá trị sau mỗi lần chạm vào lại
            caidat();
            invalidate(); // kiểm tra sự kiện chạm vào màn hình
        }
        return super.onTouchEvent(event);
    }
}
