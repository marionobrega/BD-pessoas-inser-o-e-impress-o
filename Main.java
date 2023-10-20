package br.edu.idp.disciplinas.poo2023_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {


        String paramsConexao = "jdbc:h2:mem:testdb";
        String usuario = "";
        String senha = "";
        Connection conexao = DriverManager.getConnection(paramsConexao, usuario, senha);

        String criacaoSql = "create table pessoa (id int primary key, nome varchar(150), qtdAcesso int, naturalidade varchar(75) )";
        Statement stmtC = conexao.createStatement();
        stmtC.executeUpdate(criacaoSql);
        stmtC.close();

        String insercaoSql = "insert into pessoa (id, nome, qtdAcesso, naturalidade) values "
                + "(1, 'João', 10, 'São Paulo'), "
                + "(2, 'Maria', 15, 'Rio de Janeiro'), "
                + "(3, 'Carlos', 5, 'Belo Horizonte')";
        Statement stmtI = conexao.createStatement();
        stmtI.executeUpdate(insercaoSql);
        stmtI.close();

        String consulta = "select * from pessoa";
        Statement stmt = conexao.createStatement();
        ResultSet resultado = stmt.executeQuery(consulta);

        while (resultado.next()) {
            String nome = resultado.getString("nome");
            Integer quantidade = resultado.getInt("qtdAcesso");
            String naturalidade = resultado.getString("naturalidade");

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setQuantidadeAcesso(quantidade);
            pessoa.setNaturalidade(naturalidade);

            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Quantidade de Acessos: " + pessoa.getQuantidadeAcesso());
            System.out.println("Naturalidade: " + pessoa.getNaturalidade());
            System.out.println();
        }

        System.out.println("\nTchau, até a próxima\n\n\t\t:-)");
    }
}
