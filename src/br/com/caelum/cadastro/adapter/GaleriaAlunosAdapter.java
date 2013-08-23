package br.com.caelum.cadastro.adapter;

import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import br.com.caelum.cadastro.model.Aluno;

public class GaleriaAlunosAdapter extends PagerAdapter{

	private List<Aluno> alunos;
	private Object activity;

	public GaleriaAlunosAdapter(List<Aluno> alunos, Activity context) {
		this.alunos = alunos;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
