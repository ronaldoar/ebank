package br.com.csoft.ebank.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
    
    @NotNull
    @NotEmpty
    @Email
    @Column(name = "username", nullable = false, length = 50)
	private String username;
    
    @NotNull
    @NotEmpty
    @Email
    @Column(name = "password", nullable = false, length = 8)
	private String password;
    
    @NotNull
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false, length = 30)
	private UsuarioPerfil perfil;
    
    @NotNull
    @NotEmpty
    @Column(name = "dt_cadastro", nullable = false)
	private LocalDateTime dtCadastro;
	
    @Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsuarioPerfil getPerfil() {
		return perfil;
	}
	public void setPerfil(UsuarioPerfil perfil) {
		this.perfil = perfil;
	}
	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", perfil=" + perfil
				+ ", dtCadastro=" + dtCadastro + ", ativo=" + ativo + "]";
	}
	
	
}
