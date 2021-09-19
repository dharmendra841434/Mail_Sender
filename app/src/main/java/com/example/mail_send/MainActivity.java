package com.example.mail_send;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText n,m,e,mess;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n=(EditText) findViewById(R.id.myname);
        n.setOnFocusChangeListener(this);
        m=(EditText) findViewById(R.id.mymobile);
        m.setOnFocusChangeListener(this);
        e=(EditText) findViewById(R.id.myemail);
        e.setOnFocusChangeListener(this);
        mess=(EditText) findViewById(R.id.mymessage);
        mess.setOnFocusChangeListener(this);
        sub = (Button) findViewById(R.id.submit);



        sub.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                String name = n.getText().toString();
                String mobile = m.getText().toString();
                String email = e.getText().toString();
                String message = mess.getText().toString();





                if (!name.isEmpty())
                {
                    n.setEnabled(true);
                    n.setError(null);
                    if (!mobile.isEmpty())
                    {
                        m.setEnabled(true);
                        m.setError(null);
                        if (!email.isEmpty())
                        {
                            e.setError(null);
                            e.setEnabled(true);
                            if (!message.isEmpty())
                            {
                                mess.setError(null);
                                mess.setEnabled(true);

                                sendemail();
                            }
                            else
                            {
                                mess.setError("Provide Some Message");
                            }

                        }
                        else
                        {
                            e.setError("Enetr Email id");
                        }

                    }else
                    {
                        m.setError("Enter Mobile Number");
                    }
                }else
                {
                    n.setError("Enter Name");
                }

            }
        });
    }

    private void sendemail() {

        String name = n.getText().toString();
        String mob = m.getText().toString();
        String email = e.getText().toString();
        String message = "Name :"+name+"\nMobile Number : "+mob+"\nEmail :"+email+"\n\n\n"+mess.getText().toString();


        String tosendemail = "info@redpositive.in";

        JavaMailAPI sender = new JavaMailAPI(MainActivity.this,tosendemail,"Contact Details",message);
        sender.execute();
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        String vname = n.getText().toString();
        String vmobile = m.getText().toString();
        String vemail = e.getText().toString();

        switch (view.getId())
        {
            case R.id.myname:
                if (!Pattern.matches("^[A-Za-z  ]{0,200}$", vname)) {
                    n.setError("only Alphabet used");
                } else {
                    n.setError(null);
                    n.setEnabled(true);
                }
                break;
            case R.id.mymobile:

                if (!Pattern.matches("(0/91)?[7-9][0-9]{9}", vmobile)) {
                    m.setError("Entert Valid Mobile Number");
                } else {
                    m.setError(null);
                    m.setEnabled(true);
                }
                break;
            case R.id.myemail:
                if (!Pattern.matches("^[a-zA-Z0-9]{0,20}+[@]{1}+[a-zA-Z0-9]{0,20}+[.]{1}+[a-zA-Z0-9]{0,20}$", vemail)) {
                    e.setError("enter vailed email");
                } else {
                    e.setError(null);
                    e.setEnabled(true);
                }
                break;
        }

    }
}