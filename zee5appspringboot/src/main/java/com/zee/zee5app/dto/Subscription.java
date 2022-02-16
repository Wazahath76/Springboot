package com.zee.zee5app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Table(name = "subscription")
public class Subscription implements Comparable<Subscription>{

	@Id
	@Column(name = "id")
	@Length(min = 6)
	private String id;
	@NotNull 
    private String dateOfPurchase;
	
	@NotNull
    private float amount;
	private String paymentMode;
	@NotNull
    private String expiryDate;
	@NotBlank
    private String status;
	@NotBlank
	private String type;
	@NotBlank
    private String autoRenewal;

	@Override
	public int compareTo(Subscription o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
	
	@OneToOne
	@JoinColumn(name = "regId")
	@JsonProperty(access=Access.WRITE_ONLY)
	private User register;
}
