package br.edu.unisep.tads.ifud.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    })
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    @Size(max = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 150)
    @NotBlank
    @Size(max=150)
    @Email
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("current_timestamp")
    private Date createdDate;

    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    private String updatedBy;
    
    @Column(name = "updated_date", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("current_timestamp")
    private Date updatedDate;

  public User(String user, String email, String password) {
    this.username = user;
    this.email = email;
    this.password = password;
    this.createdDate = new Date();
    this.updatedDate = new Date();
    this.createdBy = user;
    this.updatedBy = user;
  }
}
