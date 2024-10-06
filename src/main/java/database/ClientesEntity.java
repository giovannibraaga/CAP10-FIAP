package database;

import javax.persistence.*;

@Entity
@Table(name = "clientesfiap")
public class ClientesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;

    @Column(name = "CONTATO_TELEFONICO")
    private String contatoTelefonico;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "CPF")
    private String cpf;

    public ClientesEntity() {
    }

    public ClientesEntity(String nome, String dataNascimento, String contatoTelefonico, String email, String endereco, String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.contatoTelefonico = contatoTelefonico;
        this.email = email;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getContatoTelefonico() {
        return contatoTelefonico;
    }

    public void setContatoTelefonico(String contatoTelefonico) {
        this.contatoTelefonico = contatoTelefonico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", contatoTelefonico='" + contatoTelefonico + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
