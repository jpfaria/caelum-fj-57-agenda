package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Prova;

public class DetalhesProvaFragment extends Fragment {

	private Prova prova;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.provas_detalhe, container,
				false);

		if (getArguments() != null) {
			this.prova = (Prova) getArguments().getSerializable("prova");
		}

		if (this.prova != null) {
			TextView materia = (TextView) layout
					.findViewById(R.id.detalhe_prova_materia);
			TextView data = (TextView) layout
					.findViewById(R.id.detalhe_prova_data);
			ListView topicos = (ListView) layout
					.findViewById(R.id.detalhe_prova_topicos);

			materia.setText(this.prova.getMateria());
			data.setText(this.prova.getData());

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(),
					android.R.layout.simple_expandable_list_item_1,
					prova.getTopicos());

			topicos.setAdapter(adapter);

		}

		return layout;
	}
}