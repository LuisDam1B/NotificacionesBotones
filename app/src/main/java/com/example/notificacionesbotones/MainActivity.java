package com.example.notificacionesbotones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final String CHANNELAVISOS_ID = "com.example.notificacionesbotones.notificacionNormal";
    final String CHANNELMENSAJES_ID = "CHANNEL_MENSAJES_ID";

    Button btnNotificacionNormal;
    Button btnNotificacionBigText;
    Button btnNotificacionBigPicture;
    Button btnNotificacionInbox;
    Button btnNotificacionBotones;


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
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            NotificationChannel canal = crearCanal(CHANNELAVISOS_ID,"Avisos","Aviso en notificacion normal",NotificationManager.IMPORTANCE_HIGH);

            canal.setVibrationPattern(new long[] {400,600,100,300,100});
            //Registrando el canal en el sistema luego no se podra hacer cambios.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(canal);

        }
    }

   private NotificationCompat.Builder CrearNotificacion()
   {
       NotificationCompat.Builder notificacion;
       notificacion = new  NotificationCompat.Builder(this,CHANNELAVISOS_ID);

       notificacion.setSmallIcon(R.mipmap.ic_launcher);
       //Esto siguinte no vale, hay que investigarlo
       //notificacion.setLargeIcon(((BitmapDrawable) ContextCompat.getDrawable(this,R.mipmap.ic_launcher)).getBitmap());
       notificacion.setTicker("Prueba de Ticker");
       notificacion.setWhen(System.currentTimeMillis());
       //notificacion.setAutoCancel(false);

       NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       notificationManager.notify(0,notificacion.build());

    return notificacion;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearCanalesNotificacion();

        btnNotificacionNormal = findViewById(R.id.notificacionNormal);

        btnNotificacionNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder  notificacion = CrearNotificacion();
                notificacion.setContentText("Notificación normal");
            }
        });

        btnNotificacionBigText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearNotificacion();
            }
        });
    }
}
