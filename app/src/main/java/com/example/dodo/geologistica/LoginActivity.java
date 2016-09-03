package com.example.dodo.geologistica;import android.content.Intent;import android.os.StrictMode;import android.support.v7.app.ActionBar;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.view.View;import android.view.animation.Interpolator;import android.widget.CheckBox;import android.widget.EditText;import android.widget.TextView;import org.ksoap2.SoapEnvelope;import org.ksoap2.serialization.PropertyInfo;import org.ksoap2.serialization.SoapObject;import org.ksoap2.serialization.SoapPrimitive;import org.ksoap2.serialization.SoapSerializationEnvelope;import org.ksoap2.transport.HttpTransportSE;public class LoginActivity extends AppCompatActivity {    private static final String SOAP_ACTION = "http://tempuri.org/loginTech";    private static final String METHOD_NAME = "loginTech";    private boolean achou = false;    CheckBox checkBox;    Usuario usuario;    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);        StrictMode.setThreadPolicy(policy);        CONSTS.setData();        //ActionBar bar = getSupportActionBar();        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));        //android.support.v7.app.ActionBar bar = getSupportActionBar();        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));    }    public void logar(View view)    {        try {            checkBox = (CheckBox) findViewById(R.id.checkBoxContinuarConectado);            SoapObject request = new SoapObject(CONSTS.NAMESPACE, METHOD_NAME);            EditText editTextUsuario = (EditText) findViewById(R.id.input_email);            //request.addProperty("user", editTextUsuario.getText().toString());            String param1 = editTextUsuario.getText().toString();            PropertyInfo p1 = new PropertyInfo();            p1.setName("user");            p1.setValue(param1);            p1.setType(String.class);            request.addProperty(p1);            EditText editTextSenha = (EditText) findViewById(R.id.input_password);            //request.addProperty("password", editTextSenha.getText().toString());            String param2 = editTextSenha.getText().toString();            PropertyInfo p2 = new PropertyInfo();            p2.setName("password");            p2.setValue(param2);            p2.setType(String.class);            request.addProperty(p2);            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);            envelope.dotNet=true;            envelope.setOutputSoapObject(request);            HttpTransportSE androidHttpTransport = new HttpTransportSE(CONSTS.URL);            androidHttpTransport.call(SOAP_ACTION, envelope);            //Object result2 = (SoapPrimitive)envelope.getResponse();            SoapObject obj1 = (SoapObject) envelope.getResponse();            for(int i=0; i < obj1.getPropertyCount(); i++)            {                CONSTS.tecnico = new Tecnico();                CONSTS.tecnico.setIdtecnico(Integer.parseInt(obj1.getProperty(0).toString()));                CONSTS.tecnico.setNomeTecnico(obj1.getProperty(1).toString());                CONSTS.tecnico.setArea(Integer.parseInt(obj1.getProperty(2).toString()));                achou = true;                break;                //os = new Os();                //SoapObject obj2 =(SoapObject) obj1.getProperty(i);                //os.setIdOs(Integer.parseInt(obj2.getProperty(0).toString()));                //os.setData(obj2.getProperty(3).toString());                //os.setData(os.getData().substring(0,10));                //os.setHora(obj2.getProperty(4).toString());                //if(os.getHora().length() <2)                //    os.setHora("0" + os.getHora() + ":00");                //else                //   os.setHora(os.getHora() + ":00");                //os.setEndereco(obj2.getProperty(6).toString()+", " + obj2.getProperty(7).toString());                //CONSTS.listOs.add(os);            }            UsuarioDao usuarioDao = new UsuarioDao(this);            usuario = new Usuario();            if(achou) {                //if(checkBox.isChecked()){                //    usuario.setLogin(editTextUsuario.getText().toString());                //    usuario.setNome(CONSTS.tecnico.getNomeTecnico());                //    usuario.setSenha(editTextSenha.getText().toString());                //    long resultSave = usuarioDao.saveUsuario(usuario);               // }                Intent intent = new Intent(this, ListaOsActivity.class);                startActivity(intent);                setContentView(R.layout.activity_lista_os);            }            else{                TextView textView = (TextView)findViewById(R.id.aviso);                textView.setText("Erro ao logar");            }        }        catch (Exception e) {            //TextView textView = (TextView)findViewById(R.id.aviso);            //textView.setText(e.getMessage().toString());        }    }}