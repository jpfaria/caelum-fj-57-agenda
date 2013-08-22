package br.com.caelum.cadastro.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter{

	private final List<Aluno> alunos;
	private final Activity activity;
	
	
	public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
		this.alunos = alunos;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return alunos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		
		Aluno aluno = alunos.get(position);
		
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(aluno.getNome());
		
		ImageView foto = (ImageView) view.findViewById(R.id.foto);
		
		Bitmap fotoBitmap;
		
		if (aluno.getFoto() != null) {
			fotoBitmap = BitmapFactory.decodeFile(aluno.getFoto());
		} else {
			fotoBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
		}
		
		fotoBitmap = Bitmap.createScaledBitmap(fotoBitmap, 160, 160, true);
		
		foto.setImageBitmap(fotoBitmap);
		
		
		if (position % 2 == 0) {
			view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
		}
		
		return view;
	}

}
