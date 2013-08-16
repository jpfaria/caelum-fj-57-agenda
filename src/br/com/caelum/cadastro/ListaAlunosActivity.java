package br.com.caelum.cadastro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;
	private Aluno alunoSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listagem_alunos);
		criaLista();
		carregaLista();

	}

	private void criaLista() {
		this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
		
		// this.listaAlunos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		this.listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
				edicao.putExtra(Extras.ALUNO_SELECIONADO, (Aluno) adapter
						.getItemAtPosition(position));
				
				/*
				Toast.makeText(ListaAlunosActivity.this,
						"Posição selecionada: " + posicao, Toast.LENGTH_LONG)
						.show(); */
				
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

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {

		menu.add("Ligar");
		menu.add("Enviar SMS");
		menu.add("Achar Mapa");
		menu.add("Navegar no site");
		MenuItem deletar = menu.add("Deletar");
		menu.add("Enviar e-mail");

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

	}

	private void carregaLista() {
		AlunoDAO alunoDAO = new AlunoDAO(this);

		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
				android.R.layout.simple_list_item_1, alunoDAO.getLista());

		this.listaAlunos.setAdapter(adapter);
		// this.listaAlunos.setChoiceMode(ListView.);

	}

}
