package net.unibave.showcase.categoria;

import net.unibave.showcase.base.EntityId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "categoria")
@SequenceGenerator(name = "categoria_seq", sequenceName = "categoria_seq")
public class Categoria implements Serializable, EntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="categoria_seq")
    @Column(name = "cod_categoria")
    private Long id;

    @Column(name = "nome")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Categoria other = (Categoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nome=" + nome + '}';
    }

    public static class Builder {
        
        private final Categoria carro = new Categoria();
        
        public Builder nome(String nome) {
            carro.setNome(nome);
            return this;
        }
                
        public Categoria create() {
            return carro;
        }
        
    }

}
