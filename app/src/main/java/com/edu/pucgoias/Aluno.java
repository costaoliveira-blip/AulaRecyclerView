package com.edu.pucgoias;

import java.util.Objects;

public class Aluno {
    private String nome;
    private String matricula;
    private String status;

    public Aluno(String nome, String matricula, String status) {
        this.nome = nome;
        this.matricula = matricula;
        this.status = status;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getStatus() { return status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(nome, aluno.nome) &&
                Objects.equals(matricula, aluno.matricula) &&
                Objects.equals(status, aluno.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, matricula, status);
    }
}