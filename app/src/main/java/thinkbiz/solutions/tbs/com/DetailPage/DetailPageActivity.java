package thinkbiz.solutions.tbs.com.DetailPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import thinkbiz.solutions.tbs.com.AllUrls;
import thinkbiz.solutions.tbs.com.R;

public class DetailPageActivity extends AppCompatActivity {

    WebView mywebview;
    ProgressDialog progressDialog;
    String slug,slug_suffix;
    String Actname ;
    TextView textname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
//                String actname="Profile";
//                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = pref.edit();
//
//                edit.putString("Actvname",actname);
//                edit.apply();
//                Intent intent=new Intent(AccDetailsActivity.this, ProfileActivity.class);
//                startActivity(intent);
            }
        });

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Actname=pref.getString("name","");
        slug=pref.getString("slug","");
        slug_suffix=pref.getString("slug_suffix","");
        textname=(TextView)findViewById(R.id.textname);
        textname.setText(Actname);

        mywebview = (WebView) findViewById(R.id.webView1);
        mywebview.setWebViewClient(new MyWebViewClient());
        String url= AllUrls.DETAIL_PAGE+slug+slug_suffix;
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebview.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //view.loadUrl(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog = new ProgressDialog(DetailPageActivity.this);
            progressDialog.setMessage("Please wait ...");
            progressDialog.setProgressStyle(90);
            progressDialog.show();
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }
}
