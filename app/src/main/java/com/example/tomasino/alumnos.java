package com.example.tomasino;


import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;
        import java.util.List;

public class alumnos extends AppCompatActivity {

    private RecyclerView recyclerViewPersona;
    private  RecyclerViewAdaptador adaptadorPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        recyclerViewPersona= findViewById(R.id.recyclerPersonas);
        recyclerViewPersona.setLayoutManager(new LinearLayoutManager(this));

        adaptadorPersona = new RecyclerViewAdaptador(obtenerPersonas());
        recyclerViewPersona.setAdapter(adaptadorPersona);
    }


    public List<PersonaModelo> obtenerPersonas(){
        List <PersonaModelo> persona = new ArrayList<>();
        persona.add(new PersonaModelo("patricio","gonzalez",R.drawable.hombre1));
        persona.add(new PersonaModelo("andres","perez",R.drawable.hombre2));
        persona.add(new PersonaModelo("juan","soto",R.drawable.hombre3));
        persona.add(new PersonaModelo("sandra","lolo",R.drawable.mujer1));
        persona.add(new PersonaModelo("nicol","soto",R.drawable.mujer2));
        persona.add(new PersonaModelo("camila","ruiz",R.drawable.mujer3));
        return persona;
    }
}