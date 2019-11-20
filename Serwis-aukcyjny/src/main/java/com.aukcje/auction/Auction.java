//package com.aukcje.auction;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//@Data
//@Entity
//@Table
//public class Auction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id_auction",nullable = false)
//    private Long id;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private String description;
//
//    @Column(nullable = false)
//    private BigDecimal minPrice;
//
//    @Column(nullable = false)
//    private BigDecimal buyNowPrice;
//
//    @Column(nullable = false)
//    private String localization;
//
//    @Column(nullable = false)
//    private LocalDateTime dateStartedAuction;
//
//    @Column(nullable = false)
//    private LocalDateTime dateEndedAuction;
//
//    @Column(nullable = false)
//    private Long numbersOfViewers;
//
//
//
//
//}
