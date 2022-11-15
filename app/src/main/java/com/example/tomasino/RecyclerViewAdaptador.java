package com.example.tomasino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter <RecyclerViewAdaptador.ViewHolder>{

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nombre;
        private final TextView apellido;
        ImageView imagen;

        public ViewHolder (View itemView){
            super(itemView);
            nombre= itemView.findViewById(R.id.tvPersona);
            apellido= itemView.findViewById(R.id.tv_apellido);
            imagen= itemView.findViewById(R.id.img_persona);


        }
    }

    public List<PersonaModelo> personaLista;

    public RecyclerViewAdaptador(List<PersonaModelo> personaLista){
        this.personaLista=personaLista;
    }


    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tem_persona,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nombre.setText(personaLista.get(position).getNombre());
        holder.apellido.setText(personaLista.get(position).getApellido());
        holder.imagen.setImageResource(personaLista.get(position).getImagen());

    }

    @Override
    public int getItemCount (){
        return personaLista.size();
    }

















}
