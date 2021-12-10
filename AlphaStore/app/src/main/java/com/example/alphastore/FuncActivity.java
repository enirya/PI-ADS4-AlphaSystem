package com.example.alphastore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.alphastore.dao.CategoriaDAO;
import com.example.alphastore.dao.ProdutoDAO;
import com.example.alphastore.fragments.CategoriasFragment;
import com.example.alphastore.fragments.PedidosFragment;
import com.example.alphastore.fragments.ProdutosFragment;
import com.example.alphastore.model.Categoria;
import com.example.alphastore.model.Produto;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FuncActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public CategoriaDAO categoriaDao;
    public List<Categoria> listaCategoria;
    public ProdutoDAO produtoDAO;
    public List<Produto> listaProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoriaDao = new CategoriaDAO(this);
        listaCategoria = categoriaDao.lista();

        produtoDAO = new ProdutoDAO(this);
        listaProduto = produtoDAO.lista();

        setContentView(R.layout.activity_func);

        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PedidosFragment(), "Pedidos");
        adapter.addFragment(new ProdutosFragment(), "Produtos");
        adapter.addFragment(new CategoriasFragment(), "Categorias");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}