package com.osu.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.osu.demo.api.model.LobsterKind;

/**
 * The Class Lobster.
 * 
 * @author Vinod Arroju
 */
@Entity
@Table(name = "lobsters")
@SequenceGenerator(name="lobsterSeq", initialValue=110)
public class Lobster {
	
	/** The lobster id. */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="lobsterSeq")
	@Column(name = "lobster_id")
    private Integer lobsterId;
	
	/** The name. */
	@Column(nullable = false)
    private String name;
	
    /** The description. */
    private String description;
    
    /** The kind. */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LobsterKind kind;
    
    /** The created by. */
    @Column(name = "created_by")
    private String createdBy;
    
    /** The modified by. */
    @Column(name = "modified_by")
    private String modifiedBy;
    
    /** The created date. */
    @Column(name = "created_date")
    private Date createdDate;
    
    /** The modified date. */
    @Column(name = "modified_date")
    private Date modifiedDate;

	/**
	 * Gets the lobster id.
	 *
	 * @return the lobster id
	 */
	public Integer getLobsterId() {
		return lobsterId;
	}

	/**
	 * Sets the lobster id.
	 *
	 * @param lobsterId the new lobster id
	 */
	public void setLobsterId(Integer lobsterId) {
		this.lobsterId = lobsterId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the kind.
	 *
	 * @return the kind
	 */
	public LobsterKind getKind() {
		return kind;
	}

	/**
	 * Sets the kind.
	 *
	 * @param kind the new kind
	 */
	public void setKind(LobsterKind kind) {
		this.kind = kind;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	
}
