package fruteria.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Albaran")
public class AlbaranEntity {

	@Id
	@Column(name = "id")
	private Integer id;

	@NonNull
	@OneToOne
	@Column(name = "proveedorId")
	private ProveedorEntity proveedorId;
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@OneToMany(mappedBy = "albaranId")
	private List<LineaAlbaranEntity> lineasAlbaran;
}
