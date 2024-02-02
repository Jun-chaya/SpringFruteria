package fruteria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LineaAlbaran")
public class LineaAlbaranEntity {
	
	@ManyToOne
	@JoinColumn(name = "albaranId")
	private AlbaranEntity albaranId;
	
	@NonNull
	@OneToOne
	@JoinColumn(name = "itemId")
	private ItemEntity itemId;
	
	@Column(name = "cantidad")
	private Integer cantidad;
}
