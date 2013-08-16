package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends Activity {

	private Button botaoConfirmar;
	private FormularioHelper helper; 
	private Aluno aluno;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		criaFormulario();
	}

	private void defineTipoDeFormulario() {
		aluno = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_SELECIONADO);
		if (aluno == null) { // tipo insere
			aluno = new Aluno();
		} else { // tipo altera
			botaoConfirmar.setText("Alterar");
			helper.colocaNoFormulario(aluno);
		}
	}

	private void criaFormulario() {
		
		helper = new FormularioHelper(this);
		
		botaoConfirmar = (Button) findViewById(R.id.confirmar);
		botaoConfirmar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {

				AlunoDAO alunoDAO = new AlunoDAO(FormularioActivity.this);
				alunoDAO.salva(helper.pegaAlunoDoFormulario());
				alunoDAO.close();
				finish();
				
				Intent intent = new Intent(FormularioActivity.this, ListaAlunosActivity.class);
				startActivity(intent);

			}

		});
		
		defineTipoDeFormulario();
		
	}
	
	

}
