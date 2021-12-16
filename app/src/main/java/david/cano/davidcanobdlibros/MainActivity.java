package david.cano.davidcanobdlibros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edcodigo;
    EditText edtitulo;
    EditText edautor;
    CrearBD crearBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearBD = new CrearBD(this);
        edcodigo=findViewById(R.id.etCodigo);
        edtitulo=findViewById(R.id.etTitulo);
        edautor=findViewById(R.id.etAutor);
        /*bd.execSQL("INSERT INTO libros VALUES(1,'La Celestina', 'Fernando de Rojas');");
        bd.execSQL("INSERT INTO libros VALUES(2,'El Quijote', 'Miguel de Cervantes');");
        bd.execSQL("INSERT INTO libros VALUES(3,'El Lazarillo de Tormes','Anonimo');");
        bd.execSQL("INSERT INTO libros VALUES(4,'El Principe de la Niebla', 'Carlos Ruiz Zafón');");
        bd.close();*/

    }


    public void ConsultarLibro(View v) {
        String cod = edcodigo.getText().toString();
        SQLiteDatabase bd;
        bd = crearBD.getReadableDatabase();
        Cursor contenido = bd.rawQuery("select * from libros where codigo='" + cod + "';", null);
        if (contenido.moveToNext()) {
            edtitulo.setText(contenido.getString(1));
            edautor.setText(contenido.getString(2));
        }
        contenido.close();
        bd.close();
    }

    public void insertarLibro(View v) {
        SQLiteDatabase bd;
        bd = crearBD.getWritableDatabase();

        if (edcodigo.getText().toString().equals("") || edtitulo.getText().toString().equals("") ||
                edautor.getText().toString().equals("")) {
            verMensajeToast("Cajas vacías, debes introducir los datos");
        } else {
            String cod = edcodigo.getText().toString();
            String tit = edtitulo.getText().toString();
            String aut = edautor.getText().toString();
            try {
                bd.execSQL("INSERT INTO libros VALUES('" + cod + "','" + tit + "','" + aut + "');");
                verMensajeToast("Datos insertados");
            } catch (Exception sqlex) {
                verMensajeToast(sqlex.getMessage());
            }
        }
        bd.close();
        limpiarCajas();
    }

    public void verMensajeToast(String mensaje) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

    public void limpiarCajas() {
        edcodigo.setText("");
        edtitulo.setText("");
        edautor.setText("");
    }

    public void borrarLibro(View v) {
        SQLiteDatabase bd;

        bd = crearBD.getWritableDatabase();
        if (edcodigo.getText().toString().equals("") || edtitulo.getText().toString().equals("") ||
                edautor.getText().toString().equals("")) {
            verMensajeToast("Cajas vacías, debes introducir los datos");
        } else {
            String cod = edcodigo.getText().toString();
            String tit = edtitulo.getText().toString();
            String aut = edautor.getText().toString();
            try {
                bd.execSQL("DELETE INTO LIBROS VALUES(" + cod + ",'" + tit + ",'" + aut + "'); ");
                verMensajeToast("Datos borrados");
            } catch (Exception sqlex) {
                verMensajeToast(sqlex.getMessage());
            }
            limpiarCajas();
            crearBD.close();
        }
    }
}

