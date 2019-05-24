package thinkbiz.solutions.tbs.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    EditText editTextname,editTextEmail,editTextPhn,editTextPass,editTextConfPass;
    Button buttonReg;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setTitle("Register here");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextname=(EditText)findViewById(R.id.editTextNm);
        editTextEmail=(EditText)findViewById(R.id.editTextem);
        editTextPhn=(EditText)findViewById(R.id.editTextphn);
        editTextPass=(EditText)findViewById(R.id.editTextpas);
        editTextConfPass=(EditText)findViewById(R.id.editTextps1);

        buttonReg=(Button)findViewById(R.id.buttonFP);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate())
                {
                    Regst();
                }
            }
        });

    }

    private boolean isValidate()
    {
        final String email = editTextEmail.getText().toString().trim();

        if (editTextname.getText().toString().length() == 0) {
            editTextname.setError("Name not entered");
            editTextname.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return false;
        }
        if (editTextPhn.getText().toString().length() == 0) {
            editTextPhn.setError("Phone number not entered");
            editTextPhn.requestFocus();
            return false;
        }


        if (editTextPass.getText().toString().length() == 0) {
            editTextPass.setError("Password not entered");
            editTextPass.requestFocus();
            return false;
        }

        if (editTextConfPass.getText().toString().length() == 0) {
            editTextConfPass.setError("Please confirm password");
            editTextConfPass.requestFocus();
            return false;
        }

        if (!editTextPass.getText().toString().equals(editTextConfPass.getText().toString())) {
            editTextConfPass.setError("Password Not matched");
            editTextConfPass.requestFocus();
            return false;
        }

        if (editTextPass.getText().toString().length() < 5) {
            editTextPass.setError("Password should be atleast of 5 charactors");
            editTextPass.requestFocus();
            return false;
        }
        return true;
    }

    private void Regst(){
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        final String name = editTextname.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String phone = editTextPhn.getText().toString().trim();
        final String password = editTextPass.getText().toString().trim();

       //"https://ae.priceomania.com/mobileappwebservices/registration?name="+name+"&phone="+phone+"&email="+email+"&password="+password;
        String url="https://ae.priceomania.com/mobileappwebservices/registration?";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://ae.priceomania.com/mobileappwebservices/registration?name="+name+"&phone="+phone+"&email="+email+"&password="+password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("resp",response);
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            String success= obj.getString("s");
                            String error= obj.getString("e");
                            String msg=obj.getString("m");

                            if (success.equalsIgnoreCase("1"))
                            {

//                                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor edit = pref.edit();
//                                edit.putString("Myname",name);
//                                edit.putString("Myemail",email);
//                                edit.putString("Myphone",phone);
//                                edit.apply();

                                Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                progressDialog.dismiss();
                                editTextname.setText("");
                                editTextEmail.setText("");
                                editTextPhn.setText("");
                                editTextPass.setText("");
                                editTextConfPass.setText("");

                            }
                            else {
                                Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
//
//        {
//            @Override
//
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name);
//                params.put("phone", phone);
//                params.put("email", email);
//                params.put("password", password);
//                return params;
//            }
//        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
