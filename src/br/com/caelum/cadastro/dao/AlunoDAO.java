package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static String database = "CadastroCaelum";
	private static Integer version = 1;

	public AlunoDAO(Context context) {
		super(context, database, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE ALUNO (" +
				"id INTEGER PRIMARY KEY, " +
				"nome TEXT UNIQUE NOT NULL, " +
				"telefone TEXT, " +
				"endereco TEXT, " +
				"site TEXT, " +
				"nota REAL, " +
				"foto TEXT);";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS ALUNO;";
		db.execSQL(sql);
	}
	
	public void insere(Aluno aluno) {
		
		ContentValues values = new ContentValues();
		values.put("foto", aluno.getFoto());
		values.put("nome", aluno.getNome());
		values.put("site", aluno.getSite());
		values.put("site", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("nota", aluno.getNota());
		
		SQLiteDatabase db = getWritableDatabase();
		db.insert("ALUNO", null, values);
		
	}

}
