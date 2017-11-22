package com.example.rchowdam.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

//    private final String NAMESPACE = "http://project1/";
//    private final String URL = "http://10.0.2.2:7101/RCApplication1-Project1-context-root/Rc_Class1Port?WSDL";
//    private final String SOAP_ACTION = "http://project1/getIpAddress";
//    private final String METHOD_NAME = "getIpAddress";
//    private final String PARAMETER_NAME = "arg0";

//    private final String NAMESPACE = "http://project2/";
//    private final String URL = "http://10.0.2.2:7101/RCApplication1-Project2-context-root/DemoServicePort?WSDL";
//    private final String SOAP_ACTION = "http://project2/square";
//    private final String METHOD_NAME = "square";
//    private final String PARAMETER_NAME = "arg0";

    private final String NAMESPACE = "http://project3/";
    private final String URL = "http://10.0.2.2:7101/RCApplication1-Project3-context-root/Laptop_batteryPort?WSDL";
    private final String SOAP_ACTION = "http://project3/getBatteryStatus";
    private final String METHOD_NAME = "getBatteryStatus";
//    private final String PARAMETER_NAME = "arg0";


    EditText inp_url_et;
    Button ok_button_b;
    TextView result_inp_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inp_url_et = (EditText) findViewById(R.id.inp_url);
        ok_button_b = (Button) findViewById(R.id.ok_button);
        result_inp_tv = (TextView) findViewById(R.id.result_view);


        ok_button_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new CallWebService().execute(inp_url_et.getText().toString());

            }

        });

    }

    class CallWebService extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            result_inp_tv.setText("Laptop Battery Status:-----" + "\n\n" + s);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";

            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);

//            PropertyInfo propertyInfo = new PropertyInfo();
//            propertyInfo.setName(PARAMETER_NAME);
//            propertyInfo.setValue(params[0]);
//
//
////            propertyInfo.setType(String.class);
//
//            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            httpTransportSE.debug=true;

            try {
                httpTransportSE.call(SOAP_ACTION, envelope);

                System.out.println(httpTransportSE.requestDump + "0000--=0000" + httpTransportSE.responseDump);
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
//                System.out.println(soapPrimitive);
                result = soapPrimitive.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }

}
