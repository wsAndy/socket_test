package com.example.sheng.myapplication;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

//import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    Button btn_send;
    TextView txt_show;
    EditText edt_show;


    String str_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        str_show = "not connect";

        edt_show = (EditText) findViewById(R.id.edt_input);

        txt_show = (TextView)findViewById(R.id.txt_show);

        btn_send = (Button)findViewById(R.id.btn_send);

//        btn_send.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v)
//            {
//                txt_show_jud_bool = !txt_show_jud_bool;
////                if(txt_show_jud_bool == false) {
////                    Toast.makeText(MainActivity.this, " false!", Toast.LENGTH_SHORT).show();
////                }else{
////                    Toast.makeText(MainActivity.this, " true!", Toast.LENGTH_SHORT).show();
////                }
//                fun_btn_send_start();
//            }
//        });

        btn_send.setOnClickListener(new button_clicker());

        txt_show.setText(str_show);

    }

    class button_clicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if(v == btn_send)
            {
//                System.out.println("---------------button push------------");

                new Thread()
                {
                    @Override
                    public void run()
                    {
                        try{
//                            System.out.println("---------------before accept ------------");
                            acceptServer();
//                            System.out.println("---------------after server------------");
                        }catch(Exception e)
                        {
                            //Toast.makeText(MainActivity.this," error "+e.getMessage(),Toast.LENGTH_SHORT).show();
                            System.out.println("error: "+e.getMessage());
                        }
                    }
                }.start();
            }
        }

    }

    private void acceptServer() throws IOException
    {
        // create client socket
        System.out.println("socket");
        Socket socket = new Socket("192.168.1.105",54321);
        System.out.println("socket2");

        String str_num = edt_show.getText().toString();
        if(str_num.isEmpty())
        {
            str_show = "";

        }else {
            int int_number = Integer.valueOf(str_num);

            System.out.println("get int:"+int_number);

            PrintStream ps = new PrintStream(socket.getOutputStream());

            ps.println(int_number);

            Scanner sc = new Scanner(socket.getInputStream());
            str_show = sc.nextLine();

            MainActivity.this.runOnUiThread(updateUI);
        }


        socket.close();
    }

    Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            if(str_show == "")
            {
                ;
            }else {

                txt_show.setText(str_show);
            }

        }
    };



}
