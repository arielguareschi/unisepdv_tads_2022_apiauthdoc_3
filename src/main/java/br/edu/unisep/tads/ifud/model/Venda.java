package br.edu.unisep.tads.ifud.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venda")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Venda {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("current_timestamp")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "transportador_id")
    private Transportador transportador;
    
    @Column(name = "observacao")
    private String observacao;

    @OneToMany(mappedBy = "venda", 
               targetEntity = VendaItem.class,
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL )
    @JsonIgnore
    private Set<VendaItem> itens;
    
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
}
