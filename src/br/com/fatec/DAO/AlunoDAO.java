/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.bean.Aluno;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author Viotti
 */
public class AlunoDAO implements DAO <Aluno> {

    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Aluno aluno;
    
    @Override
    public boolean insere(Aluno obj) throws SQLException {
        String sql = "INSERT INTO Aluno (codAluno, nome, turma) "
                + "VALUES (?, ?, ?)"; //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.conectar();
        //prepara o comando pst
        pst = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Aluno com o comando INSERT
        pst.setInt(1, obj.getCodAluno());
        pst.setString(2, obj.getNome());
        pst.setString(3, obj.getTurma());
        //executa o comando
        int res = pst.executeUpdate();
        //fecha o banco
        Banco.desconectar();
        return res != 0;
    }

    @Override
    public boolean remove(Aluno obj) throws SQLException {
        String sql = "DELETE FROM Aluno WHERE codAluno = ? "; //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.conectar();
        //prepara o comando pst
        pst = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Aluno com o comando DELETE
        pst.setInt(1, obj.getCodAluno());
        //executa o comando
        int res = pst.executeUpdate();
        //fecha o banco
        Banco.desconectar();
        return res != 0;
    }

    @Override
    public boolean altera(Aluno obj) throws SQLException {
        String sql = "UPDATE Aluno SET nome = ? WHERE codAluno = ? "; 
        //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.conectar();
        //prepara o comando pst
        pst = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Aluno com o comando update
        pst.setString(3, obj.getTurma());
        pst.setString(2, obj.getNome());
        pst.setInt(1, obj.getCodAluno());
        //executa o comando
        int res = pst.executeUpdate();
        //fecha o banco
        Banco.desconectar();
        return res != 0;
    }

    @Override
    public Aluno buscaID(Aluno obj) throws SQLException {
        String sql = "SELECT * FROM Aluno "
                + "WHERE codAluno = ?"; //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.conectar();
        //prepara o comando pst
        pst = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Aluno com o comando SELECT
        pst.setInt(1, obj.getCodAluno());
        //executa o comando
        rs = pst.executeQuery();
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //instancia o objeto para retorno dos dados
            aluno = new Aluno();
            //jogar os dados do banco para o objeto
            aluno.setCodAluno(rs.getInt("codAluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setTurma(rs.getString("turma"));
        } 
        else {
            //não encontrou nada!!!
            aluno = null;
        }
        //fecha o banco
        Banco.desconectar();
        //devolve o objeto com os dados do banco ou não
        return aluno;
    }

    @Override
    public Collection<Aluno> lista(String criterio) throws SQLException {
        //cria a coleção para os dados
        Collection<Aluno> retorno = new ArrayList<>();
        
        String sql = "SELECT * FROM Aluno ";
        
        //verifica se tem filtro a fazer
        if(criterio.length() > 0) {
            sql += "WHERE " + criterio;
        }
    
        //abre a conexao como banco
        Banco.conectar();
        //prepara o comando pst
        pst = Banco.obterConexao().prepareStatement(sql);
        //executa o comando
        rs = pst.executeQuery();
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        //enquanto existir registros na tabela, vai lendo até o fim
        while(rs.next()) {
            //instancia o objeto para retorno dos dados
            aluno = new Aluno();
            //jogar os dados do banco para o objeto
            aluno.setCodAluno(rs.getInt("codAluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setTurma(rs.getString("turma"));
            
            //adiciona o registro dentro da Coleção
            retorno.add(aluno);
        } 
        //fecha o banco
        Banco.desconectar();
        //devolve a lista com todos os registros
        return retorno;
    }
}
