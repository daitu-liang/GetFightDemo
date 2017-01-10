package com.daitu_liang.study.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.daitu_liang.study.mytest.datepicker.SignDialogActivity;
import com.daitu_liang.study.mytest.http.RetroftActivity;
import com.daitu_liang.study.mytest.svg.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityOriginal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_origin);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivityOriginal.this, AnimationActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Button btnPoperty = (Button) findViewById(R.id.btn1);
        btnPoperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityOriginal.this, FloatTestActivity.class));
            }
        });
        Button btnPoperty2 = (Button) findViewById(R.id.btn2);
        btnPoperty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityOriginal.this, SnowActivity.class));
            }
        });
        Button btnPoperty3 = (Button) findViewById(R.id.btn3);
        btnPoperty3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivityOriginal.this, IndraftActivity.class));
            }
        });

        Button btnPoperty4 = (Button) findViewById(R.id.btn4);
        btnPoperty4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivityOriginal.this, MainTwoActivity.class));
            }
        });
        Button btnPoperty5 = (Button) findViewById(R.id.btn5);
        btnPoperty5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivityOriginal.this, BottomActivity.class));
            }
        });
        Button btnPoperty6 = (Button) findViewById(R.id.btn6);
        btnPoperty6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivityOriginal.this, SignDialogActivity.class));
            }
        });
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn7,R.id.btn8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivityOriginal.this, AnimationActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivityOriginal.this, MainTwoActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivityOriginal.this, PaintActivity.class));
                break;
            case R.id.btn4:
                break;
            case R.id.btn7:
                startActivity(new Intent(MainActivityOriginal.this, RetroftActivity.class));
                break;
            case R.id.btn8:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(MainActivityOriginal.this, MainActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainActivityOriginal.this, PaintActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivityOriginal.this, PorterDuffXfermodeActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivityOriginal.this, MatrixActivity.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivityOriginal.this, WaveDemoActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
