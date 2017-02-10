package com.example.nikitran.wk2_fragmentcommunication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class F1 extends Fragment {

    EditText et1;
    Button b1;

    public F1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f1, container, false);
        et1 = (EditText) v.findViewById(R.id.eT1);
        b1 = (Button) v.findViewById(R.id.b1);

        // 1. When button 1 is clicked

        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendMyText();
            }
        });

        return v;
    }

    private void sendMyText()
    {
        //sendNoInterface();
        sendWithInterface();

    }

    // main activity is hard coded, using the method is not recommended
    private void sendNoInterface()
    {
        MainActivity ma = (MainActivity) getActivity();
        ma.sendto2(et1.getText().toString());
    }

    // using an interface
    // 2. finds that Activity where the fragment is at and call the sendto method from
    private void sendWithInterface()
    {
        F1Interface myA = (F1Interface) getActivity();
        myA.iSendTo2(et1.getText().toString());
    }

    public void setMyEditText(String s)
    {
        et1.setText(s);
    }

    //implement an interface in F1
    public interface F1Interface {
        void iSendTo2(String s);
    }
}
