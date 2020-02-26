package com.example.android6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  public static final String EXTRA = "clickIndex";
  ListView presidentList;
  List<President> presidents;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    presidentList = findViewById(R.id.presidentList);
    presidents = GlobalModel.getInstance().getPresidents();
  }

  protected void onStart(){
    super.onStart();
    presidentList.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            presidents)
    );

    presidentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        Intent nextActivity = new Intent(MainActivity.this, ItemDetails.class);
        nextActivity.putExtra(EXTRA, i);
        startActivity(nextActivity);
      }
    });
  }

  public void addNewPresident(View caller){
    presidents.add(new President("","","",""));
    Intent nextActivity = new Intent(MainActivity.this, ItemDetails.class);
    nextActivity.putExtra(EXTRA, presidents.size()-1);
    startActivity(nextActivity);
  }
}