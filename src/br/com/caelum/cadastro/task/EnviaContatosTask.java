package br.com.caelum.cadastro.task;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;

public class EnviaContatosTask extends AsyncTask<Object, Object, String> {

	private final String endereco = "http://www.caelum.com.br/mobile";
	
	private final Context context;
	
	private ProgressDialog progress;
	
	public EnviaContatosTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde ...", "Envio de dados para a web", true, true);
	}
	
	@Override
	protected String doInBackground(Object... arg0) {
		
		AlunoDAO dao = new AlunoDAO(context);
		List<Aluno> alunos = dao.lista();
		dao.close();

		String json = new AlunoConverter().toJson(alunos);
		// Toast.makeText(this, json, Toast.LENGTH_LONG).show();

		WebClient client = new WebClient(endereco);
		
		try {
			return client.post(json);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(context, result,
				Toast.LENGTH_LONG).show();
		progress.dismiss();
	}

}
