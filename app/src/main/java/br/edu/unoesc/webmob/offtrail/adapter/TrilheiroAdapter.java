package br.edu.unoesc.webmob.offtrail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.sql.SQLException;
import java.text.Collator;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.unoesc.webmob.offtrail.helper.DatabaseHelper;
import br.edu.unoesc.webmob.offtrail.model.Trilheiro;
import br.edu.unoesc.webmob.offtrail.ui.TrilheiroItemView;
import br.edu.unoesc.webmob.offtrail.ui.TrilheiroItemView_;

@EBean
public class TrilheiroAdapter
        extends BaseAdapter {
    List<Trilheiro> trilheiros;

    @Bean
    DatabaseHelper dh;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        try {
            trilheiros = dh.getTrilheiroDao().queryForAll();
            // ordenar trilheiros de forma descendente (médoto compareTo no model Trilheiro)
            Collections.sort(trilheiros);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // atualização do listview com os trilheiros cadastrados
    public void attTrilheiros() {
        try {
            trilheiros = dh.getTrilheiroDao().queryForAll();
            // ordenar trilheiros de forma descendente (médoto compareTo no model Trilheiro)
            Collections.sort(trilheiros);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return trilheiros.size();
    }

    @Override
    public Trilheiro getItem(int position) {
        return trilheiros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TrilheiroItemView trilheiroItemView;
        if (convertView == null) {
            trilheiroItemView = TrilheiroItemView_.build(context);
        } else {
            trilheiroItemView = (TrilheiroItemView) convertView;
        }

        trilheiroItemView.bind(getItem(position));

        return trilheiroItemView;
    }
}