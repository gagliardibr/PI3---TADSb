package br.senac.tads.pi3b.agenda1;

import java.util.Date;

/**
 *
 * @author bruna.gagliardi
 */
public class Pessoa {
    
    private long id;
    
    private String nome;
    
    private Date dtNascimento; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    
    
            
}
