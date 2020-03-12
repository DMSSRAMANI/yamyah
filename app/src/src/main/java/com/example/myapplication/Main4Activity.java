package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.logging.Level.INFO;

public class Main4Activity extends AppCompatActivity {

    Map<String, Object> getData ;
    ArrayList<String>  mStringList= new ArrayList<String>();
    String[] mStringArray;
    String[] item = {"a" ,"b" ,"c","d"};
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<Map<String, String>> planetsList = new ArrayList<Map<String,String>>();

    private void initList() {
        // We populate the planets
        planetsList.add(createPlanet("planet", "Mercury"));
        planetsList.add(createPlanet("planet", "Venus"));
        planetsList.add(createPlanet("planet", "Mars"));
        planetsList.add(createPlanet("planet", "Jupiter"));
        planetsList.add(createPlanet("planet", "Saturn"));
        planetsList.add(createPlanet("planet", "Uranus"));
        planetsList.add(createPlanet("planet", "Neptune"));
    }

    private HashMap<String, String> createPlanet(String key, String name) {
        HashMap<String, String> planet = new HashMap<String, String>();
        planet.put(key, name);
        return planet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ListView listView = (ListView) findViewById(R.id.list_view);
        // This is a simple adapter that accepts as parameter
        // Context
        // Data list
        // The row layout that is used during the row creation
        // The keys used to retrieve the data
        // The View id used to show the data. The key number and the view id must match
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(String.valueOf(INFO + "--activity_main4--"), document.getId() + " => " + document.getData());
                                Log.d(String.valueOf(INFO + "--activity_main4--"), String.valueOf(mStringList.size()));
                                String pdname = String.valueOf(document.getData().get("pname"));
                                Log.d(String.valueOf(INFO + "--activity_main4--"), pdname);
                                mStringList.add(pdname);
                                getData = document.getData();
                            }
                        } else {
                            Log.d(String.valueOf(INFO), "failed access the products DB");
                        }
                        Log.d(String.valueOf(INFO+"activity_main4>>>>>>>>>"), "Total size  --> " + mStringList.size());

                    }
                });

        mStringArray = new String[mStringList.size()];
        Log.d(String.valueOf(INFO+"activity_main4>>>>>>>>>"), "Total size  --> " + mStringArray.length);
        mStringArray = mStringList.toArray(mStringArray);

        for(int i = 0; i < mStringArray.length ; i++){
            Log.d("string is",(String)mStringArray[i]);
        }
        Log.d(String.valueOf(INFO+"activity_main4>>>>>>>>>"), "Total size  --> " + mStringList.size());
        Log.d(String.valueOf(INFO+"activity_main4>>>>>>>>>"), "Total size  --> " + mStringArray.length);
        Toast.makeText(getApplicationContext(),"Product Iteration done",Toast.LENGTH_SHORT).show();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_main4,mStringList);
        listView.setAdapter(adapter);
    }
}
