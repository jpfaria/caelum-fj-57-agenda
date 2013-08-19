package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
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
	private Button confirmar;

	private Aluno aluno;
	private String localArquivoFoto;

	public FormularioHelper(FormularioActivity activity) {
		nome = (EditText) activity.findViewById(R.id.nome);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		site = (EditText) activity.findViewById(R.id.site);
		nota = (SeekBar) activity.findViewById(R.id.nota);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		botaoImagem = (ImageView) activity.findViewById(R.id.foto);
		confirmar = (Button) activity.findViewById(R.id.confirmar);
		aluno = new Aluno();
	}

	public Aluno pegaAlunoDoFormulario() {
		aluno.setFoto(localArquivoFoto);
		aluno.setNome(nome.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setNota(Double.valueOf(nota.getProgress()));
		return aluno;
	}
	
	public void colocaNoFormulario(Aluno aluno) {
		nome.setText(aluno.getNome());
		telefone.setText(aluno.getTelefone());
		site.setText(aluno.getSite());
		nota.setProgress(Double.valueOf(aluno.getNota()).intValue());
		endereco.setText(aluno.getEndereco());
		if (aluno.getFoto() != null) {
			carregaImagem(aluno.getFoto());
		}
		this.aluno = aluno;
	}
	
	public ImageView getBotaoImagem() {
		return this.botaoImagem;
	}
	
	public Button getBotaoConfirmar() {
		return this.confirmar;
	}

	public void carregaImagem(String localArquivoFoto) {
		this.localArquivoFoto = localArquivoFoto;
		Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
		Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
		botaoImagem.setImageBitmap(imagemFotoReduzida);
		
	}
	
}
