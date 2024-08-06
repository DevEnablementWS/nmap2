package com.okazukka.nmap2

import jakarta.persistence.*


@Entity(name = "makers")
class MakerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val companyName: String,

    @OneToMany(mappedBy = "maker", cascade = [CascadeType.ALL])
    val toilets: MutableList<ToiletEntity> = mutableListOf(),
)

//Hibernate: insert into makers (company_name,id) values (?,default)
//Hibernate: insert into toilets (address,maker_id,name,id) values (?,?,?,default)
//Hibernate: insert into toilets (address,maker_id,name,id) values (?,?,?,default)
//Hibernate: select me1_0.id,me1_0.company_name from makers me1_0
//Hibernate: select te1_0.id,te1_0.address,te1_0.maker_id,te1_0.name from toilets te1_0


//select t1_0.makers_id,t1_1.id,t1_1.address,m1_0.id,m1_0.company_name,t1_1.name
// from makers_toilets t1_0
// join toilets t1_1 on t1_1.id=t1_0.toilets_id left
// join makers m1_0 on m1_0.id=t1_1.maker_id
// where t1_0.makers_id=?