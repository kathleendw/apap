package apapedia.user.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_apapedia")
@SQLDelete(sql = "UPDATE user_apapedia SET deleted = true WHERE id_user=?")
@Where(clause = "deleted=false")
public class UserApapedia {
    @Id
    private UUID idUser = UUID.randomUUID();

    @NotNull
    @Column(name = "name_user", unique = true)
    private String nameUser;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String email;

    @NotNull
    private long balance = 0;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private LocalDateTime createdAt;

    @NotNull
    @Column
    private LocalDateTime updatedAt;

    @NotNull
    @Column
    private boolean deleted;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private ERole role;
}
