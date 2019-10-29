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
       notificacion.setAutoCancel(false);



    return notificacion;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearCanalesNotificacion();

        btnNotificacionNormal = findViewById(R.id.notificacionNormal);
        btnNotificacionBigText = findViewById(R.id.notificacionBigText);
        btnNotificacionBigPicture = findViewById(R.id.notificacionBigPicture);
        btnNotificacionInbox = findViewById(R.id.notificacionInbox);
        btnNotificacionBotones = findViewById(R.id.notificacionBotones);

        btnNotificacionNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder  notificacion = CrearNotificacion();
                notificacion.setContentText("Esta es una notificación normal!!...   ");
                notificacion.setContentTitle("Notificación normal");
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,notificacion.build());
            }
        });

        btnNotificacionBigText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder  notificacion = CrearNotificacion();
                NotificationCompat.BigTextStyle n = new NotificationCompat.BigTextStyle(notificacion)
                        .bigText("Al contrario del pensamiento popular, el texto de Lorem Ipsum no es simplemente texto aleatorio. Tiene sus raices en una pieza cl´sica de la literatura del Latin, que data del año 45 antes de Cristo, haciendo que este adquiera mas de 2000 años de antiguedad. Richard McClintock")
                        .setBigContentTitle("Titulo expandido BigText")
                        .setSummaryText("Summary text");

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,notificacion.build());


            }
        });


        btnNotificacionBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder  notificacion = CrearNotificacion();
                NotificationCompat.BigPictureStyle nBigText = new NotificationCompat.BigPictureStyle(notificacion)
                        //.bigPicture(((BitmapDrawable) ContextCompat.getDrawable(this,R.mipmap.ic_launcher)).getBitmap())
                        //.bigLargeIcon(((BitmapDrawable) ContextCompat.getDrawable(this,R.mipmap.ic_launcher)).getBitmap())
                        .setBigContentTitle("Titulo de BigPictureStyle")
                        .setSummaryText("Summary Text");


                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,notificacion.build());
            }

        });

        btnNotificacionInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] lines = {"Mensaje1","Mensaje2","Mensaje3","Mensaje4","Mensaje5"};
                NotificationCompat.Builder  notificacion = CrearNotificacion();
                NotificationCompat.InboxStyle nIS = new NotificationCompat.InboxStyle(notificacion)
                        .setBigContentTitle("Expanded Inbox Title")
                        .setSummaryText("Summary Text");

                for (String line : lines)
                {
                    nIS.addLine(line);
                }

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(3,nIS.build());

            }
        });

        btnNotificacionBotones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder  notificacion = CrearNotificacion();
                NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background,
                        "Delete",pendingIntent.ge)
            }
        });
    }


}
