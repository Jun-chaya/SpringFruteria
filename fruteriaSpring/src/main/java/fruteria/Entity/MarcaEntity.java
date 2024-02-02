package fruteria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Marca")
public class MarcaEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@NonNull
	@Column(name = "nombre")
	private String nombre;
}
