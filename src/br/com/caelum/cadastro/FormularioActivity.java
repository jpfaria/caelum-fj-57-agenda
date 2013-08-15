package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;

public class FormularioActivity extends Activity {

	private Button botaoConfirmar;
	private FormularioHelper helper; 
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		this.helper = new FormularioHelper(this);
		this.botaoConfirmar = (Button) findViewById(R.id.confirmar);
		this.botaoConfirmar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {

				AlunoDAO alunoDAO = new AlunoDAO(FormularioActivity.this);
				alunoDAO.insere(helper.pegaAlunoDoFormulario());
				alunoDAO.close();
				
				Intent intent = new Intent(FormularioActivity.this, ListaAlunosActivity.class);
				startActivity(intent);

			}

		});
		
	}

}
