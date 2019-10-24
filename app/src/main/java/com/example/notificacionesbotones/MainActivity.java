package com.example.notificacionesbotones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNELAVISOS_ID = "CHANNEL_AVISOS_ID";
    public static final String CHANNELMENSAJES_ID = "CHANNEL_MENSAJES_ID";

    Button btnNotificacionNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotificacionNormal = findViewById(R.id.notificacionNormal);

        btnNotificacionNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearNotificacion();
            }
        });
    }

    private NotificationChannel crearCanal(String idCanal, String nombreCanal, String descripcion, int importancia)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel canal = new NotificationChannel(idCanal,nombreCanal,importancia);
            canal.setDescription(descripcion);
            return canal;

        }
        return null;
    }

    private void crearCanalesNotificacion()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            NotificationChannel canal = crearCanal(CHANNELAVISOS_ID,"Avisos","Avisos importantes",NotificationManager.IMPORTANCE_HIGH);
            canal.setVibrationPattern(new long[] {400,600,100,300,100});
            //Registrando el canal en el sistema luego no se podra hacer cambios.

            notificationManager.createNotificationChannel(canal);

        }
    }

   private void CrearNotificacion()
   {
       NotificationCompat.Builder notificacion;
       notificacion = new  NotificationCompat.Builder(getApplicationContext(),CHANNELMENSAJES_ID);
       notificacion.setContentText("texto de la notificacion");
       notificacion.setSmallIcon(R.mipmap.ic_launcher);
       notificacion.setLargeIcon(((BitmapDrawable) ContextCompat.getDrawable(this,R.mipmap.ic_launcher)).getBitmap());
       notificacion.setTicker("Prueba de Ticker");
       notificacion.setWhen(System.currentTimeMillis());
       notificacion.setAutoCancel(false);

       NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       mNotificationManager.notify(0,notificacion.build());


   }
}
