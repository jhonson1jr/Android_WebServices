package com.example.lanternaverde.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.lanternaverde.retrofit.views.atualizarUsuario;

import butterknife.BindView;
import butterknife.ButterKnife;
import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by Lanterna Verde on 19/08/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<jsonConsultaUsuarios> results; //jsonConsultaUsuarios manipula o json vindo do servidor

    public RecyclerViewAdapter(Context context, List<jsonConsultaUsuarios> results) {
        this.context = context;
        this.results = results;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //populando a view com os dados atribuidos
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_users, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { //atribui os valores na view
        jsonConsultaUsuarios result = results.get(position);
        holder.textoID.setText(""+result.getId()); //concatenando com aspas pra forçar a conversão pra string
        holder.textoNomeUsuario.setText(""+result.getUsuario());
        holder.textoSenha.setText(""+result.getSenha());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //instanciando os obj que vao exibir os valores do JSON vindo do servidor

        @BindView(R.id.textoID) TextView textoID;
        @BindView(R.id.textoNomeUsuario) TextView textoNomeUsuario;
        @BindView(R.id.textoSenha) TextView textoSenha;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int id = Integer.parseInt(textoID.getText().toString());
            String nome = textoNomeUsuario.getText().toString();
            String senha = textoSenha.getText().toString();

            Intent i = new Intent(context, atualizarUsuario.class);
            i.putExtra("id",id);
            i.putExtra("nome",nome);
            i.putExtra("senha", senha);
            context.startActivity(i);
        }
    }
}
