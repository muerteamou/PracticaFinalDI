package com.example.practicafinaldi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private LayoutInflater aInflater;
    private List<String> listaItems;

    public Adaptador(Context context, List<String> listaItems) {
        this.listaItems = listaItems;
        this.aInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = aInflater.inflate(R.layout.rv_add_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        String aux = listaItems.get(position);

    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView producto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.itNombre);
        }

        public String getproducto() {
            return producto.getText().toString();
        }

        public void setproducto(String producto) {
            this.producto.setText(producto);
        }
    }
}
