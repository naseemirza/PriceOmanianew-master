package thinkbiz.solutions.tbs.com;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    Button LoginBtn, Regbtn;
    TextView frgtpass;

    EditText editTextEmail,editTextPass;
    Button buttonLogn;
    ProgressDialog progressDialog;
    public static Boolean booltype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setTitle("Login Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        booltype=pref.getBoolean("Booltype", Boolean.parseBoolean(""));

        if(booltype){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        editTextEmail=(EditText)findViewById(R.id.editTextFP);
        editTextPass=(EditText)findViewById(R.id.editTextP);

        frgtpass=(TextView) findViewById(R.id.textViewfrgt);
        frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPassActivity.class));
            }
        });

        LoginBtn=(Button)findViewById(R.id.buttonL);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(LoginActivity.this,MainActivity.class));

                if (isOnline()) {

                } else {
                    try {

                        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(LoginActivity.this).create();
                        alertDialog.setTitle("Info");
                        alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(LoginActivity.this,LoginActivity.class));
                            }
                        });
                        alertDialog.show();
                    } catch (Exception e) {

                    }
                }

                if(isValidate())
                {
                    Loginbtn();
                }
            }
        });

        Regbtn=(Button)findViewById(R.id.buttonFP);
        Regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            // Toast.makeText(this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isValidate()
    {
        final String email = editTextEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return false;
        }

        if (editTextPass.getText().toString().length() == 0) {
            editTextPass.setError("Password not entered");
            editTextPass.requestFocus();
            return false;
        }
        if (editTextPass.getText().toString().length() < 5) {
            editTextPass.setError("Password should be atleast of 5 charactors");
            editTextPass.requestFocus();
            return false;
        }

        return true;
    }

    private void Loginbtn() {

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPass.getText().toString().trim();

       // https://ae.priceomania.com/mobileappwebservices/login?email="+email+"&password="+password
      //  String url="https://ae.priceomania.com/mobileappwebservices/login?";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ae.priceomania.com/mobileappwebservices/login?email="+email+"&password="+password,
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
                           // String user_id=obj.getString("user_id");
                          //  Log.e("uid",user_id);
                            // String name=obj.getString("user_name");
                            //  String email=obj.getString("user_email");
                            //String phone=obj.getString("user_phone");

                            if (success.equalsIgnoreCase("1"))
                            {
                                booltype=true;

                                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();
                               // edit.putString("Myname",name);
                               // edit.putString("user_id",user_id);
                                // edit.putString("name",name);
                                //edit.putString("email",email);
                                edit.putBoolean("Booltype",booltype);
                               // edit.putBoolean("Booltype1",ads);
                                // edit.putString("phone",phone);

                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                edit.apply();
                                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                editTextEmail.setText("");
                                editTextPass.setText("");
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
//        {
//            @Override
//
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", email);
//                params.put("password", password);
//                return params;
//            }
//        };

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(stringRequest);
    }
}
