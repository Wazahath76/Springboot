package com.zee.zee5app.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "seriesName")}, name = "series")
public class Series implements Comparable<Series> {
	
	@Id
	@Column(name = "id")
	@Length(min = 6)
	private String id;
	
    @NotBlank
	private String seriesName;
    @NotBlank
	private String cast;
	@NotNull
	private String releaseDate;
	//@NotBlank
	private String trailer;
	@NotBlank
	private String language;
	
	@Max(value = 70)
	private String ageLimit;
	
	@NotBlank
	private String genre;

	@Min(value = 1)
	private int noofEpisodes;
	
	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
	
	@OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
	private List<Episodes> episodes = new ArrayList<>();
	


	
}