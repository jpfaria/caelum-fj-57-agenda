package br.com.caelum.cadastro.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.ProvasActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Prova;

public class ListaProvasFragment extends Fragment {

	private ListView listViewProvas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.lista_provas, container,
				false);

		listViewProvas = (ListView) layoutProvas
				.findViewById(R.id.lista_provas);

		Prova p1 = new Prova("20/03/2012", "Matemática");
		p1.setTopicos(Arrays.asList("Alebra Linear", "Integral", "Diferencial"));

		Prova p2 = new Prova("20/03/2012", "Matemática");
		p2.setTopicos(Arrays.asList("Complemento nominal",
				"Oracoes Subordinadas"));

		List<Prova> provas = Arrays.asList(p1, p2);

		listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),
				android.R.layout.simple_list_item_1, provas));

		listViewProvas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Prova selecionada = (Prova) adapter.getItemAtPosition(position);

				/*
				Toast.makeText(getActivity(),
						"Prova selecionada: " + selecionada, Toast.LENGTH_LONG)
						.show(); */
				
				ProvasActivity calendarioProvas = (ProvasActivity) getActivity();
				calendarioProvas.selecionaProvas(selecionada);

			}
		});

		return layoutProvas;
	}

}
