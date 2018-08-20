package thinkbiz.solutions.tbs.com.CardDetailsPkg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import thinkbiz.solutions.tbs.com.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ColorActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    ListView childListView;
    String ustring;

    public static List<ColorModel> colorList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        getSupportActionBar().setTitle("Filters");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Options"));


        childListView = (ListView) findViewById(R.id.list_viewclr);
        colorList = new ArrayList<>();


        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        //SharedPreferences example=getSharedPreferences("prod_id",0);
        ustring=pref.getString("usermessage","");


        Log.e("responce",ustring);

        loadItemList();

    }

    private void loadItemList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ae.priceomania.com/mobileappwebservices/getColor?pid=" +ustring ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("response", response);

                        try {
                            JSONObject rootJsonObject = new JSONObject(response);
                            //String  id = rootJsonObject.getString("category_id");
                            //Log.e("Id", id);

                            JSONArray subCategoryArray = rootJsonObject.getJSONArray("color");
                            Log.e("subCategoryArray", subCategoryArray.length() + "");

                            for (int i = 0; i < subCategoryArray.length(); i++) {

                                JSONObject detailsObject = subCategoryArray.getJSONObject(i);
                                colorList.add(new ColorModel(
                                        detailsObject.optString("color")
                                       ));


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ColorAdapter adapter = new ColorAdapter(colorList, getApplicationContext());
                        childListView.setAdapter(adapter);

                        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> arg0, View view,
                                                    int position, long arg3) {

                                TextView tv=(TextView)view.findViewById(R.id.textViewcolor);
                                String tag=tv.getText().toString();

                                Log.e("Id", tag);

//                                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor edit = pref.edit();
//
//                                Intent intent=new Intent();
//                                edit.putString("color",tag);
//                                setResult(2,intent);
//                                finish();

                                Intent intent=new Intent();
                                intent.putExtra("color",tag);
                                setResult(2,intent);
                                finish();

                                LinearLayout item_view = (LinearLayout) view;
                                final RadioButton itemcheck = (RadioButton)
                                        item_view.findViewById(R.id.rbuttonc);



                                if (itemcheck.isChecked()) {
                                    itemcheck.setChecked(true);

                                } else {
                                    itemcheck.setChecked(true);
                                }

                                itemcheck.setChecked(true);

                            }

                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }

}
