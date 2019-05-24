package thinkbiz.solutions.tbs.com.MoreOptionsPkg;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import thinkbiz.solutions.tbs.com.R;

public class OptionsActivity extends AppCompatActivity {

    List<OpModel> productList;
    RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_options);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            productList = new ArrayList<>();

            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));
            productList.add(new OpModel("Apple iPhone 6 plus","Souq","AED","2072",R.drawable.souq));

            OpAdapter adapter = new OpAdapter(this, productList);

            recyclerView.setAdapter(adapter);
        }
}
