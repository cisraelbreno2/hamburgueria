package br.com.hamburgueria.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class OrderModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientModel client;
	
	@ManyToOne
	@JoinColumn(name = "route_id")
	private RouteModel route;

}
