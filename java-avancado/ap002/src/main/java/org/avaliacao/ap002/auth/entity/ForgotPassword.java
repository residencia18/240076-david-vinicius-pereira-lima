package org.avaliacao.ap002.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.avaliacao.ap002.auth.entity.user.User;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forgotPassID;

    @Column(nullable = false)
    private Integer oneTimePass;

    @Column(nullable = false)
    private Date expirationTime;

    @OneToOne
    private User user;


}
