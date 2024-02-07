package fruteria.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LineaAlbaran")
public class LineaAlbaranEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "albaranId", nullable = false)
	private AlbaranEntity albaran;

	@ManyToOne
	@JoinColumn(name = "itemId", nullable = false)
	private ItemEntity item;

	private Integer cantidad;
}
