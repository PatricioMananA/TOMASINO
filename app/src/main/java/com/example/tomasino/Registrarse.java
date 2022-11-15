package com.example.tomasino;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registrarse extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ConstraintLayout Layout ;
    ImageView iv1;
    private Button btNotificacion;
    private PendingIntent pendingIntent;
    private final static  String CHANNEL_ID = "NOTIFIACION";
    private final static int NOTIFICACION_ID=0;

    String [] country = {"seleccione sede","Santiago centro",
            "San Joaquin",
            "Arica",
            "Iquique",
            "Antofagasta",
            "Copiapo",
            "la serana",
            "Ovalle",
            "Viña del mar",
            "Rancagua",
            "Curico",
            "Talca",
            "Chillan",
            "concepcion",
            "Los angeles", "Temuco",
            "Valdivia",
            "Osorno",
            "Puerto Montt",
            "Punta Arenas",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);


        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        Layout = findViewById(R.id.Layout);
        iv1 = findViewById(R.id.iv1);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        // notficacion

        btNotificacion = findViewById(R.id.btn_snack);
        btNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotificationChannel();
                createNotification();
                snack();

            }
            private void createNotificationChannel() {
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                    CharSequence name ="Notificacion Tomasino";
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name, NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

            }

            private void createNotification() {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
                builder.setSmallIcon(R.drawable.notificacion);
                builder.setContentTitle("Notificacion Tomasino");
                builder.setContentText("Se creo tu usuario");
                builder.setColor(Color.GREEN);
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                 builder.setVibrate(new long []{1000,1000,1000,1000,1000});
                 builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());

            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void snack (){
        Snackbar.make(Layout,"Datos guardados correctamente", Snackbar.LENGTH_LONG).setAction("Cerrar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v3) {

                    }
                })
                .setActionTextColor(getResources().getColor(R.color.purple_200)).show();

    }


    final int CAPTURA_IMAGEN=1;

    public void tomarFoto (View v){

        Intent i =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,CAPTURA_IMAGEN);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CAPTURA_IMAGEN && resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap bitmap1 = (Bitmap)extras.get("data");
            iv1.setImageBitmap(bitmap1);

            try {
                FileOutputStream fos = openFileOutput(crearNombreArchivoJPG(), Context.MODE_PRIVATE);
                bitmap1.compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.close();

            }catch (Exception e){

            }

        }
    }

    private String crearNombreArchivoJPG() {
        String fecha= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return fecha+".jpg";
    }



public  void VerTodasLasFotos (View v){
        Intent intent = new Intent(this, actividad2.class);
        startActivity(intent);

}
























}