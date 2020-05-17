package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText texteditor;
    TextView header2;
    EditText filename;
    Button save;
    Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        texteditor = (EditText) findViewById(R.id.editor);
        filename = (EditText) findViewById(R.id.filename);
        header2 = (TextView) findViewById(R.id.header2);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
    }


    public void saveFile(View v){
        // tiedostoon kirjoitus
        String text = texteditor.getText().toString();
        String fName = filename.getText().toString();

        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(fName, context.MODE_PRIVATE));
            writer.write(text);
            writer.close();

        } catch (IOException e){
            Log.e("IOException:", "Check your input." );

        } finally {
            System.out.println("File saved.");
            // Näytä käyttäjälle
        }
    }

    public void openFile(View v){
        // tiedoston luku
        String fName = filename.getText().toString();

        try {
            InputStream reader = context.openFileInput(fName);
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));
            String input = "";
            while ((input = br.readLine()) != null){
                texteditor.setText(input);
            }
            reader.close();

        } catch (IOException e) {
            Log.e("IOException:", "Error when reading a file.");

        } finally {
            System.out.println("File read.");
        }

    }
}
