package com.example.ndpsh.ejercicio_07.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ndpsh.ejercicio_07.Interfaces.OnPersonCreated;
import com.example.ndpsh.ejercicio_07.Models.Country;
import com.example.ndpsh.ejercicio_07.Models.Person;
import com.example.ndpsh.ejercicio_07.R;
import com.example.ndpsh.ejercicio_07.Utils.Util;

import java.util.List;


public class PersonFormFragment extends Fragment {

    private EditText editTextName;
    private Spinner spinnerCountry;
    private Button btnCreate;

    private List<Country> countries;

    private OnPersonCreated onPersonCreated;


    public PersonFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_form, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        spinnerCountry = view.findViewById(R.id.spinnerCountry);
        btnCreate = view.findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewPerson();
            }
        });

        // Obtenemos todos los paises
        countries = Util.getCountries(getContext());

        // Creamos un ArrayAdapter para ser pasado a nuestro Spinner/DropDown/lista despeglabe
        // Con un layout integrado en Android para su uso directo llamado
        // simple_spinner_dropdown_item
        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(getContext(),android.R.layout.simple_spinner_dropdown_item, countries);
        spinnerCountry.setAdapter(adapter);


        return view;
    }

    private void createNewPerson() {
        if (!editTextName.getText().toString().isEmpty()) {
            // Recogemos el pais seleccionado de la siguiente manera
            Country country = (Country) spinnerCountry.getSelectedItem();
            Person person = new Person(editTextName.getText().toString(), country);
            //Usamos la interfaz para comunicarnos con el MainActivity y este con el otro Fragment
            onPersonCreated.createPerson(person);

        }
    }
    // Eventos para enlazar el listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPersonCreated) {
            onPersonCreated = (OnPersonCreated) context;
        }else {
            throw new RuntimeException(context.toString() + "must implement OnPersonCreated");
        }
    }

    // Eventos para deselazar el listener


    @Override
    public void onDetach() {
        super.onDetach();
        onPersonCreated = null;
    }
}
