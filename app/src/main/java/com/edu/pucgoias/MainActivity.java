package com.edu.pucgoias;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import com.edu.pucgoias.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlunoAdapter alunoAdapter;
    private final ArrayList<Aluno> alunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
        loadAlunos();

        binding.btnAdicionar.setOnClickListener(v -> adicionarAluno());
    }

    private void setupRecyclerView() {
        alunoAdapter = new AlunoAdapter((aluno, position) ->
                Toast.makeText(getApplicationContext(),
                        "Clicou em: " + aluno.getNome(),
                        Toast.LENGTH_SHORT).show()
        );
        binding.recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewAlunos.setAdapter(alunoAdapter);
    }

    private void loadAlunos() {
        alunos.add(new Aluno("Alice Silva", "12345", "Ativo"));
        alunos.add(new Aluno("Bob Santos", "67890", "Ativo"));
        alunos.add(new Aluno("Carlos Oliveira", "13579", "Ativo"));
        alunoAdapter.submitList(new ArrayList<>(alunos));
    }

    private void adicionarAluno() {
        String nome = binding.editNome.getText().toString().trim();
        String matricula = binding.editMatricula.getText().toString().trim();
        String status = binding.editStatus.getText().toString().trim();

        if (nome.isEmpty() || matricula.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        alunos.add(new Aluno(nome, matricula, status));
        alunoAdapter.submitList(new ArrayList<>(alunos));

        binding.editNome.setText("");
        binding.editMatricula.setText("");
        binding.editStatus.setText("");

        Toast.makeText(this, "Aluno adicionado!", Toast.LENGTH_SHORT).show();
    }
}
