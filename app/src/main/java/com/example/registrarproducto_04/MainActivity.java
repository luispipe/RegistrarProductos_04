package com.example.registrarproducto_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,prize,quantity;
    Button register,end;
    TextView totalValue;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editTextProducto);
        prize=findViewById(R.id.editTextPrecio);
        quantity=findViewById(R.id.editTextCantidad);

        register=findViewById(R.id.buttonRegistrar);
        end= findViewById(R.id.buttonFinalizar);

        totalValue=findViewById(R.id.tvTotalValue);

        table=findViewById(R.id.tableProducts);

        ArrayList<Producto> factura=new ArrayList<>();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= name.getText().toString();
                String precio= prize.getText().toString();
                String cantidad=quantity.getText().toString();

                if(nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                "Todos los campos deben diligenciarse", Toast.LENGTH_LONG).show();
                }else{
                   int precioNew= Integer.parseInt(precio);
                   int cantidadNew=Integer.parseInt(cantidad);
                   Producto nuevo= new Producto(nombre,precioNew,cantidadNew);
                   factura.add(nuevo);
                   llenarTabla(nuevo);
                   calcularTotal(factura);
                   limpiar();
                }



            }
        });



    }

    public void llenarTabla(Producto producto){
        int valor= producto.getCantidad()*producto.getPrecio();

        TableRow fila= new TableRow(this);

        TextView celda1= new TextView(this);
        celda1.setText(producto.getNombre());
        celda1.setWidth(110);
        celda1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(celda1);

        TextView celda2= new TextView(this);
        celda2.setText(producto.getCantidad()+"");
        celda2.setWidth(85);
        celda2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(celda2);

        TextView celda3=new TextView(this);
        celda3.setText(producto.getPrecio()+"");
        celda3.setWidth(80);
        celda3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(celda3);

        TextView celda4=new TextView(this);
        celda4.setText(valor+"");
        celda4.setWidth(85);
        celda4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(celda4);

        table.addView(fila);

    }

    public void limpiar(){
        name.setText("");
        prize.setText("");
        quantity.setText("");
    }

    public void calcularTotal(ArrayList<Producto> compra){
        int valor=0;
        for (Producto i: compra){
            valor+= i.getPrecio()*i.getCantidad();
        }
        totalValue.setText(valor+"");
    }

}