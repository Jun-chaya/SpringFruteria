package fruteria.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Recibo")
public class ReciboEntity {

	@Id
	@Column(name = "id")
	private Integer id;

	@NonNull
	@OneToOne
	@JoinColumn(name = "clienteId")
	private ClienteEntity clienteId;

	@Column(name = "fecha")
	private LocalDate fecha;

	@OneToMany(mappedBy = "reciboId")
	private List<LineaReciboEntity> lineasRecibo;
}
