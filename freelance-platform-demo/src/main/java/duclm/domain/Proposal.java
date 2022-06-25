package duclm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proposals")
public class Proposal implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long proposalId;
	
	@Column(nullable = false)
	private double bid;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String description;

	@Column(nullable = false)
	private int status;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne
    @JoinColumn(name = "job_id")
	Job job;
}
