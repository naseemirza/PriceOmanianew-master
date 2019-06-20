package thinkbiz.solutions.tbs.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PriceChartActivity extends AppCompatActivity {

    ArrayList<Entry> x;
    ArrayList<String> y;
    private LineChart mChart;
    public String TAG = "PriceChartActivity";
    MarkerView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_chart);

        getSupportActionBar().setTitle("Monthly Price Chart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawChart();

        x = new ArrayList<Entry>();
        y = new ArrayList<String>();
        mChart = (LineChart)findViewById(R.id.chart1);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setMarkerView(mv);
        XAxis xl = mChart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(true);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);


    }

    private void drawChart() {

        String tag_string_req = "req_chart";

        StringRequest strReq = new StringRequest(Request.Method.POST, "https://ae.priceomania.com/mobileappwebservices/price_graph?pid=2072",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "Response: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                           // String id = jsonObject.getString("id");
                            JSONArray jsonArray = jsonObject.getJSONArray("price_graph");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                int value = jsonObject.getInt("price");
                                String date = jsonObject.getString("last_update");
                                x.add(new Entry(value, i));
                                y.add(date);

                            }
                            LineDataSet set1 = new LineDataSet(x, "NAV Data Value");
                            set1.setLineWidth(1.5f);
                            set1.setCircleRadius(4f);
                            LineData data = new LineData(y, set1);
                            mChart.setData(data);
                            mChart.invalidate();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });
        strReq.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError arg0) throws VolleyError {
            }

            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }
        });
        strReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
