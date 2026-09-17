package com.edu.pucgoias;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.pucgoias.databinding.LayoutListaBinding;

public class AlunoAdapter extends ListAdapter<Aluno, AlunoAdapter.AlunoViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Aluno aluno, int position);
    }

    public AlunoAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<Aluno> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Aluno>() {

                @Override
                public boolean areItemsTheSame(
                        @NonNull Aluno oldItem,
                        @NonNull Aluno newItem) {

                    return oldItem.getMatricula()
                            .equals(newItem.getMatricula());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Aluno oldItem,
                        @NonNull Aluno newItem) {

                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        LayoutListaBinding binding = LayoutListaBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new AlunoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(
            @NonNull AlunoViewHolder holder,
            int position) {

        Aluno aluno = getItem(position);

        holder.bind(aluno, listener);
    }

    public static class AlunoViewHolder
            extends RecyclerView.ViewHolder {

        private final LayoutListaBinding binding;

        public AlunoViewHolder(
                @NonNull LayoutListaBinding binding) {

            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(
                Aluno aluno,
                OnItemClickListener listener) {

            binding.texto1.setText(aluno.getNome());

            binding.texto2.setText(
                    "Matrícula: " + aluno.getMatricula()
            );

            binding.texto3.setText(
                    aluno.getStatus()
            );

            itemView.setOnClickListener(v -> {

                if (listener != null) {

                    int position = getBindingAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(aluno, position);
                    }
                }
            });
        }
    }
}