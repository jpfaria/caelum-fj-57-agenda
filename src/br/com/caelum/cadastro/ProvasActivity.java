package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import br.com.caelum.cadastro.model.Prova;

public class ProvasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.provas);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.replace(R.id.provas_lista_frame,
				new ListaProvasFragment(),
				ListaProvasFragment.class.getCanonicalName());
		
		if (isTablet()) {

			transaction.replace(R.id.provas_detalhe_frame,
					new DetalhesProvaFragment(),
					DetalhesProvaFragment.class.getCanonicalName());

		}

		transaction.commit();

	}

	public boolean isTablet() {
		return getResources().getBoolean(R.bool.isTablet);
	}
	
	public void selecionaProvas(Prova prova) {
		
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", prova);
		
		DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
		detalhesProva.setArguments(argumentos);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		int frame;
		
		if (!isTablet()) {
			transaction.addToBackStack(null);
			frame = R.id.provas_lista_frame;
		} else {
			frame = R.id.provas_detalhe_frame;
		}
		
		transaction.replace(frame, detalhesProva, DetalhesProvaFragment.class.getCanonicalName());
		
			
		transaction.commit();
		
	}

}
