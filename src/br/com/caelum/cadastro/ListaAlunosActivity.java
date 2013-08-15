package br.com.caelum.cadastro;

import br.com.caelum.cadastro.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends Activity {

	private String[] alunos = { "Anderson", "Filipe", "Ghilherme" };
	private ListView listaAlunos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, alunos);

		this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
		this.listaAlunos.setAdapter(adapter);
		this.listaAlunos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		this.listaAlunos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Toast.makeText(ListaAlunosActivity.this,
						"Posição selecionada: " + posicao, Toast.LENGTH_LONG)
						.show();

			}

		});

		this.listaAlunos
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
							View view, int posicao, long id) {
						Toast.makeText(ListaAlunosActivity.this,
								"Nome selecionado: " + alunos[posicao],
								Toast.LENGTH_LONG).show();
						return true; // cancela onItemClick
					}

				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
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

}
