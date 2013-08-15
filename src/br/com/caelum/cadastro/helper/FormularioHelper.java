package br.com.caelum.cadastro.helper;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

  private EditText nome;
	private EditText telefone;
	private EditText site;
	private SeekBar nota;
	private EditText endereco;
	private ImageView botaoImagem;
	
	private Aluno aluno;

	public FormularioHelper(FormularioActivity activity) {
		nome = (EditText) activity.findViewById(R.id.nome);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		site = (EditText) activity.findViewById(R.id.site);
		nota = (SeekBar) activity.findViewById(R.id.nota);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		botaoImagem = (ImageView) activity.findViewById(R.id.foto);
		aluno = new Aluno();
	}

	public Aluno pegaAlunoDoFormulario() {
		aluno.setNome(nome.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setNota(Double.valueOf(nota.getProgress()));
		return aluno;
	}
	
}
