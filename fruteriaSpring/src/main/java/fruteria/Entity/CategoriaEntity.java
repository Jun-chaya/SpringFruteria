package fruteria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Categoria")
public class CategoriaEntity {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
}
