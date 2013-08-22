package br.com.caelum.cadastro;

import com.crashlytics.android.Crashlytics;
import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.listener.MyPhoneStateListener;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.task.EnviaContatosTask;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;
	private Aluno alunoSelecionado;
	
	private TelephonyManager tel;
	private MyPhoneStateListener myListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Crashlytics.start(this);
		setContentView(R.layout.listagem_alunos);
		criaLista();
		carregaLista();
		registraListener();

	}

	private void registraListener() {
		myListener = new MyPhoneStateListener();
		tel = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
		tel.listen(myListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_principal_novo:
	
				Intent intent = new Intent(this, FormularioActivity.class);
				startActivity(intent);
	
				return false;
	
			case R.id.menu_principal_enviar_alunos:
				
				new EnviaContatosTask(this).execute();
	
				return false;
	
			case R.id.menu_principal_info:
				
				Toast.makeText(ListaAlunosActivity.this,
						 "Sinal: " + myListener.getSignalStrength(), Toast.LENGTH_LONG)
						 .show();
	
				return false;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);

		MenuItem ligar = menu.add("Ligar");
		MenuItem sms = menu.add("Enviar SMS");
		MenuItem achar = menu.add("Achar Mapa");
		MenuItem navegar = menu.add("Navegar no site");
		MenuItem deletar = menu.add("Deletar");
		MenuItem email = menu.add("Enviar e-mail");

		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				new AlertDialog.Builder(ListaAlunosActivity.this)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle("Deletar")
						.setMessage("Deseja mesmo deletar?")
						.setPositiveButton("Quero",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										if (alunoSelecionado.getFoto() != null) {
											File file = new File(
													alunoSelecionado.getFoto());
											file.delete();
										}

										AlunoDAO dao = new AlunoDAO(
												ListaAlunosActivity.this);
										dao.deletar(alunoSelecionado);
										dao.close();

										carregaLista();

									}
								}).setNegativeButton("Não", null).show();

				return false;
			}

		});

		Intent intentLigar = new Intent(Intent.ACTION_CALL);
		intentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
		ligar.setIntent(intentLigar);

		Intent intentSMS = new Intent(Intent.ACTION_VIEW);
		intentSMS.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
		intentSMS.putExtra("sms_body", "oi");
		sms.setIntent(intentSMS);

		Intent intentAcharMapa = new Intent(Intent.ACTION_VIEW);
		intentAcharMapa.setData(Uri.parse("geo:0,0?z=14&q="
				+ alunoSelecionado.getEndereco()));
		achar.setIntent(intentAcharMapa);

		Intent intentNavegar = new Intent(Intent.ACTION_VIEW);
		intentNavegar.setData(Uri.parse("http:" + alunoSelecionado.getSite()));
		navegar.setIntent(intentNavegar);

		Intent intentEmail = new Intent(Intent.ACTION_SEND);
		intentEmail.setType("message/rfc822");
		intentEmail.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "caelum@caelum.com.br" });
		intentEmail.putExtra(Intent.EXTRA_SUBJECT,
				"Elogios do curso de android");
		intentEmail.putExtra(Intent.EXTRA_TEXT, "Este curso é ótimo");
		email.setIntent(intentEmail);

	}

	private void criaLista() {
		this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

		// this.listaAlunos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		this.listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				Intent edicao = new Intent(ListaAlunosActivity.this,
						FormularioActivity.class);
				edicao.putExtra(Extras.ALUNO_SELECTED,
						(Aluno) adapter.getItemAtPosition(position));

				/*
				 * Toast.makeText(ListaAlunosActivity.this,
				 * "Posição selecionada: " + posicao, Toast.LENGTH_LONG)
				 * .show();
				 */

				startActivity(edicao);

			}

		});

		this.listaAlunos
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
							View view, int position, long id) {

						alunoSelecionado = (Aluno) adapter
								.getItemAtPosition(position);

						return false;
					}

				});

		registerForContextMenu(this.listaAlunos);
	}

	private void carregaLista() {
		AlunoDAO alunoDAO = new AlunoDAO(this);

		/*
		 * ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
		 * android.R.layout.simple_list_item_1, alunoDAO.lista());
		 */

		ListaAlunosAdapter adapter = new ListaAlunosAdapter(this,
				alunoDAO.lista());

		this.listaAlunos.setAdapter(adapter);
		// this.listaAlunos.setChoiceMode(ListView.);

	}

}
