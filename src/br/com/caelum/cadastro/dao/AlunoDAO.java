package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static String database = "CadastroCaelum";
	private static Integer version = 1;
	private static final String TABLE = "ALUNO";
	private static final String[] COLUMNS = { "id", "nome", "telefone",
			"endereco", "site", "nota", "foto" };

	public AlunoDAO(Context context) {
		super(context, database, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE + " (id INTEGER PRIMARY KEY, "
				+ "nome TEXT UNIQUE NOT NULL, telefone TEXT, "
				+ "endereco TEXT, site TEXT, nota REAL, "
				+ "foto TEXT);";

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS" + TABLE +";";
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
		db.insert(TABLE, null, values);
	}
	
	public List<Aluno> getLista() {
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.query(TABLE, COLUMNS, null, null, null, null, null);
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		while (cursor.moveToNext()) 
		{
			Aluno aluno = new Aluno();
			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setTelefone(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setSite(cursor.getString(4));
			aluno.setNota(cursor.getDouble(5));
			aluno.setFoto(cursor.getString(6));
			
			alunos.add(aluno);
		    
		}
		
		return alunos;
		
	}

}
