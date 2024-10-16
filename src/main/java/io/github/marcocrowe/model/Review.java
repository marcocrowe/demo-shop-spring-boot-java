package io.github.marcocrowe.model;


import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "customer", referencedColumnName = "id")
    @ManyToOne
    @ToString.Exclude
    private Customer customer;

    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    @ToString.Exclude
    private Product product;

    private Integer rating;

    @Lob
    private String reviewText;

    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    private boolean isFlaggedSpam;

}