package com.zee.zee5app.dto;

import java.net.URL;
import java.lang.*;
import java.util.Objects;
import javax.naming.NameNotFoundException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "movieName")}, name = "movies")
public class Movie implements Comparable<Movie>{
	
	@Id
	@Column(name = "id")
	@Length(min = 6)
	private String id;
	
	@NotBlank
	private String movieName;
	@NotBlank
	private String cast;
	private int length;
	@NotNull
	private String releaseDate;
//	@Lob // this to to store file as blob
//	private byte[] trailer;
	private String trailer;
	@NotBlank
	private String language;
	@Max(value = 70)
	private int ageLimit;
	@NotBlank
	private String genre;
	
	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
	
}
