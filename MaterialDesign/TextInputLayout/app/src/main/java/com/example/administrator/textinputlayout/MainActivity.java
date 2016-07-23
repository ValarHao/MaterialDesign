package com.example.administrator.textinputlayout;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * TextInputLayout的一些方法：
         * getEditText() => 得到包含的EditText控件
         * setHint() => 设置提示信息
         * setError() => 设置错误信息并显示在EditText下方
         * setErrorEnabled() => Error信息是否显示，该方法一定要在setError()之后执行
         */
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.id_username_input);
        EditText userName = textInputLayout.getEditText();
        textInputLayout.setHint("请输入用户名");
        textInputLayout.setError("用户名输入错误！");
        textInputLayout.setErrorEnabled(true); //将错误信息显示或不显示

        TextInputEditText password = (TextInputEditText) findViewById(R.id.id_password);
        password.setHint("请输入密码");
        password.setError("密码输入错误！");
    }
}
