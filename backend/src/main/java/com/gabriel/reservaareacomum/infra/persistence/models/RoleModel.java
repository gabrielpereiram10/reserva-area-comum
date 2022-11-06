package com.gabriel.reservaareacomum.infra.persistence.models;

import com.gabriel.reservaareacomum.shared.valueObjects.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "ROLES")
public class RoleModel implements GrantedAuthority, Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Role roleName;

    public RoleModel(Role roleName) {
        this.roleName = roleName;
    }

    public RoleModel() {

    }

    @Override
    public String getAuthority() {
        return roleName.toString();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }
}
