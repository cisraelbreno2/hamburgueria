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
@Table(name = "order_product")
@Data
public class OrderProductModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String note;
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderModel order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductModel product;
	
}
