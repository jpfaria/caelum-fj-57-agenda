package br.com.caelum.cadastro.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class DeviceInfoTask extends AsyncTask<Object, Object, String> {

	private final Context context;
	
	private ProgressDialog progress;
	
	public DeviceInfoTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde ...", "Coletando informações do dispositivo", true, true);
	}

	@Override
	protected String doInBackground(Object... params) {
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(context, result,
				Toast.LENGTH_LONG).show();
		progress.dismiss();
	}
	
}
