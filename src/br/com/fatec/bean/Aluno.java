/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.bean;

import java.util.Objects;

/**
 *
 * @author usuario1
 */
public class Aluno {
    
    private int codAluno;
    private String nome;
    private String turma;

    public Aluno() {
    }

    public Aluno(int codAluno, String nome, String turma) {
        this.codAluno = codAluno;
        this.nome = nome;
        this.turma = turma;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
    
    @Override
    public String toString() {
        return getNome();
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.codAluno;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.turma);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (this.codAluno != other.codAluno) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.turma, other.turma)) {
            return false;
        }
        return true;
    }
    
    
}
