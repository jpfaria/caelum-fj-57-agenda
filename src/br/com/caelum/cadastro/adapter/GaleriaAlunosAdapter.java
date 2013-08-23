package br.com.caelum.cadastro.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

public class GaleriaAlunosAdapter extends PagerAdapter{

	private List<Aluno> alunos;
	private Activity activity;

	public GaleriaAlunosAdapter(List<Aluno> alunos, Activity context) {
		this.alunos = alunos;
		this.activity = context;
	}
	
	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view.equals(obj);
	}
	
	@Override
	public void destroyItem(ViewGroup pager, int position, Object object) {
		((ViewPager) pager).removeView((View) object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup pager, int position) {
		ImageView foto = new ImageView(activity);
		Aluno aluno = alunos.get(position);
		
		if (aluno.getFoto() != null) {
			
			Bitmap imagem = BitmapFactory.decodeFile(aluno.getFoto());
			foto.setImageBitmap(Bitmap.createScaledBitmap(imagem, 200, 200, true));
			
		} else {
			foto.setImageResource(R.drawable.ic_no_image);
		}
		
		((ViewPager) pager).addView(foto, 0);
		
		return foto;
	}

}
