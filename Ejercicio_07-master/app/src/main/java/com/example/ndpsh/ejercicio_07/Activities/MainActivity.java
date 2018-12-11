package com.example.ndpsh.ejercicio_07.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.example.ndpsh.ejercicio_07.Adapters.ViewPagerAdapter;
import com.example.ndpsh.ejercicio_07.Fragments.PersonListFragment;
import com.example.ndpsh.ejercicio_07.Interfaces.OnPersonCreated;
import com.example.ndpsh.ejercicio_07.Models.Person;
import com.example.ndpsh.ejercicio_07.R;


/**
 * Created by Naim on 28/11/18
 */

public class MainActivity extends AppCompatActivity implements OnPersonCreated {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    // Indice de position de los fragments
    public static final int PERSON_FORM_FRAGMENT = 0;
    public static final int PERSON_LIST_FRAGMENT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);

    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setTabLayout(){
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_title_first)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_title_second)));
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerTabLayout(final ViewPager viewPager) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int postion = tab.getPosition();
                viewPager.setCurrentItem(postion);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void createPerson(Person person) {
        // Obtenemos el fragment deseado, ListFragment, a traves de
        // getSupportFragmentManager(), y posteriormente pasamos el indice de posicion
        // de dicho fragment
        PersonListFragment fragment = (PersonListFragment) getSupportFragmentManager().getFragments().get(PERSON_LIST_FRAGMENT);
        // Llamamos al metodo de nuestro fragment
        fragment.addPerson(person);
        // Movemos el viewpager hacia el ListFragment para ver la persona agregamos en el listView;
        viewPager.setCurrentItem(PERSON_LIST_FRAGMENT);


    }
}
