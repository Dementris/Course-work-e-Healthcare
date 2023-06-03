package com.cursework.ehelthcare.token;


import com.cursework.ehelthcare.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Token.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    /**
     * The Id.
     */
    @Id
  @GeneratedValue
  public Integer id;

    /**
     * The Token.
     */
    @Column(unique = true)
  public String token;

    /**
     * The Token type.
     */
    @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

    /**
     * The Revoked.
     */
    public boolean revoked;

    /**
     * The Expired.
     */
    public boolean expired;

    /**
     * The User.
     */
    @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "users_id")
  public User user;
}
