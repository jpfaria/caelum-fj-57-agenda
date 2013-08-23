package br.com.caelum.cadastro;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.model.Aluno;

public class FormularioActivity extends Activity {

	private Button botaoConfirmar;
	private ImageView botaoImagem;
	private FormularioHelper helper; 
	private Aluno aluno;
	private String localArquivoFoto;
	private static final int TIRA_FOTO = 123;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		criaFormulario(); 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == TIRA_FOTO) {
			if (resultCode == Activity.RESULT_OK) {
				helper.carregaImagem(this.localArquivoFoto);
			} else {
				this.localArquivoFoto = null;
			}
		}
	}

	private void defineTipoDeFormulario() {
		aluno = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_SELECTED);
		if (aluno == null) { // tipo insere
			aluno = new Aluno();
		} else { // tipo altera
			botaoConfirmar.setText("Alterar");
			helper.colocaNoFormulario(aluno);
		}
	}

	private void criaFormulario() {
		
		helper = new FormularioHelper(this);
		
		botaoConfirmar = helper.getBotaoConfirmar();
		botaoConfirmar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				AlunoDAO alunoDAO = new AlunoDAO(FormularioActivity.this);
				alunoDAO.salva(helper.pegaAlunoDoFormulario());
				alunoDAO.close();
				finish();
				
				/* nao precisa.. o metodo finish mata o form e mostra anterior
				Intent intent = new Intent(FormularioActivity.this, ListaAlunosActivity.class);
				startActivity(intent);
				*/
			}

		});
		
		botaoImagem = helper.getBotaoImagem();
		botaoImagem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {

				localArquivoFoto = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";
				Uri localFoto = Uri.fromFile( new File(localArquivoFoto));
				
				Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
				
				startActivityForResult(irParaCamera, TIRA_FOTO);

			}

		});
		
		defineTipoDeFormulario();
		
	}
	
	

}
