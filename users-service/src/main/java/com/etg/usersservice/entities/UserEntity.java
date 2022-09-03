package com.etg.usersservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity implements Serializable {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "id",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID userId;
    @Column(nullable = false, length =25)
    private String firstName;
    @Column(nullable = false, length =25)
    private String lastName;
    private String dateOfBirth;
    @Column(nullable = false, length =25, unique = true)
    private String email;
    private String encryptedPassword;

}
