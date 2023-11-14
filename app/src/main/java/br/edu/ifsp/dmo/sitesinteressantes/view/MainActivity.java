package br.edu.ifsp.dmo.sitesinteressantes.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.ifsp.dmo.sitesinteressantes.R;
import br.edu.ifsp.dmo.sitesinteressantes.model.Site;
import br.edu.ifsp.dmo.sitesinteressantes.model.dao.SiteDao;
import br.edu.ifsp.dmo.sitesinteressantes.view.adapter.SiteAdapter;
import br.edu.ifsp.dmo.sitesinteressantes.view.adapter.SiteClickListener;

public class MainActivity extends AppCompatActivity implements SiteClickListener {

    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private List<Site> siteList;
    private SiteDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new SiteDao(this);

        button = findViewById(R.id.button_add_site);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SiteDetailsActivity.class);
            startActivity(intent);
        });
        recyclerView = findViewById(R.id.recyclerview_sites);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_tag){
            Intent intent = new Intent(this, EditTagActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void configList(){
        siteList = dao.recuperateAll();
        SiteAdapter adapter = new SiteAdapter(siteList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void clickEditSite(int position) {
        Toast.makeText(this, "entrou no edit " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickDeleteSite(int position) {
        siteList = dao.recuperateAll();
        Site site = siteList.get(position);
        dao.delete(site);
        siteList.remove(position);
        siteList = dao.recuperateAll();
        configList();

        Toast.makeText(this, "entrou no delete " + position, Toast.LENGTH_SHORT).show();

    }
}