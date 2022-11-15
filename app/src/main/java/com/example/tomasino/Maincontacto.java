package com.example.tomasino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Maincontacto extends AppCompatActivity {
    private EditText ed_nombre,ed_apellido,ed_edad;
    private Button b_agregar,b_ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincontacto);

        ed_nombre = findViewById(R.id.et_nombre);
        ed_apellido = findViewById(R.id.et_apellido);
        ed_edad = findViewById(R.id.et_edad);

        b_agregar = findViewById(R.id.btn_agregar);
        b_ver = findViewById(R.id.btn_ver);

        b_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), Leer.class);
                startActivity(i);
            }
        });
        b_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    public void insertar()
    {
        try
        {
            String nombre = ed_nombre.getText().toString();
            String correo = ed_apellido.getText().toString();
            String mensaje = ed_edad.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS contacto (id INTEGER PRIMARY KEY AUTOINCREMENT,nombre VARCHAR,correo VARCHAR,mensaje VARCHAR)");

            String sql = "insert into contacto(nombre,correo,mensaje)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,correo);
            statement.bindString(3,mensaje);
            statement.execute();
            Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            ed_nombre.setText("");
            ed_apellido.setText("");
            ed_edad.setText("");
            ed_nombre.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}