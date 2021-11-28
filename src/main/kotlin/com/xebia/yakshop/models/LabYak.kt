package com.xebia.yakshop.models


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.xebia.yakshop.utils.Sex
import javax.persistence.*

@Entity
@Table(name = "labyak")
data class LabYak(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var yakId: Long,
    @ManyToOne
    @JoinColumn(name = "herd_id")
    @JsonIgnore
    var herd: Herd?,
    var name: String,
    var age: Double,
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    var sex: Sex = Sex.f,
    @JsonProperty("age-last-shaved")
    var ageLastShaved: Double
)
