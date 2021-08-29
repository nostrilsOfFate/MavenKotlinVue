package com.lenakotlinvuepractice.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "users")
data class User (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = 0,

        @Column(name="username")
        var username: String?=null,

        @Column(name="first_name")
        var firstName: String?=null,

        @Column(name="last_name")
        var lastName: String?=null,

        @Column(name="email")
        var email: String?=null,

        @Column(name="password")
        var password: String?=null,

        @Column(name="enabled")
        var enabled: Boolean = false,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Collection<Role>? = null
)

@Entity
@Table(name = "roles")
data class Role (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name="name")
        val name: String

)

class LoginUser : Serializable {

        @JsonProperty("username")
        var username: String? = null

        @JsonProperty("password")
        var password: String? = null

        constructor() {}

        constructor(username: String, password: String) {
                this.username = username
                this.password = password
        }

        companion object {
                private const val serialVersionUID = -1764970284520387975L
        }
}

class NewUser : Serializable {

        @JsonProperty("username")
        var username: String? = null

        @JsonProperty("firstName")
        var firstName: String? = null

        @JsonProperty("lastName")
        var lastName: String? = null

        @JsonProperty("email")
        var email: String? = null

        @JsonProperty("password")
        var password: String? = null

        constructor() {}

        constructor(username: String, firstName: String, lastName: String, email: String, password: String, recaptchaToken: String) {
                this.username = username
                this.firstName = firstName
                this.lastName = lastName
                this.email = email
                this.password = password
        }

        companion object {
                private const val serialVersionUID = -1764970284520387975L
        }
}

class JwtResponse(var accessToken: String?, var username: String?, val authorities:
Collection<GrantedAuthority>) {
        var type = "Bearer"
}

class ResponseMessage(var message: String?)

