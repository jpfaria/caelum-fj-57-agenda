package br.com.caelum.cadastro;

import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ProvasActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.provas);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		transaction.replace(R.id.provas_view, new ListaProvasFragment(), "listaProvas");
		
		transaction.commit();
		
	}
	
}
