package com.example.oraltest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.oraltest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    static int RecordNumForWhole=0;
    static String RecordData = "Private.txt";
    static String store = "Private.xml";

    public static void RecW(int N){
        RecordNumForWhole= N;
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;//xml so the corresponding generated class is ActivityMa
    // inBinding . This class holds all the bindings from the layout properties (for example, the
    // user variable) to the layout's views and knows how to assign values for the binding expres
    // sions

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());//获得根节点并设置为活动视图
        setSupportActionBar(binding.toolbar);//设置应用栏控件
        //初始化一些全局控件
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}