package com.oklimenko.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @Generated(GenerationTime.INSERT)
//    @Column(nullable = false, columnDefinition = "bigserial")
//    @SequenceGenerator(name="payment_id_seq",
//            sequenceName="payment_id_seq",
//            allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator="payment_id_seq")
    private long clientId;
    private String status;
    private long serviceId;
    private String clientComment;
    private BigDecimal amount;
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID idempotancyKey;
    private LocalDateTime dateCreated;
    private LocalDateTime dateCompleted;
}
