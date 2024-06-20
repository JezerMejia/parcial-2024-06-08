package ni.factorizacion.parcial20240608.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
public class User implements UserDetails {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<History> history;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles;
    @OneToMany(mappedBy = "medic", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<AppointmentMedicSpecialty> appointmentMedicSpecialty = new HashSet<>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getId()));
        }
        return authorities;
    }

    public void addRole(Role role) {
        if (roles.contains(role)) return;
        this.getRoles().add(role);
        role.getUsers().add(this);
    }
    public void removeRole(Role role) {
        this.getRoles().remove(role);
        role.getUsers().remove(this);
    }
}
