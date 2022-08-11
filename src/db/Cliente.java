package db;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Integer codigo;
    private String nome;
    private List<Telefone> telefone;
    
    public Cliente() {
     this.telefone = new ArrayList<>();
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }
    
    public void setTelefone(Telefone telefone) {
        this.telefone.add(telefone);
    }
}
