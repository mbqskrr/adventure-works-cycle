package com.example.BalantaTaller1.model.prod;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Future;
//import javax.validation.constraints.Future;
//import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.BalantaTaller1.model.validation.WorkorderValidation;

/*import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
*/
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


/**
 * The persistent class for the workorder database table.
 * 
 */
@Entity
@NamedQuery(name="Workorder.findAll", query="SELECT w FROM Workorder w")
public class Workorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WORKORDER_WORKORDERID_GENERATOR",allocationSize = 1, sequenceName="WORKORDER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKORDER_WORKORDERID_GENERATOR")
	private Integer workorderid;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp duedate;

	@Future(groups = WorkorderValidation.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups = WorkorderValidation.class)
	private LocalDate enddate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	@Positive(groups = WorkorderValidation.class)
	private Integer orderqty;

	@PositiveOrZero(groups = WorkorderValidation.class)
	private Integer scrappedqty;
	
	@PastOrPresent(groups = WorkorderValidation.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups = WorkorderValidation.class)
	private LocalDate startdate;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	@NotNull(groups = WorkorderValidation.class)
	private Product product;

	//bi-directional many-to-one association to Scrapreason
	@ManyToOne
	@JoinColumn(name="scrapreasonid")
	@NotNull(groups = WorkorderValidation.class)
	private Scrapreason scrapreason;

	//bi-directional many-to-one association to Workorderrouting
	@OneToMany(mappedBy="workorder")
	private List<Workorderrouting> workorderroutings;

	public Workorder() {
	}

	public Integer getWorkorderid() {
		return this.workorderid;
	}

	public void setWorkorderid(Integer workorderid) {
		this.workorderid = workorderid;
	}

	public Timestamp getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public LocalDate getEnddate() {
		return this.enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getOrderqty() {
		return this.orderqty;
	}

	public void setOrderqty(Integer orderqty) {
		this.orderqty = orderqty;
	}

	public Integer getScrappedqty() {
		return this.scrappedqty;
	}

	public void setScrappedqty(Integer scrappedqty) {
		this.scrappedqty = scrappedqty;
	}

	public LocalDate getStartdate() {
		return this.startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Scrapreason getScrapreason() {
		return this.scrapreason;
	}

	public void setScrapreason(Scrapreason scrapreason) {
		this.scrapreason = scrapreason;
	}

	public List<Workorderrouting> getWorkorderroutings() {
		return this.workorderroutings;
	}

	public void setWorkorderroutings(List<Workorderrouting> workorderroutings) {
		this.workorderroutings = workorderroutings;
	}

	public Workorderrouting addWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().add(workorderrouting);
		workorderrouting.setWorkorder(this);

		return workorderrouting;
	}

	public Workorderrouting removeWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().remove(workorderrouting);
		workorderrouting.setWorkorder(null);

		return workorderrouting;
	}

}