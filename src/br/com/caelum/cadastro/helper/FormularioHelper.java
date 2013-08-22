package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

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
	
	private FormularioActivity activity;

	public FormularioHelper(FormularioActivity activity) {
		nome = (EditText) activity.findViewById(R.id.nome);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		site = (EditText) activity.findViewById(R.id.site);
		nota = (SeekBar) activity.findViewById(R.id.nota);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		botaoImagem = (ImageView) activity.findViewById(R.id.foto);
		confirmar = (Button) activity.findViewById(R.id.confirmar);
		this.activity = activity;
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
		carregaImagem(aluno.getFoto());
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
		Bitmap fotoBitmap;
		if (localArquivoFoto != null ) {
			fotoBitmap = BitmapFactory.decodeFile(localArquivoFoto);
		} else {
			fotoBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
		}
		fotoBitmap = Bitmap.createScaledBitmap(fotoBitmap, 350, 350, true);
		botaoImagem.setImageBitmap(fotoBitmap);
	}
	
}
