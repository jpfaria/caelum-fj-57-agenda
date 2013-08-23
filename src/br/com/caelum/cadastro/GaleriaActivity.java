package br.com.caelum.cadastro;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import br.com.caelum.cadastro.adapter.GaleriaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.model.Aluno;

public class GaleriaActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.galeria);

		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.lista();
		dao.close();
		
		ViewPager gallery = (ViewPager) findViewById(R.id.gallery);
		GaleriaAlunosAdapter adapter = new GaleriaAlunosAdapter(alunos, this);
		gallery.setAdapter(adapter);

	}

	

}
